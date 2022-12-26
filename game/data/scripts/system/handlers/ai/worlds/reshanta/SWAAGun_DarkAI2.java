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
package ai.worlds.reshanta;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("SWAAGun_Dark")
public class SWAAGun_DarkAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(186000246) != null) { //Magic Cannonball.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 10));
        } else {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_AB1_PCTank_NoItem);
        }
    }
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000) {
		    switch (getNpcId()) {
				//Archon Battery [Reshanta]
				case 251735:
				case 251755:
				case 251775:
				//Asmodian Defense Turret [Kaldor]
				case 252171:
				case 252172:
				case 252173:
				case 252174:
				case 252175:
				case 252176:
				case 252177:
				//Empty Aetheric Cannon [Reshanta]
				case 881982:
				    SkillEngine.getInstance().getSkill(player, 21518, 1, player).useNoAnimationSkill();
				break;
			}
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
		return true;
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}