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
package ai.instance.illuminaryObelisk;

import ai.ActionItemNpcAI2;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("shield_control_room")
public class ShieldControlRoomTeleporterAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 730886: //Shield Control Room Teleporter.
				switch (player.getWorldId()) {
                    case 301230000: //Illuminary Obelisk 4.5
					    PacketSendUtility.sendMessage(player, "you enter <Illuminary Obelisk>");
						TeleportService2.teleportTo(player, 301230000, 266.04742f, 244.20813f, 455.17575f, (byte) 45);
			        break;
				} switch (player.getWorldId()) {
                    case 301370000: //[Infernal] Illuminary Obelisk 4.7
					    PacketSendUtility.sendMessage(player, "you enter <[Infernal] Illuminary Obelisk>");
						TeleportService2.teleportTo(player, 301370000, 266.04742f, 244.20813f, 455.17575f, (byte) 45);
			        break;
				}
		    break;
		}
	}
}