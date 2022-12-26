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
package quest.pernon;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/****/
/** Author Rinzler (Encom)
/****/

public class _28826Free_To_A_Good_Home extends QuestHandler
{
	private static final int questId = 28826;
	
	public _28826Free_To_A_Good_Home() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(830662).addOnQuestStart(questId); //Logirunerk.
		qe.registerQuestNpc(830663).addOnQuestStart(questId); //Davinrinerk.
		qe.registerQuestNpc(830662).addOnTalkEvent(questId); //Logirunerk.
		qe.registerQuestNpc(830663).addOnTalkEvent(questId); //Davinrinerk.
		qe.registerQuestNpc(730525).addOnTalkEvent(questId); //Vintage Grab Box.
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			switch (targetId) {
			    case 830662: //Logirunerk.
				case 830663: { //Davinrinerk.
				    switch (dialog) {
					    case START_DIALOG: {
						    return sendQuestDialog(env, 1011);
					    } default: {
						    return sendQuestStartDialog(env);
					    }
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 730525) { //Vintage Grab Box.
				switch (dialog) {
					case USE_OBJECT: {
						return sendQuestDialog(env, 2375);
					} case SELECT_REWARD: {
						changeQuestStep(env, 0, 0, true);
						return sendQuestDialog(env, 5);
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 730525) { //Vintage Grab Box.
				return sendQuestEndDialog(env);
			}
		}
		return false;
	}
}