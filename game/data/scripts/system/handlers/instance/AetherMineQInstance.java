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
package instance;

import java.util.*;
import javolution.util.FastMap;

import com.aionemu.gameserver.instance.handlers.GeneralInstanceHandler;
import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.services.player.PlayerReviveService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;

/****/
/** Author Rinzler (Encom)
/****/

@InstanceID(301690000)
public class AetherMineQInstance extends GeneralInstanceHandler
{
	private Race spawnRace;
	private FastMap<Integer, VisibleObject> objects = new FastMap<Integer, VisibleObject>();
	
	@Override
	public void onEnterInstance(Player player) {
		switch (player.getRace()) {
			case ELYOS:
				spawnIDLF6_Q_Advance_Teleporter_Q10529A();
			break;
			case ASMODIANS:
				spawnIDLF6_Q_Advance_Teleporter_Q20529A();
			break;
		} if (spawnRace == null) {
			spawnRace = player.getRace();
			SpawnIDLF6QRace();
		}
	}
	
	@Override
	public void onDie(Npc npc) {
		Player player = npc.getAggroList().getMostPlayerDamage();
		switch (npc.getObjectTemplate().getTemplateId()) {
			case 244111: //LF6_Mission_Dark_Fi_75_An.
			case 244112: //LF6_Mission_Dark_Wi_75_An.
			case 244113: //LF6_Mission_Dark_Fi_Named_75_An.
			case 244127: //DF6_Mission_Light_Fi_75_An.
			case 244128: //DF6_Mission_Light_Wi_75_An.
			case 244129: //DF6_Mission_Light_Fi_Named_75_An.
			    despawnNpc(npc);
			break;
		}
	}
	
	private void SpawnIDLF6QRace() {
		final int npc1 = spawnRace == Race.ASMODIANS ? 806298 : 806293;
		final int fi75An = spawnRace == Race.ASMODIANS ? 244127 : 244111;
		final int wi75An = spawnRace == Race.ASMODIANS ? 244128 : 244112;
		//Npc
		spawn(npc1, 320.69873f, 260.07156f, 256.3936f, (byte) 85);
		//Mission_Fi_75_An.
		spawn(fi75An, 179.0f, 168.0f, 228.18404f, (byte) 8);
        spawn(fi75An, 172.0f, 149.0f, 230.51636f, (byte) 43);
        spawn(fi75An, 322.17755f, 222.98203f, 252.07573f, (byte) 28);
        spawn(fi75An, 303.89597f, 217.86458f, 246.82591f, (byte) 17);
        spawn(fi75An, 287.44846f, 217.56142f, 244.23021f, (byte) 7);
        spawn(fi75An, 258.6159f, 195.05444f, 235.32248f, (byte) 12);
        spawn(fi75An, 226.57643f, 179.74837f, 231.16173f, (byte) 11);
        spawn(fi75An, 206.74022f, 178.92027f, 229.21239f, (byte) 5);
		//Mission_Wi_75_An.
		spawn(wi75An, 187.0f, 152.0f, 228.18611f, (byte) 10);
        spawn(wi75An, 310.9536f, 237.79036f, 252.14915f, (byte) 25);
        spawn(wi75An, 290.05392f, 202.05699f, 243.7684f, (byte) 15);
        spawn(wi75An, 197.0f, 167.0f, 228.58382f, (byte) 15);
        spawn(wi75An, 214.0f, 168.0f, 229.42783f, (byte) 10);
        spawn(wi75An, 337.95862f, 238.1435f, 255.98781f, (byte) 39);
        spawn(wi75An, 277.2896f, 202.203f, 241.40765f, (byte) 11);
        spawn(wi75An, 168.0f, 158.0f, 230.86432f, (byte) 97);
    }
	
	private void despawnNpc(Npc npc) {
		if (npc != null) {
			npc.getController().onDelete();
		}
	}
	
	private void spawnIDLF6_Q_Advance_Teleporter_Q10529A() {
		SpawnTemplate advanceTeleporter1 = SpawnEngine.addNewSingleTimeSpawn(301690000, 703317, 163.97794f, 150.90314f, 231.73216f, (byte) 0);
		advanceTeleporter1.setEntityId(39);
		objects.put(703317, SpawnEngine.spawnObject(advanceTeleporter1, instanceId));
	}
	private void spawnIDLF6_Q_Advance_Teleporter_Q20529A() {
		SpawnTemplate advanceTeleporter2 = SpawnEngine.addNewSingleTimeSpawn(301690000, 703325, 163.97794f, 150.90314f, 231.73216f, (byte) 0);
		advanceTeleporter2.setEntityId(39);
		objects.put(703325, SpawnEngine.spawnObject(advanceTeleporter2, instanceId));
	}
	
	@Override
    public boolean onReviveEvent(Player player) {
		player.getGameStats().updateStatsAndSpeedVisually();
		PlayerReviveService.revive(player, 100, 100, false, 0);
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_REBIRTH_MASSAGE_ME);
		PacketSendUtility.sendPacket(player, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_INSTANT_DUNGEON_RESURRECT, 0, 0));
        return TeleportService2.teleportTo(player, mapId, instanceId, 323.85403f, 267.03787f, 259.2078f, (byte) 91);
    }
	
	@Override
	public boolean onDie(final Player player, Creature lastAttacker) {
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.DIE, 0, player.equals(lastAttacker) ? 0 : lastAttacker.getObjectId()), true);
		PacketSendUtility.sendPacket(player, new SM_DIE(player.haveSelfRezEffect(), player.haveSelfRezItem(), 0, 8));
		return true;
	}
}