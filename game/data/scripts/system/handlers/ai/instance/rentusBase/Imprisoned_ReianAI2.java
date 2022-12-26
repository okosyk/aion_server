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
package ai.instance.rentusBase;

import ai.GeneralNpcAI2;

import java.util.concurrent.atomic.AtomicBoolean;

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.walker.WalkerTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;


/****/
/** Author Rinzler (Encom)
/****/

@AIName("imprisoned_reian")
public class Imprisoned_ReianAI2 extends GeneralNpcAI2
{
    private String walkerId;
	private WalkerTemplate template;
	private AtomicBoolean isSaved = new AtomicBoolean(false);
    private AtomicBoolean isAsked = new AtomicBoolean(false);
    
	@Override
	protected void handleSpawned() {
		walkerId = getSpawnTemplate().getWalkerId();
		getSpawnTemplate().setWalkerId(null);
		if (walkerId != null) {
			template = DataManager.WALKER_DATA.getWalkerTemplate(walkerId);
		}
		super.handleSpawned();
	}
	
	@Override
	protected void handleMoveArrived() {
		int point = getOwner().getMoveController().getCurrentPoint();
		super.handleMoveArrived();
		if (template.getRouteSteps().size() - 4 == point) {
			getSpawnTemplate().setWalkerId(null);
			WalkManager.stopWalking(this);
			AI2Actions.deleteOwner(this);
		}
	}
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (walkerId != null) {
			if (creature instanceof Player) {
				final Player player = (Player) creature;
				if (MathUtil.getDistance(getOwner(), player) <= 21) {
					if (isAsked.compareAndSet(false, true)) {
						switch (Rnd.get(1, 2)) {
							case 1:
								sendMsg(390563);
							break;
							case 2:
								sendMsg(390567);
							break;
						}
					}
				} if (MathUtil.getDistance(getOwner(), player) <= 6) {
					if (isSaved.compareAndSet(false, true)) {
						getSpawnTemplate().setWalkerId(walkerId);
						WalkManager.startWalking(this);
						getOwner().setState(1);
						PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
						switch (Rnd.get(1, 2)) {
							case 1:
								sendMsg(342410);
							break;
							case 2:
								sendMsg(342411);
							break;
						}
					}
				}
			}
		}
	}
	
	private void sendMsg(int msg) {
		NpcShoutsService.getInstance().sendMsg(getOwner(), msg, getObjectId(), 0, 0);
	}
}