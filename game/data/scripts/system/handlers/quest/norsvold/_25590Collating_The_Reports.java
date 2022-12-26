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
package quest.norsvold;

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _25590Collating_The_Reports extends QuestHandler
{
	private final static int questId = 25590;
	
	public _25590Collating_The_Reports() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(806114).addOnQuestStart(questId); //Reinhard.
		qe.registerQuestNpc(806114).addOnTalkEvent(questId); //Reinhard.
		qe.registerQuestNpc(806228).addOnTalkEvent(questId); //Bastok.
		qe.registerQuestNpc(806229).addOnTalkEvent(questId); //Duisys.
		qe.registerQuestNpc(806230).addOnTalkEvent(questId); //Sieden.
		qe.registerQuestNpc(806231).addOnTalkEvent(questId); //Norte.
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		final Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc) {
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		} if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			if (targetId == 806114) { //Ilisia.
				if (env.getDialog() == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 4762);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		} if (qs == null) {
			return false;
		} if (qs.getStatus() == QuestStatus.START) {
			switch (targetId) {
				case 806228: { //Bastok.
					switch (env.getDialog()) {
						case START_DIALOG: {
							return sendQuestDialog(env, 1011);
						} case STEP_TO_1: {
							giveQuestItem(env, 182215982, 1);
							return defaultCloseDialog(env, 0, 1);
						}
					}
				} case 806229: { //Duisys.
				    switch (env.getDialog()) {
						case START_DIALOG: {
							return sendQuestDialog(env, 1352);
						} case STEP_TO_2: {
							giveQuestItem(env, 182215983, 1);
							return defaultCloseDialog(env, 1, 2);
						}
					}
				} case 806230: { //Sieden.
				    switch (env.getDialog()) {
						case START_DIALOG: {
							return sendQuestDialog(env, 1693);
						} case STEP_TO_3: {
							giveQuestItem(env, 182215984, 1);
							return defaultCloseDialog(env, 2, 3);
						}
					}
				} case 806231: { //Norte.
				    switch (env.getDialog()) {
						case START_DIALOG: {
							return sendQuestDialog(env, 2034);
						} case SET_REWARD: {
							giveQuestItem(env, 182215985, 1);
							qs.setQuestVar(4);
							updateQuestStatus(env);
							return defaultCloseDialog(env, 4, 4, true, false);
						}
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 806116) { //Reinhard.
				if (env.getDialog() == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 10002);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		}
		return false;
	}
}