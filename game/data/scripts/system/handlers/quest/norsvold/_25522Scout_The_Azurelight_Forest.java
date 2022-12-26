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

public class _25522Scout_The_Azurelight_Forest extends QuestHandler
{
    private final static int questId = 25522;
	
    public _25522Scout_The_Azurelight_Forest() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(806108).addOnQuestStart(questId);
        qe.registerQuestNpc(806108).addOnTalkEvent(questId);
		qe.registerQuestNpc(241755).addOnKillEvent(questId);
		qe.registerQuestNpc(241756).addOnKillEvent(questId);
		qe.registerQuestNpc(241757).addOnKillEvent(questId);
		qe.registerQuestNpc(241759).addOnKillEvent(questId);
		qe.registerQuestNpc(241758).addOnKillEvent(questId);
		qe.registerQuestNpc(241760).addOnKillEvent(questId);
		qe.registerQuestNpc(241761).addOnKillEvent(questId);
		qe.registerQuestNpc(241762).addOnKillEvent(questId);
		qe.registerQuestNpc(241763).addOnKillEvent(questId);
		qe.registerQuestNpc(242867).addOnKillEvent(questId);
		qe.registerQuestNpc(242871).addOnKillEvent(questId);
		qe.registerQuestNpc(242875).addOnKillEvent(questId);
		qe.registerQuestNpc(242879).addOnKillEvent(questId);
		qe.registerQuestNpc(242883).addOnKillEvent(questId);
		qe.registerQuestNpc(242887).addOnKillEvent(questId);
		qe.registerQuestNpc(242891).addOnKillEvent(questId);
		qe.registerQuestNpc(242895).addOnKillEvent(questId);
		qe.registerQuestNpc(242899).addOnKillEvent(questId);
		qe.registerQuestNpc(242903).addOnKillEvent(questId);
		qe.registerQuestNpc(242907).addOnKillEvent(questId);
		qe.registerQuestNpc(242911).addOnKillEvent(questId);
		qe.registerQuestNpc(242915).addOnKillEvent(questId);
		qe.registerQuestNpc(242919).addOnKillEvent(questId);
		qe.registerQuestNpc(242923).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 806108) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806108) {
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
            if (targetId == 806108) {
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
				case 241755:
				case 241756:
				case 241757:
				case 241759:
				case 241758:
				case 241760:
				case 241761:
				case 241762:
				case 241763:
				case 242867:
				case 242871:
				case 242875:
				case 242879:
				case 242883:
				case 242887:
				case 242891:
				case 242895:
				case 242899:
				case 242903:
				case 242907:
				case 242911:
				case 242915:
				case 242919:
				case 242923:
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