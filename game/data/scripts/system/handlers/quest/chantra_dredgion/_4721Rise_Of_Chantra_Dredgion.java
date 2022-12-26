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
package quest.chantra_dredgion;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _4721Rise_Of_Chantra_Dredgion extends QuestHandler
{
	private static final int questId = 4721;

	public _4721Rise_Of_Chantra_Dredgion() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(799226).addOnQuestStart(questId); //Valetta.
		qe.registerQuestNpc(799226).addOnTalkEvent(questId); //Valetta.
		qe.registerQuestNpc(799403).addOnTalkEvent(questId); //Yorgen.
		qe.registerQuestNpc(700948).addOnTalkEvent(questId); //Balaur Weapon.
		qe.registerQuestNpc(216886).addOnKillEvent(questId); //Captain Zanata.
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		int var = qs.getQuestVarById(0);
		if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 799226) { //Valetta.
				if (dialog == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 4762);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		}
		else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 799403) { //Yorgen.
				switch (dialog) {
					case START_DIALOG: {
						if (var == 0) {
							return sendQuestDialog(env, 1011);
						}
					} case STEP_TO_1: {
						return defaultCloseDialog(env, 0, 1);
					}
				}
			} else if (targetId == 700948) { //Balaur Weapon.
				if (dialog == QuestDialog.USE_OBJECT) {
					closeDialogWindow(env);
					return true;
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 799226) { //Valetta.
				if (dialog == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 10002);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onKillEvent(QuestEnv env) {
		return defaultOnKillEvent(env, 216886, 2, true); //Captain Zanata.
	}
}