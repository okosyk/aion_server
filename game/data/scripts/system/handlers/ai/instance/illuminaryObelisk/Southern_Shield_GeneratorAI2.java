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

import java.util.*;
import javolution.util.FastMap;

import java.util.concurrent.atomic.AtomicBoolean;
import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.*;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("southern_shield_generator")
public class Southern_Shield_GeneratorAI2 extends NpcAI2
{
	private boolean isInstanceDestroyed;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private FastMap<Integer, VisibleObject> objects = new FastMap<Integer, VisibleObject>();
	
	@Override
    protected void handleDialogStart(Player player) {
        if (player.getInventory().getFirstItemByItemId(164000289) != null) {
            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
        } else {
            PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402211));
        }
    }
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 702012: //Southern Shield Generator.
				    announceIDF5U3Defence03();
				break;
			}
		}
	}
	
	private void announceIDF5U3Defence03() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_U3_DEFENCE_03_ATTACKED);
				}
			}
		});
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		switch (getNpcId()) {
		    case 702012: //Southern Shield Generator.
		        switch (player.getWorldId()) {
		            case 301230000: //Illuminary Obelisk 4.5
				        if (dialogId == 10000 && player.getInventory().decreaseByItemId(164000289, 3)) {
			                startWSSG1();
					        PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402226));
					        SpawnTemplate AionFXPostGlow = SpawnEngine.addNewSingleTimeSpawn(301230000, 702016, 343.12021f, 254.10585f, 291.62302f, (byte) 0);
					        AionFXPostGlow.setEntityId(34);
					        objects.put(702016, SpawnEngine.spawnObject(AionFXPostGlow, instanceId));
					    }
				    break;
			    } switch (player.getWorldId()) {
				    case 301370000: //[Infernal] Illuminary Obelisk 4.7
					    if (dialogId == 10000 && player.getInventory().decreaseByItemId(164000289, 3)) {
					        startIWSSG1();
					        PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402226));
					        SpawnTemplate AionFXPostGlow = SpawnEngine.addNewSingleTimeSpawn(301370000, 702016, 343.12021f, 254.10585f, 291.62302f, (byte) 0);
						    AionFXPostGlow.setEntityId(34);
						    objects.put(702016, SpawnEngine.spawnObject(AionFXPostGlow, instanceId));
						}
					break;
				}
			break;
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
	
	private void startWSSG1() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackGenerator((Npc)spawn(233729, 337.93338f, 257.88702f, 292.43845f, (byte) 60), 298.06833f, 257.82462f, 295.92047f, false);
				attackGenerator((Npc)spawn(233730, 338.05304f, 254.6424f, 292.3325f, (byte) 60), 298.272f, 254.66296f, 295.94693f, false);
				attackGenerator((Npc)spawn(233731, 338.13315f, 251.34738f, 292.48932f, (byte) 59), 297.2915f, 252.06613f, 295.854f, false);
			}
		}, 1000);
	}
	
	private void startIWSSG1() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackGenerator((Npc)spawn(234729, 337.93338f, 257.88702f, 292.43845f, (byte) 60), 298.06833f, 257.82462f, 295.92047f, false);
				attackGenerator((Npc)spawn(234730, 338.05304f, 254.6424f, 292.3325f, (byte) 60), 298.272f, 254.66296f, 295.94693f, false);
				attackGenerator((Npc)spawn(234731, 338.13315f, 251.34738f, 292.48932f, (byte) 59), 297.2915f, 252.06613f, 295.854f, false);
			}
		}, 1000);
	}
	
	private void attackGenerator(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	public void onInstanceDestroy() {
		isInstanceDestroyed = true;
	}
}