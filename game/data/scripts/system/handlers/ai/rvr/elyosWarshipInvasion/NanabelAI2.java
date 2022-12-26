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
package ai.rvr.elyosWarshipInvasion;

import ai.AggressiveNpcAI2;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.*;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("nanabel")
public class NanabelAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 75) {
			announceDF6G1BossSpawn01();
			spawn(240769, 1391.9735f, 1615.5792f, 1010.55457f, (byte) 25); //Nanabel <Commander>
			AI2Actions.deleteOwner(this);
		}
	}
	
	private void announceDF6G1BossSpawn01() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				//The Elyos Frigate Commander has arrived.
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_DF6_G1_Boss_Spawn_01);
			}
		});
	}
}