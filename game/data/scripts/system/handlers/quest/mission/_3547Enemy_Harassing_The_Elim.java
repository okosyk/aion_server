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
package quest.mission;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

/****/
/** Author Rinzler (Encom)
/****/

public class _3547Enemy_Harassing_The_Elim extends QuestHandler
{
    private final static int questId = 3547;
	
    public _3547Enemy_Harassing_The_Elim() {
        super(questId);
    }
	
    @Override
    public void register() {
        qe.registerQuestNpc(730024).addOnQuestStart(questId);
        qe.registerQuestNpc(730024).addOnTalkEvent(questId);
        qe.registerQuestNpc(204647).addOnTalkEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 730024) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 1011);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } if (qs == null) {
            return false;
        } if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (targetId == 204647) {
                switch (dialog) {
                    case START_DIALOG:
                        if (var == 0) {
                            return sendQuestDialog(env, 1352);
                        } else if (var == 1) {
                            return sendQuestDialog(env, 2375);
                        }
                    case STEP_TO_1: {
                        return defaultCloseDialog(env, 0, 1);
                    } case CHECK_COLLECTED_ITEMS_SIMPLE: {
                        if (QuestService.collectItemCheck(env, true)) {
                            changeQuestStep(env, 1, 1, true);
                            return sendQuestDialog(env, 5);
                        } else {
                            return closeDialogWindow(env);
                        }
                    }
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 204647) {
				return sendQuestEndDialog(env);
			}
		}
        return false;
    }
}