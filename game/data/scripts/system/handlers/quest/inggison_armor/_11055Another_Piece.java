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
package quest.inggison_armor;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

public class _11055Another_Piece extends QuestHandler
{
	private final static int questId = 11055;
	
	public _11055Another_Piece() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(798990).addOnQuestStart(questId); //Titus
		qe.registerQuestNpc(798990).addOnTalkEvent(questId); //Titus
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			if (targetId == 798990) { //Titus
				switch (dialog) {
					case START_DIALOG:
						return sendQuestDialog(env, 1011);
					case SELECT_ACTION_1012: {
						return sendQuestDialog(env, 1012);
					} case ASK_ACCEPTION: {
						return sendQuestDialog(env, 4);
					} case ACCEPT_QUEST: {
						return sendQuestStartDialog(env);
					} case REFUSE_QUEST: {
						return sendQuestDialog(env, 1004);
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 798990) { //Titus
				switch (dialog) {
					case START_DIALOG: {
						return sendQuestDialog(env, 2375);
					} default: {
						return sendQuestStartDialog(env);
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 798990) { //Titus
				return sendQuestEndDialog(env);
			}
		}
		return false;
	}
}