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
package ai.instance.empyreanCrucible;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import ai.AggressiveNpcAI2;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.actions.PlayerActions;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("rm_1337")
public class RM1337AI2 extends AggressiveNpcAI2
{
	private Future<?> phaseTask;
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	private AtomicBoolean isStartedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			sendMsg(1500229);
		}
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 95) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 75) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 55) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 35) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		} if (hpPercentage <= 15) {
			if (isStartedEvent.compareAndSet(false, true)) {
				startPhaseTask();
			}
		}
	}
	
	private void startPhaseTask() {
		phaseTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isAlreadyDead()) {
					cancelPhaseTask();
				} else {
					getOwner().getController().cancelCurrentSkill();
					NpcShoutsService.getInstance().sendMsg(getOwner(), 1500230, getObjectId(), 0, 0);
					SkillEngine.getInstance().getSkill(getOwner(), 19551, 10, getOwner()).useNoAnimationSkill();
					List<Player> players = getLifedPlayers();
					if (!players.isEmpty()) {
						int size = players.size();
						if (players.size() < 6) {
							for (Player p: players) {
								spawnSparks(p);
							}
						} else {
							int count = Rnd.get(1, size);
							for (int i = 0; i < count; i++) {
								if (players.isEmpty()) {
									break;
								}
								spawnSparks(players.get(Rnd.get(players.size())));
							}
						}
					}
				}
			}
		}, 3000, 15000);
	}
	
	private void spawnSparks(Player player) {
		final float x = player.getX();
		final float y = player.getY();
		final float z = player.getZ();
		if (x > 0 && y > 0 && z > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					if (!isAlreadyDead()) {
						spawn(282373, x, y, z, (byte) 0); //Sparks.
					}
				}
			}, 3000);
		}
	}
	
	private List<Player> getLifedPlayers() {
		List<Player> players = new ArrayList<Player>();
		for (Player player: getKnownList().getKnownPlayers().values()) {
			if (!PlayerActions.isAlreadyDead(player)) {
				players.add(player);
			}
		}
		return players;
	}
	
	private void sendMsg(int msg) {
		NpcShoutsService.getInstance().sendMsg(getOwner(), msg, getObjectId(), false, 0, 0);
	}
	
	@Override
	protected void handleDespawned() {
		cancelPhaseTask();
		super.handleDespawned();
	}
	
	@Override
	public void handleDied() {
		cancelPhaseTask();
		sendMsg(1500231);
		super.handleDied();
	}
	
	@Override
	protected void handleBackHome() {
		cancelPhaseTask();
		isStartedEvent.set(false);
		isAggred.set(false);
		super.handleBackHome();
	}
	
	private void cancelPhaseTask() {
		if (phaseTask != null && !phaseTask.isDone()) {
			phaseTask.cancel(true);
		}
	}
}