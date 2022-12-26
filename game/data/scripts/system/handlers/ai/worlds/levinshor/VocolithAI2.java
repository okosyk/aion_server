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
package ai.worlds.levinshor;

import java.util.*;

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;
import com.aionemu.gameserver.world.World;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("vocolith")
public class VocolithAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(185000216) != null) { //Ancestor's Relic.
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 27));
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF4_Advance_FNamed_Fail);
        }
    }
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000 && player.getInventory().decreaseByItemId(185000216, 1)) {
		    switch (getNpcId()) {
		        //Vocolith [North Warden]
				case 804573:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [Coast Warden]
			    case 804574:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [South Warden]
			    case 804575:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [East Woods Warden]
			    case 804579:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [North Woods Warden]
			    case 804580:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [Cut Warden]
			    case 804581:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [West Warden]
			    case 804582:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
				//Vocolith [East Warden]
			    case 804583:
				    switch (Rnd.get(1, 4)) {
					    case 1:
						    announceLevinshorBoss();
							spawn(235217, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Lava Arm Cruego.
						break;
						case 2:
						    announceLevinshorBoss();
						    spawn(235218, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Cruel Lamia.
						break;
						case 3:
						    announceLevinshorBoss();
							spawn(235219, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Destoyer Feld.
						break;
						case 4:
						    announceLevinshorBoss();
							spawn(235220, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //Ruthless Tyranicca.
						break;
					}
				break;
			}
		}
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF4_Advance_FNamed_Spawn_Item);
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
		return true;
	}
	
	@Override
    protected void handleSpawned() {
        announceLevinshorBossReturn();
		super.handleSpawned();
    }
	
	private void announceLevinshorBossReturn() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF4_Advance_Summon_Named_Time);
			}
		});
	}
	
	private void announceLevinshorBoss() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LDF4_Advance_FNamed_Spawn);
			}
		});
	}
}