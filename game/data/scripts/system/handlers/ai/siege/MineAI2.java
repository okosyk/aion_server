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
package ai.siege;

import ai.AggressiveNpcAI2;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("siege_mine")
public class MineAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleCreatureAggro(Creature creature) {
		AI2Actions.useSkill(this, 21866); //Wide Area Explosion.
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(MineAI2.this);
			}
		}, 1000);
	}
}