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
package quest.esoterrace;

import java.util.Collections;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.quest.QuestItems;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.item.ItemService;

/****/
/** Author Rinzler (Encom)
/****/

public class _28409Make_The_Blade_Complete extends QuestHandler
{
	private final static int questId = 28409;

	public _28409Make_The_Blade_Complete() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(799558).addOnQuestStart(questId);
		qe.registerQuestNpc(799558).addOnTalkEvent(questId);
		qe.registerQuestNpc(799557).addOnTalkEvent(questId);
		qe.registerQuestNpc(205237).addOnTalkEvent(questId);
		qe.registerQuestNpc(215795).addOnKillEvent(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		int targetId = env.getTargetId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (targetId == 799558) {
			if (qs == null || qs.getStatus() == QuestStatus.NONE) {
				if (env.getDialog() == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 4762);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		} else if (targetId == 799557) {
			if (qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 0) {
				if (env.getDialog() == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 1011);
				} else if (env.getDialog() == QuestDialog.STEP_TO_1) {
					return defaultCloseDialog(env, 0, 1);
				} else {
					return sendQuestStartDialog(env);
				}
			} else if (qs != null && qs.getStatus() == QuestStatus.REWARD) {
				if (env.getDialog() == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 10002);
				} else if (env.getDialog() == QuestDialog.SELECT_REWARD) {
					return sendQuestDialog(env, 5);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		} else if (targetId == 205237) {
			if (qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 1) {
				if (env.getDialog() == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 1352);
				} else if (env.getDialog() == QuestDialog.STEP_TO_2) {
					return defaultCloseDialog(env, 1, 2, 182215007, 1, 182215006, 1);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onKillEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs == null || qs.getStatus() != QuestStatus.START)
		return false;
		int targetId = env.getTargetId();
		switch(targetId) {
			case 215795:
				if (qs.getQuestVarById(0) == 2) {
					ItemService.addQuestItems(player, Collections.singletonList(new QuestItems(182215008, 1)));
					player.getInventory().decreaseByItemId(182215007, 1);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);	
				}
			break;
		}
		return false;
	}
}