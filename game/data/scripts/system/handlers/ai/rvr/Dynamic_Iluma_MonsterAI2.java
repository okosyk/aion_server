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
package ai.rvr;

import ai.AggressiveNpcAI2;

import java.util.*;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.*;
import com.aionemu.gameserver.world.*;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("dynamic_iluma_monster")
public class Dynamic_Iluma_MonsterAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		switch (Rnd.get(1, 2)) {
			case 1:
				spawnLF6EventDoor();
			break;
			case 2:
			break;
		}
		super.handleDied();
	}
	
	private void spawnLF6EventDoor() {
		spawn(241053, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Portal.
		switch (Rnd.get(1, 4)) {
			case 1:
			    spawn(240887, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Warrior.
			break;
			case 2:
				spawn(240888, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Mage.
			break;
			case 3:
				spawn(240889, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Scout.
			break;
			case 4:
				spawn(240890, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Archon Marksman.
			break;
		}
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				despawnNpc(241053); //Portal.
			}
		}, 60000);
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