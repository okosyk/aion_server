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
package ai.event.conquestOffering;

import ai.AggressiveNpcAI2;

import java.util.*;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.*;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.world.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("conquest_gelkmaros")
public class Conquest_Gelkmaros_BossAI2 extends AggressiveNpcAI2
{
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		boostDefense();
    }
	
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			sendGuide();
		} switch (Rnd.get(1, 2)) {
			case 1:
			    spawnSecretPortal();
			break;
			case 2:
			break;
		}
		super.handleDied();
	}
	
    private void spawnSecretPortal() {
    	final WorldPosition p = getPosition();
		if (p != null) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					spawn(833021, p.getX(), p.getY(), p.getZ(), (byte) 0); //Secret Portal.
				}
			}, 15000);
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
				    despawnNpc(833021); //Secret Portal.
				}
			}, 300000); //5 Minutes.
		}
    }
	
    private void spawnQuestionablePortal() {
    	final WorldPosition p = getPosition();
		if (p != null) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					spawn(833022, p.getX(), p.getY(), p.getZ(), (byte) 0); //Questionable Portal.
				}
			}, 15000);
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
				    despawnNpc(833022); //Secret Portal.
				}
			}, 300000); //5 Minutes.
		}
    }
	
    private void spawnConquestNpcBuff() {
    	final WorldPosition p = getPosition();
		if (p != null) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					switch (Rnd.get(1, 4)) {
					    case 1:
						    spawn(856175, p.getX(), p.getY(), p.getZ(), (byte) 0); //Pawrunerk.
					    break;
						case 2:
						    spawn(856176, p.getX(), p.getY(), p.getZ(), (byte) 0); //Chitrunerk.
					    break;
						case 3:
						    spawn(856177, p.getX(), p.getY(), p.getZ(), (byte) 0); //Rapirunerk.
					    break;
						case 4:
						    spawn(856178, p.getX(), p.getY(), p.getZ(), (byte) 0); //Dandrunerk.
					    break;
					}
				}
			}, 15000);
		}
    }
	
	private void boostDefense() {
		SkillEngine.getInstance().getSkill(getOwner(), 21923, 1, getOwner()).useNoAnimationSkill(); //Boost Defense.
	}
	
	private void sendGuide() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(player, getOwner(), 15)) {
					HTMLService.sendGuideHtml(player, "Conquest_Offering");
				}
			}
		});
	}
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
}