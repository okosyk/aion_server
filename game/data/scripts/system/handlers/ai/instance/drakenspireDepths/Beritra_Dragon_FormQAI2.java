/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package ai.instance.drakenspireDepths;

import java.util.*;
import java.util.concurrent.Future;

import ai.AggressiveNpcAI2;

import java.util.concurrent.atomic.AtomicBoolean;

import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.services.*;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("beritra_dragon_formQ")
public class Beritra_Dragon_FormQAI2 extends AggressiveNpcAI2
{
	private Future<?> dragonFormTaskQ;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 237238:
					//Beritra will disappear when the relic is completely extracted in 7 minutes.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402722, 0);
					//Beritra will disappear when the relic is completely extracted in 5 minutes.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402724, 120000);
					//Beritra will disappear when the relic is completely extracted in 1 minute.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402725, 360000);
					//Beritra will disappear when the relic is completely extracted in a moment.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402726, 370000);
					//The extraction of the Balaur Lord's Relic is complete and Beritra has disappeared.
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1402720, 420000);
					dragonFormTaskQ = ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							AI2Actions.deleteOwner(Beritra_Dragon_FormQAI2.this);
						}
					}, 420000);
				break;
			}
		}
	}
	
	private void cancelDragonFormTaskQ() {
		if (dragonFormTaskQ != null && !dragonFormTaskQ.isDone()) {
			dragonFormTaskQ.cancel(true);
		}
	}
	
	@Override
	protected void handleDied() {
		cancelDragonFormTaskQ();
		super.handleDied();
	}
}