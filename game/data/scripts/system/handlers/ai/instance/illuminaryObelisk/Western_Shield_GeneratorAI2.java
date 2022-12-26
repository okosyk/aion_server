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

@AIName("western_shield_generator")
public class Western_Shield_GeneratorAI2 extends NpcAI2
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
				case 702011: //Western Shield Generator.
				    announceIDF5U3Defence02();
				break;
			}
		}
	}
	
	private void announceIDF5U3Defence02() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_U3_DEFENCE_02_ATTACKED);
				}
			}
		});
	}
	
	@Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
		int instanceId = getPosition().getInstanceId();
		switch (getNpcId()) {
		    case 702011: //Western Shield Generator.
		        switch (player.getWorldId()) {
		            case 301230000: //Illuminary Obelisk 4.5
				        if (dialogId == 10000 && player.getInventory().decreaseByItemId(164000289, 3)) {
			                startWWSG1();
					        PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402225));
					        SpawnTemplate RecvShadowPLSM = SpawnEngine.addNewSingleTimeSpawn(301230000, 702015, 255.7034f, 171.83853f, 325.81653f, (byte) 0);
					        RecvShadowPLSM.setEntityId(18);
					        objects.put(702015, SpawnEngine.spawnObject(RecvShadowPLSM, instanceId));
					    }
				    break;
			    } switch (player.getWorldId()) {
				    case 301370000: //[Infernal] Illuminary Obelisk 4.7
					    if (dialogId == 10000 && player.getInventory().decreaseByItemId(164000289, 3)) {
					        startIWWSG1();
					        PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402225));
					        SpawnTemplate RecvShadowPLSM = SpawnEngine.addNewSingleTimeSpawn(301370000, 702015, 255.7034f, 171.83853f, 325.81653f, (byte) 0);
						    RecvShadowPLSM.setEntityId(18);
						    objects.put(702015, SpawnEngine.spawnObject(RecvShadowPLSM, instanceId));
						}
					break;
				}
			break;
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
	
	private void startWWSG1() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackGenerator((Npc)spawn(233726, 258.37912f, 176.03621f, 325.59268f, (byte) 30), 258.4031f, 212.42247f, 321.33325f, false);
				attackGenerator((Npc)spawn(233727, 255.55922f, 176.17963f, 325.49332f, (byte) 29), 255.8037f, 212.23003f, 321.34384f, false);
				attackGenerator((Npc)spawn(233728, 252.49738f, 176.27466f, 325.52942f, (byte) 29), 253.00607f, 213.30444f, 321.28207f, false);
			}
		}, 1000);
	}
	
	private void startIWWSG1() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackGenerator((Npc)spawn(234726, 258.37912f, 176.03621f, 325.59268f, (byte) 30), 258.4031f, 212.42247f, 321.33325f, false);
				attackGenerator((Npc)spawn(234727, 252.49738f, 176.27466f, 325.52942f, (byte) 29), 253.00607f, 213.30444f, 321.28207f, false);
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