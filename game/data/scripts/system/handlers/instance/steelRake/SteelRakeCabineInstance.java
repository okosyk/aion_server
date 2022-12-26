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
package instance.steelRake;

import java.util.*;
import javolution.util.FastMap;
import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.instance.handlers.GeneralInstanceHandler;
import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.drop.DropItem;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.services.drop.DropRegistrationService;
import com.aionemu.gameserver.services.player.PlayerReviveService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.knownlist.Visitor;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Rinzler (Encom)
/****/

@InstanceID(300460000)
public class SteelRakeCabineInstance extends GeneralInstanceHandler
{
	private boolean isInstanceDestroyed;
	private FastMap<Integer, VisibleObject> objects = new FastMap<Integer, VisibleObject>();
	
	@Override
	public void onInstanceCreate(WorldMapInstance instance) {
		super.onInstanceCreate(instance);
		switch (Rnd.get(1, 3)) {
		    case 1:
				spawn(219026, 353.814f, 491.557f, 949.466f, (byte) 119); //Sweeper Nunukin.
			break;
			case 2:
				spawn(219032, 462.71558f, 512.5599f, 952.5455f, (byte) 1); //Madame Bovariki.
			break;
			case 3:
				spawn(219032, 506.1134f, 545.7197f, 952.4226f, (byte) 74); //Madame Bovariki.
			break;
		}
	}
	
	@Override
    public void onDropRegistered(Npc npc) {
        Set<DropItem> dropItems = DropRegistrationService.getInstance().getCurrentDropMap().get(npc.getObjectId());
		int npcId = npc.getNpcId();
        switch (npcId) {
			case 219040: //Tamer Anikiki [Steel Rake Cabin]
				dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 182209084, 1)); //Taming A Manduri.
				dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 182209099, 1)); //Taming A Manduri.
			break;
			case 219033: //Golden Eye Mantutu.
			    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 188053787, 1)); //Stigma Support Bundle.
			break;
			case 215489: //Treasure Box.
			case 700553: //Treasure Box.
			case 700554: //Pirate Ship Treasure Box.
				switch (Rnd.get(1, 6)) {
					case 1:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050017, 2)); //Blue Idian: Physical Attack.
					break;
					case 2:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050018, 2)); //Blue Idian: Magical Attack.
					break;
					case 3:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050019, 2)); //Blue Idian: Physical Defense.
					break;
					case 4:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050020, 2)); //Blue Idian: Magical Defense.
					break;
					case 5:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050021, 2)); //Blue Idian: Assistance.
					break;
					case 6:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 166050022, 2)); //Blue Idian: Resistance.
					break;
				}
			break;
        }
    }
	
	@Override
	public void onDie(Npc npc) {
		Player player = npc.getAggroList().getMostPlayerDamage();
		switch (npc.getObjectTemplate().getTemplateId()) {
			case 700549: //Air Vent Cover.
			    despawnNpc(npc);
			break;
			case 219033: //Golden Eye Mantutu.
				sendMsg("[Congratulation]: you finish <Steel Rake Cabine>");
				spawn(700554, 736.64728f, 493.73834f, 941.4781f, (byte) 45); //Pirate Ship Treasure Box.
				spawn(700554, 720.41028f, 511.63718f, 939.7604f, (byte) 90); //Pirate Ship Treasure Box.
		        spawn(700554, 739.51251f, 506.14313f, 941.4781f, (byte) 77); //Pirate Ship Treasure Box.
				spawn(700554, 721.76172f, 491.83142f, 939.6068f, (byte) 32); //Pirate Ship Treasure Box.
				SpawnTemplate hiddenPassage = SpawnEngine.addNewSingleTimeSpawn(300460000, 730766, 734.18994f, 484.61578f, 941.70868f, (byte) 0);
				hiddenPassage.setEntityId(61);
				objects.put(730766, SpawnEngine.spawnObject(hiddenPassage, instanceId)); //Hidden Passage.
			break;
		}
	}
	
	private void sendMsg(final String str) {
		instance.doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendMessage(player, str);
			}
		});
	}
	
	private void despawnNpc(Npc npc) {
		if (npc != null) {
			npc.getController().onDelete();
		}
	}
	
	@Override
    public boolean onReviveEvent(Player player) {
		player.getGameStats().updateStatsAndSpeedVisually();
		PlayerReviveService.revive(player, 100, 100, false, 0);
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_REBIRTH_MASSAGE_ME);
		PacketSendUtility.sendPacket(player, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_INSTANT_DUNGEON_RESURRECT, 0, 0));
        return TeleportService2.teleportTo(player, mapId, instanceId, 235.97552f, 506.31268f, 948.6735f, (byte) 60);
    }
	
	@Override
	public boolean onDie(final Player player, Creature lastAttacker) {
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.DIE, 0, player.equals(lastAttacker) ? 0 : lastAttacker.getObjectId()), true);
		PacketSendUtility.sendPacket(player, new SM_DIE(player.haveSelfRezEffect(), player.haveSelfRezItem(), 0, 8));
		return true;
	}
}