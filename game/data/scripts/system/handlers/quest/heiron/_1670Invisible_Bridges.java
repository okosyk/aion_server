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
package quest.heiron;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.HandlerResult;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Rinzler (Encom)
/****/

public class _1670Invisible_Bridges extends QuestHandler
{
	private final static int questId = 1670;
	
	public _1670Invisible_Bridges() {
		super(questId);
	}
	
	public void register() {
		qe.registerQuestItem(182201770, questId);
		qe.registerQuestNpc(203837).addOnTalkEvent(questId);
		qe.registerOnEnterZone(ZoneName.get("LF3_SENSORYAREA_Q1670A_206048_1_210040000"), questId);
		qe.registerOnEnterZone(ZoneName.get("LF3_SENSORYAREA_Q1670B_206049_11_210040000"), questId);
		qe.registerOnEnterZone(ZoneName.get("LF3_SENSORYAREA_Q1670C_206050_2_210040000"), questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 0) { 
				if (dialog == QuestDialog.ACCEPT_QUEST) {
					QuestService.startQuest(env);
					return closeDialogWindow(env);
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 203837) {
				if (dialog == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 2375);
				} else if (dialog == QuestDialog.SELECT_REWARD) {
					return defaultCloseDialog(env, 0, 1, true, true);
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 203837) {
				if (dialog == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 1352);
				}
				removeQuestItem(env, 182201770, 1);
				return sendQuestEndDialog(env);
			}
		}
		return false;
	}
	
	@Override
	public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
		Player player = env.getPlayer();
		if (player == null)
			return false;
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
  		    int var = qs.getQuestVarById(0);
			int var1 = qs.getQuestVarById(1);
			int var2 = qs.getQuestVarById(2);
			int var3 = qs.getQuestVarById(3);
			if (var == 1) {
				if (zoneName == ZoneName.get("LF3_SENSORYAREA_Q1670A_206048_1_210040000")) {
					if (var1 == 0) {
						changeQuestStep(env, 0, 16, false);
						return true;
					}
				} else if (zoneName == ZoneName.get("LF3_SENSORYAREA_Q1670B_206049_11_210040000")) {
					if (var2 == 16) {
						changeQuestStep(env, 16, 48, false);
						return true;
					}
				} else if (zoneName == ZoneName.get("LF3_SENSORYAREA_Q1670C_206050_2_210040000")) {
					if (var3 == 48) {
						changeQuestStep(env, 48, 48, true);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public HandlerResult onItemUseEvent(QuestEnv env, Item item) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			return HandlerResult.fromBoolean(sendQuestDialog(env, 4));
		}
		return HandlerResult.FAILED;
	}
}