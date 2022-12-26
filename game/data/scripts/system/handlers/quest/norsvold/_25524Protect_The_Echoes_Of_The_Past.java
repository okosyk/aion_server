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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _25524Protect_The_Echoes_Of_The_Past extends QuestHandler
{
    private final static int questId = 25524;
	
    public _25524Protect_The_Echoes_Of_The_Past() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(806109).addOnQuestStart(questId);
        qe.registerQuestNpc(806109).addOnTalkEvent(questId);
		qe.registerQuestNpc(241601).addOnKillEvent(questId);
		qe.registerQuestNpc(241602).addOnKillEvent(questId);
		qe.registerQuestNpc(241603).addOnKillEvent(questId);
		qe.registerQuestNpc(241604).addOnKillEvent(questId);
		qe.registerQuestNpc(241605).addOnKillEvent(questId);
		qe.registerQuestNpc(241606).addOnKillEvent(questId);
		qe.registerQuestNpc(242207).addOnKillEvent(questId);
		qe.registerQuestNpc(242211).addOnKillEvent(questId);
		qe.registerQuestNpc(242215).addOnKillEvent(questId);
		qe.registerQuestNpc(242219).addOnKillEvent(questId);
		qe.registerQuestNpc(242223).addOnKillEvent(questId);
		qe.registerQuestNpc(242227).addOnKillEvent(questId);
		qe.registerQuestNpc(242231).addOnKillEvent(questId);
		qe.registerQuestNpc(242235).addOnKillEvent(questId);
		qe.registerQuestNpc(242239).addOnKillEvent(questId);
		qe.registerQuestNpc(242243).addOnKillEvent(questId);
		qe.registerQuestNpc(242247).addOnKillEvent(questId);
		qe.registerQuestNpc(242251).addOnKillEvent(questId);
		qe.registerQuestNpc(242255).addOnKillEvent(questId);
		qe.registerQuestNpc(242259).addOnKillEvent(questId);
		qe.registerQuestNpc(242263).addOnKillEvent(questId);
		qe.registerQuestNpc(242267).addOnKillEvent(questId);
		qe.registerQuestNpc(242271).addOnKillEvent(questId);
		qe.registerQuestNpc(242275).addOnKillEvent(questId);
		qe.registerQuestNpc(242279).addOnKillEvent(questId);
		qe.registerQuestNpc(242283).addOnKillEvent(questId);
		qe.registerQuestNpc(243278).addOnKillEvent(questId);
		qe.registerQuestNpc(243279).addOnKillEvent(questId);
		qe.registerQuestNpc(243280).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 806109) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806109) {
                if (dialog == QuestDialog.START_DIALOG) {
                    if (qs.getQuestVarById(0) == 60) {
                        return sendQuestDialog(env, 2375);
                    }
                } if (dialog == QuestDialog.SELECT_REWARD) {
                    changeQuestStep(env, 60, 61, true);
                    return sendQuestEndDialog(env);
                }
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806109) {
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 10002);
				} else if (env.getDialog() == QuestDialog.SELECT_REWARD) {
					return sendQuestDialog(env, 5);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		}
        return false;
    }
	
    public boolean onKillEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            switch (env.getTargetId()) {
				case 241601:
				case 241602:
				case 241603:
				case 241604:
				case 241605:
				case 241606:
				case 242207:
				case 242211:
				case 242215:
				case 242219:
				case 242223:
				case 242227:
				case 242231:
				case 242235:
				case 242239:
				case 242243:
				case 242247:
				case 242251:
				case 242255:
				case 242259:
				case 242263:
				case 242267:
				case 242271:
				case 242275:
				case 242279:
				case 242283:
				case 243278:
				case 243279:
				case 243280:
                if (qs.getQuestVarById(1) < 60) {
					qs.setQuestVarById(1, qs.getQuestVarById(1) + 1);
					updateQuestStatus(env);
				} if (qs.getQuestVarById(1) >= 60) {
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
				}
            }
        }
        return false;
    }
}