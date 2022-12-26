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
package quest.iluma;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _15522Scout_The_Azurelight_Forest extends QuestHandler
{
    private final static int questId = 15522;
	
    public _15522Scout_The_Azurelight_Forest() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(806096).addOnQuestStart(questId);
        qe.registerQuestNpc(806096).addOnTalkEvent(questId);
		qe.registerQuestNpc(241589).addOnKillEvent(questId);
		qe.registerQuestNpc(241590).addOnKillEvent(questId);
		qe.registerQuestNpc(241591).addOnKillEvent(questId);
		qe.registerQuestNpc(241592).addOnKillEvent(questId);
		qe.registerQuestNpc(241593).addOnKillEvent(questId);
		qe.registerQuestNpc(241594).addOnKillEvent(questId);
		qe.registerQuestNpc(241595).addOnKillEvent(questId);
		qe.registerQuestNpc(241596).addOnKillEvent(questId);
		qe.registerQuestNpc(241597).addOnKillEvent(questId);
		qe.registerQuestNpc(243264).addOnKillEvent(questId);
		qe.registerQuestNpc(243265).addOnKillEvent(questId);
		qe.registerQuestNpc(242147).addOnKillEvent(questId);
		qe.registerQuestNpc(242151).addOnKillEvent(questId);
		qe.registerQuestNpc(242155).addOnKillEvent(questId);
		qe.registerQuestNpc(242159).addOnKillEvent(questId);
		qe.registerQuestNpc(242163).addOnKillEvent(questId);
		qe.registerQuestNpc(242167).addOnKillEvent(questId);
		qe.registerQuestNpc(242171).addOnKillEvent(questId);
		qe.registerQuestNpc(242175).addOnKillEvent(questId);
		qe.registerQuestNpc(242179).addOnKillEvent(questId);
		qe.registerQuestNpc(242183).addOnKillEvent(questId);
		qe.registerQuestNpc(242187).addOnKillEvent(questId);
		qe.registerQuestNpc(242191).addOnKillEvent(questId);
		qe.registerQuestNpc(242195).addOnKillEvent(questId);
		qe.registerQuestNpc(242199).addOnKillEvent(questId);
		qe.registerQuestNpc(242203).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 806096) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806096) {
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
            if (targetId == 806096) {
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
				case 241589:
				case 241590:
				case 241591:
				case 241592:
				case 241593:
				case 241594:
				case 241595:
				case 241596:
				case 241597:
				case 243264:
				case 243265:
				case 242147:
				case 242151:
				case 242155:
				case 242159:
				case 242163:
				case 242167:
				case 242171:
				case 242175:
				case 242179:
				case 242183:
				case 242187:
				case 242191:
				case 242195:
				case 242199:
				case 242203:
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