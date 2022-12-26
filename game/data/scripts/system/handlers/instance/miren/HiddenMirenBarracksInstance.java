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
package instance.miren;

import java.util.*;
import java.util.concurrent.Future;

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.instance.handlers.GeneralInstanceHandler;
import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.drop.DropItem;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.StaticDoor;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.items.storage.Storage;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.services.player.PlayerReviveService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.services.drop.DropRegistrationService;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.knownlist.Visitor;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Rinzler (Encom)
/****/

@InstanceID(301250000)
public class HiddenMirenBarracksInstance extends GeneralInstanceHandler
{
	private boolean isStartTimer1 = false;
	private boolean isStartTimer2 = false;
	private boolean isStartTimer3 = false;
	private boolean isStartTimer4 = false;
	private boolean isStartTimer5 = false;
	private boolean isStartTimer6 = false;
	private boolean isStartTimer7 = false;
	private boolean isStartTimer8 = false;
	private boolean isStartTimer9 = false;
	private boolean isStartTimer10 = false;
	private boolean isStartTimer11 = false;
	private boolean isStartTimer12 = false;
	private Map<Integer, StaticDoor> doors;
	private Future<?> chestHMIBarracksTask;
	private List<Npc> HMIBarracksChest = new ArrayList<Npc>();
	
	public void onDropRegistered(Npc npc) {
		Set<DropItem> dropItems = DropRegistrationService.getInstance().getCurrentDropMap().get(npc.getObjectId());
		int npcId = npc.getNpcId();
		int index = dropItems.size() + 1;
		switch (npcId) {
			case 233713: //Treasurer Karpasha.
			case 233714: //Treasurer Pasikar.
			case 233715: //Treasurer Subatita.
			case 233716: //Treasurer Mahirwa.
				switch (Rnd.get(1, 3)) {
					case 1:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 185000066, 1)); //Miren Armory Key.
					break;
					case 2:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 185000067, 1)); //Miren Supply Base Key.
					break;
					case 3:
				        dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 185000068, 1)); //Miren Operations Room Key.
					break;
				}
			break;
			case 233718: //Weakened Miren Prince.
				for (Player player: instance.getPlayersInside()) {
				    if (player.isOnline()) {
						dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 185000070, 1)); //Miren Gold Room Key.
						dropItems.add(DropRegistrationService.getInstance().regDropItem(index++, player.getObjectId(), npcId, 188053619, 1)); //Ancient Manastone Bundle.
					} switch (Rnd.get(1, 2)) {
						case 1:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 188053623, 1)); //Fire Dragon King's Weapon Bundle [Mythic].
						break;
						case 2:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 188054244, 1)); //Dreaming Nether Water Dragon King's Weapon Chest [Mythic].
						break;
					}
				}
			break;
			case 233719: //Enraged Miren Prince.
				for (Player player: instance.getPlayersInside()) {
				    if (player.isOnline()) {
						dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 185000070, 1)); //Miren Gold Room Key.
					} switch (Rnd.get(1, 2)) {
						case 1:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 188053623, 1)); //Fire Dragon King's Weapon Bundle [Mythic].
						break;
						case 2:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 188054244, 1)); //Dreaming Nether Water Dragon King's Weapon Chest [Mythic].
						break;
					} switch (Rnd.get(1, 2)) {
						case 1:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 122001746, 1)); //Ereshkigal Legion Red Beryl Ring.
						break;
						case 2:
						    dropItems.add(DropRegistrationService.getInstance().regDropItem(1, 0, npcId, 122001747, 1)); //Ereshkigal Legion Turquoise Ring.
						break;
					}
				}
			break;
		}
	}
	
	@Override
    public void onInstanceCreate(WorldMapInstance instance) {
        super.onInstanceCreate(instance);
        doors = instance.getDoors();
		switch (Rnd.get(1, 4)) {
			case 1:
				spawn(233713, 527.769f, 212.12146f, 178.46744f, (byte) 90); //Treasurer Karpasha.
			break;
			case 2:
				spawn(233714, 527.769f, 212.12146f, 178.46744f, (byte) 90); //Treasurer Pasikar.
			break;
			case 3:
				spawn(233715, 527.769f, 212.12146f, 178.46744f, (byte) 90); //Treasurer Subatita.
			break;
			case 4:
				spawn(233716, 527.769f, 212.12146f, 178.46744f, (byte) 90); //Treasurer Mahirwa.
			break;
		} switch (Rnd.get(1, 2)) {
			case 1:
				spawn(233718, 526.6656f, 845.7792f, 199.44875f, (byte) 90); //Weakened Miren Prince.
			break;
			case 2:
				spawn(233719, 526.6656f, 845.7792f, 199.44875f, (byte) 90); //Enraged Miren Prince.
			break;
		}
    }
	
	@Override
	public void onDie(Npc npc) {
		Player player = npc.getAggroList().getMostPlayerDamage();
		switch (npc.getObjectTemplate().getTemplateId()) {
			case 237318: //Ereshkigal Sheetar Keeper.
			    //A heavy door has opened somewhere.
				sendMsgByRace(1401839, Race.PC_ALL, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
				    public void run() {
						deleteNpc(731580);
				    }
			    }, 5000);
			break;
			case 237320: //Ereshkigal Sheetar Cryoslinger.
			    //A heavy door has opened somewhere.
				sendMsgByRace(1401839, Race.PC_ALL, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
				    public void run() {
						deleteNpc(700545);
				    }
			    }, 5000);
			break;
			case 233711: //Ranx Quaestor.
			    //A heavy door has opened somewhere.
				sendMsgByRace(1401839, Race.PC_ALL, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
				    public void run() {
						deleteNpc(700546);
				    }
			    }, 5000);
			break;
			case 233717: //Ebonlord Vershinak.
			    //A heavy door has opened somewhere.
				sendMsgByRace(1401839, Race.PC_ALL, 5000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
				    @Override
				    public void run() {
						deleteNpc(700547);
				    }
			    }, 5000);
			break;
			case 233718: //Weakened Miren Prince.
			case 233719: //Enraged Miren Prince.
				doors.get(2).setOpen(true);
				doors.get(3).setOpen(true);
				doors.get(5).setOpen(true);
				doors.get(6).setOpen(true);
				doors.get(17).setOpen(true);
				doors.get(18).setOpen(true);
				doors.get(28).setOpen(true);
				doors.get(74).setOpen(true);
				doors.get(76).setOpen(true);
				doors.get(79).setOpen(true);
				doors.get(80).setOpen(true);
				chestHMIBarracksTask.cancel(true);
				sendMsg("[Congratulation]: you finish <Hidden Miren Barracks>");
				instance.doOnAllPlayers(new Visitor<Player>() {
			        @Override
			        public void visit(Player player) {
				        if (player.isOnline()) {
						    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
					    }
				    }
			    });
			break;
		}
	}
	
	@Override
	public void onEnterInstance(final Player player) {
		super.onInstanceCreate(instance);
		if (!isStartTimer1) {
			isStartTimer1 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
						PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			HMIBarracksChest.add((Npc) spawn(702296, 478.56662f, 815.6565f, 199.76048f, (byte) 70));
			HMIBarracksChest.add((Npc) spawn(702296, 471.32745f, 834.5498f, 199.76048f, (byte) 63));
			HMIBarracksChest.add((Npc) spawn(702296, 470.52844f, 854.9471f, 199.76048f, (byte) 56));
			HMIBarracksChest.add((Npc) spawn(702297, 477.76843f, 873.94354f, 199.76036f, (byte) 50));
			HMIBarracksChest.add((Npc) spawn(702297, 490.90323f, 889.6053f, 199.76036f, (byte) 43));
			HMIBarracksChest.add((Npc) spawn(702297, 508.64328f, 899.91547f, 199.76036f, (byte) 36));
			HMIBarracksChest.add((Npc) spawn(702298, 528.42053f, 903.5909f, 199.76036f, (byte) 29));
			HMIBarracksChest.add((Npc) spawn(702298, 548.2363f, 900.31604f, 199.76036f, (byte) 23));
			HMIBarracksChest.add((Npc) spawn(702298, 565.53644f, 890.173f, 199.76036f, (byte) 16));
			HMIBarracksChest.add((Npc) spawn(702299, 578.9111f,  874.7958f, 199.76036f, (byte) 9));
			HMIBarracksChest.add((Npc) spawn(702299, 585.83545f, 855.7736f, 199.76036f, (byte) 3));
			HMIBarracksChest.add((Npc) spawn(702299, 586.7527f, 835.4556f, 199.76036f, (byte) 116));
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer2();
					sendMsg(1400245);
					HMIBarracksChest.get(0).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer2() {
        if (!isStartTimer2) {
			isStartTimer2 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer3();
					sendMsg(1400245);
					HMIBarracksChest.get(1).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer3() {
	    if (!isStartTimer3) {
			isStartTimer3 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer4();
					sendMsg(1400245);
					HMIBarracksChest.get(2).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer4() {
	    if (!isStartTimer4) {
			isStartTimer4 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer5();
					sendMsg(1400245);
					HMIBarracksChest.get(3).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer5() {
	    if (!isStartTimer5) {
			isStartTimer5 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer6();
					sendMsg(1400245);
					HMIBarracksChest.get(4).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer6() {
	    if (!isStartTimer6) {
			isStartTimer6 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer7();
					sendMsg(1400245);
					HMIBarracksChest.get(5).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer7() {
	    if (!isStartTimer7) {
			isStartTimer7 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer8();
					sendMsg(1400245);
					HMIBarracksChest.get(6).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer8() {
	    if (!isStartTimer8) {
			isStartTimer8 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer9();
					sendMsg(1400245);
					HMIBarracksChest.get(7).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer9() {
	    if (!isStartTimer9) {
			isStartTimer9 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer10();
					sendMsg(1400245);
					HMIBarracksChest.get(8).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer10() {
	    if (!isStartTimer10) {
			isStartTimer10 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer11();
					sendMsg(1400245);
					HMIBarracksChest.get(9).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer11() {
	    if (!isStartTimer11) {
			isStartTimer11 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					StartTimer12();
					sendMsg(1400245);
					HMIBarracksChest.get(10).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	private void StartTimer12() {
	    if (!isStartTimer12) {
			isStartTimer12 = true;
			System.currentTimeMillis();
			instance.doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    if (player.isOnline()) {
					    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 300));
					}
				}
			});
			chestHMIBarracksTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					sendMsg(1400244);
					sendMsg(1400245);
					HMIBarracksChest.get(11).getController().onDelete();
				}
			}, 300000);
		}
	}
	
	@Override
	public void onLeaveInstance(Player player) {
		removeItems(player);
	}
	
	@Override
	public void onPlayerLogOut(Player player) {
		removeItems(player);
	}
	
	private void removeItems(Player player) {
		Storage storage = player.getInventory();
		storage.decreaseByItemId(185000066, storage.getItemCountByItemId(185000066));
		storage.decreaseByItemId(185000067, storage.getItemCountByItemId(185000067));
		storage.decreaseByItemId(185000068, storage.getItemCountByItemId(185000068));
		storage.decreaseByItemId(185000069, storage.getItemCountByItemId(185000069));
		storage.decreaseByItemId(185000070, storage.getItemCountByItemId(185000070));
	}
	
	private void sendMsg(final String str) {
		instance.doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendMessage(player, str);
			}
		});
	}
	
	protected void sendMsgByRace(final int msg, final Race race, int time) {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				instance.doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						if (player.getRace().equals(race) || race.equals(Race.PC_ALL)) {
							PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(msg));
						}
					}
				});
			}
		}, time);
	}
	
	private void deleteNpc(int npcId) {
		if (getNpc(npcId) != null) {
			getNpc(npcId).getController().onDelete();
		}
	}
	
	@Override
    public boolean onReviveEvent(Player player) {
		player.getGameStats().updateStatsAndSpeedVisually();
		PlayerReviveService.revive(player, 100, 100, false, 0);
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_REBIRTH_MASSAGE_ME);
		PacketSendUtility.sendPacket(player, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_INSTANT_DUNGEON_RESURRECT, 0, 0));
        return TeleportService2.teleportTo(player, mapId, instanceId, 527.334f, 122.056f, 175.954f, (byte) 0);
    }
	
	@Override
	public boolean onDie(final Player player, Creature lastAttacker) {
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.DIE, 0, player.equals(lastAttacker) ? 0 : lastAttacker.getObjectId()), true);
		PacketSendUtility.sendPacket(player, new SM_DIE(player.haveSelfRezEffect(), player.haveSelfRezItem(), 0, 8));
		return true;
	}
}