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
import javolution.util.FastList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.ai2.manager.*;
import com.aionemu.gameserver.instance.handlers.*;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.drop.DropItem;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.InstanceScoreType;
import com.aionemu.gameserver.model.instance.instancereward.*;
import com.aionemu.gameserver.model.instance.playerreward.*;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.services.drop.DropRegistrationService;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Rinzler (Encom)
/****/

@InstanceID(301500000)
public class StonespearReachInstance extends GeneralInstanceHandler
{
	private Race spawnRace;
	private long instanceTime;
	private Future<?> instanceTimer;
	private boolean isInstanceDestroyed;
	private StonespearReachReward instanceReward;
	private final FastList<Future<?>> stonespearTask = FastList.newInstance();
	
	protected StonespearReachPlayerReward getPlayerReward(Player player) {
		Integer object = player.getObjectId();
		if (instanceReward.getPlayerReward(object) == null) {
			addPlayerToReward(player);
		}
		return (StonespearReachPlayerReward) instanceReward.getPlayerReward(object);
	}
	
	private void addPlayerToReward(Player player) {
		instanceReward.addPlayerReward(new StonespearReachPlayerReward(player.getObjectId()));
	}
	
	private boolean containPlayer(Integer object) {
		return instanceReward.containPlayer(object);
	}
	
	public void onDropRegistered(Npc npc) {
		Set<DropItem> dropItems = DropRegistrationService.getInstance().getCurrentDropMap().get(npc.getObjectId());
		int npcId = npc.getNpcId();
		switch (npcId) {
		}
	}
	
	private void SpawnRaceInstance() {
		final int territoryManager = spawnRace == Race.ASMODIANS ? 833489 : 833488; //Legion Territory Manager.
		spawn(territoryManager, 165.91524f, 264.50375f, 97.454155f, (byte) 0);
    }
	
	protected void startInstanceTask() {
    	instanceTime = System.currentTimeMillis();
        instanceReward.setInstanceStartTime();
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
				spawn(855833, 251.47273f, 264.46713f, 96.30522f, (byte) 61);
				spawn(855833, 230.85971f, 285.67032f, 96.418526f, (byte) 90);
				spawn(855833, 211.20746f, 264.05276f, 96.53291f, (byte) 0);
				spawn(855833, 231.29951f, 243.66095f, 96.36497f, (byte) 29);
            }
        }, 90000));
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
            }
        }, 220000));
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
            }
        }, 400000));
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
            }
        }, 600000));
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
            }
        }, 900000));
		stonespearTask.add(ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                instance.doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						stopInstance(player);
					}
				});
            }
        }, 1800000));
    }
	
	@Override
	public void onDie(Npc npc) {
		int points = 0;
		int npcId = npc.getNpcId();
		Player player = npc.getAggroList().getMostPlayerDamage();
		switch (npc.getObjectTemplate().getTemplateId()) {
		    case 855833:
				points = 2000;
				despawnNpc(npc);
			break;
		} if (instanceReward.getInstanceScoreType().isStartProgress()) {
			instanceReward.addNpcKill();
			instanceReward.addPoints(points);
			sendPacket(npc.getObjectTemplate().getNameId(), points);
		}
	}
	
	private int getTime() {
        long result = System.currentTimeMillis() - instanceTime;
        if (result < 90000) {
            return (int) (90000 - result);
        } else if (result < 1800000) { //30-Mins
            return (int) (1800000 - (result - 90000));
        }
        return 0;
    }
	
	private void sendPacket(final int nameId, final int point) {
		instance.doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (nameId != 0) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400237, new DescriptionId(nameId * 2 + 1), point));
				}
				PacketSendUtility.sendPacket(player, new SM_INSTANCE_SCORE(getTime(), instanceReward, null));
			}
		});
	}
	
	private int checkRank(int totalPoints) {
		int rank = 0;
		if (totalPoints > 71600) { //Rank S.
			rank = 1;
		} else if (totalPoints > 41000) { //Rank A.
			rank = 2;
		} else if (totalPoints > 26000) { //Rank B.
			rank = 3;
		} else if (totalPoints > 14000) { //Rank C.
			rank = 4;
		} else if (totalPoints > 8800) { //Rank D.
			rank = 5;
		} else if (totalPoints > 0) { //Rank F.
			rank = 6;
		} else {
			rank = 8;
		}
		return rank;
	}
	
	@Override
	public void onEnterInstance(final Player player) {
		if (!containPlayer(player.getObjectId())) {
			addPlayerToReward(player);
		} if (instanceTimer == null) {
			instanceTime = System.currentTimeMillis();
			instanceTimer = ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					deleteNpc(833284);
					//The member recruitment window has passed. You cannot recruit any more members.
					sendMsgByRace(1401181, Race.PC_ALL, 5000);
					instanceReward.setInstanceScoreType(InstanceScoreType.START_PROGRESS);
					sendPacket(0, 0);
				}
			}, 90000);
		} if (spawnRace == null) {
			spawnRace = player.getRace();
			SpawnRaceInstance();
		}
		sendPacket(0, 0);
	}
	
	protected void stopInstance(Player player) {
		stopInstanceTask();
		instanceReward.setRank(checkRank(instanceReward.getPoints()));
		instanceReward.setInstanceScoreType(InstanceScoreType.END_PROGRESS);
		doReward(player);
		sendMsg("[Congratulation]: you finish <Stonespear Reach 4.8>");
		sendPacket(0, 0);
	}
	
	@Override
	public void doReward(Player player) {
	}
	
	private void moveToForward(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	private void startAssault1() {
		moveToForward((Npc)spawn(0, 0f, 0f, 0f, (byte) 0), 0f, 0f, 0f, false);
	}
	
	private void stopInstanceTask() {
        for (FastList.Node<Future<?>> n = stonespearTask.head(), end = stonespearTask.tail(); (n = n.getNext()) != end; ) {
            if (n.getValue() != null) {
                n.getValue().cancel(true);
            }
        }
    }
	
	@Override
	public void onInstanceCreate(WorldMapInstance instance) {
		super.onInstanceCreate(instance);
		instanceReward = new StonespearReachReward(mapId, instanceId);
		instanceReward.setInstanceScoreType(InstanceScoreType.PREPARING);
		startInstanceTask();
	}
	
	@Override
	public void onInstanceDestroy() {
		if (instanceTimer != null) {
			instanceTimer.cancel(false);
		}
		isInstanceDestroyed = true;
	}
	
	private void despawnNpc(Npc npc) {
		if (npc != null) {
			npc.getController().onDelete();
		}
	}
	
	private void deleteNpc(int npcId) {
		if (getNpc(npcId) != null) {
			getNpc(npcId).getController().onDelete();
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
	
	@Override
	public boolean onDie(final Player player, Creature lastAttacker) {
		PacketSendUtility.broadcastPacket(player, new SM_EMOTION(player, EmotionType.DIE, 0, player.equals(lastAttacker) ? 0 : lastAttacker.getObjectId()), true);
		PacketSendUtility.sendPacket(player, new SM_DIE(player.haveSelfRezEffect(), player.haveSelfRezItem(), 0, 8));
		return true;
	}
}