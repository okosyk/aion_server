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
package quest.enshar;

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;
import com.aionemu.gameserver.services.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _25051Treasure_Of_Ancient_King extends QuestHandler
{
    private final static int questId = 25051;
	
    public _25051Treasure_Of_Ancient_King() {
        super(questId);
    }
	
    @Override
    public void register() {
        qe.registerQuestNpc(804915).addOnQuestStart(questId);
        qe.registerQuestNpc(804915).addOnTalkEvent(questId);
        qe.registerQuestNpc(731553).addOnTalkEvent(questId);
        qe.registerQuestNpc(805160).addOnTalkEvent(questId);
		qe.registerQuestNpc(731554).addOnTalkEvent(questId);
		qe.registerQuestNpc(731555).addOnTalkEvent(questId);
		qe.registerQuestNpc(804916).addOnTalkEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        int var = qs.getQuestVarById(0);
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 804915) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 731553) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    if (var == 0) {
                        return sendQuestDialog(env, 1011);
                    }
                } else if (env.getDialog() == QuestDialog.STEP_TO_1) {
					QuestService.addNewSpawn(220080000, player.getInstanceId(), 805160, 2046.8f, 1588.8f, 348.4f, (byte) 90);
					changeQuestStep(env, 0, 1, false);
                    return closeDialogWindow(env);
                }
            } if (targetId == 805160) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    if (var == 1) {
                        return sendQuestDialog(env, 1352);
                    }
                } else if (env.getDialog() == QuestDialog.STEP_TO_2) {
					Npc npc = (Npc) env.getVisibleObject();
                    npc.getController().onDelete();
					changeQuestStep(env, 1, 2, false);
                    return closeDialogWindow(env);
                }
            } if (targetId == 731554) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    if (var == 2) {
                        return sendQuestDialog(env, 1693);
                    }
                } else if (env.getDialog() == QuestDialog.STEP_TO_3) {
					giveQuestItem(env, 182215720, 1);
					changeQuestStep(env, 2, 3, false);
                    return closeDialogWindow(env);
                }
            } if (targetId == 731555) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    if (var == 3) {
                        return sendQuestDialog(env, 2034);
                    }
                } else if (env.getDialog() == QuestDialog.SET_REWARD) {
					removeQuestItem(env, 182215720, 1);
					QuestService.addNewSpawn(220080000, player.getInstanceId(), 220031, 1933.8f, 1418.8f, 359.6f, (byte) 37);
					changeQuestStep(env, 3, 4, true);
                    return closeDialogWindow(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 804916) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 2376);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
        return false;
    }
}