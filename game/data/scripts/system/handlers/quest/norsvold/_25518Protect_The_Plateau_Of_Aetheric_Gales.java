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

public class _25518Protect_The_Plateau_Of_Aetheric_Gales extends QuestHandler
{
    private final static int questId = 25518;
	
    public _25518Protect_The_Plateau_Of_Aetheric_Gales() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(806107).addOnQuestStart(questId);
        qe.registerQuestNpc(806107).addOnTalkEvent(questId);
		qe.registerQuestNpc(241580).addOnKillEvent(questId);
		qe.registerQuestNpc(241581).addOnKillEvent(questId);
		qe.registerQuestNpc(241582).addOnKillEvent(questId);
		qe.registerQuestNpc(241583).addOnKillEvent(questId);
		qe.registerQuestNpc(241584).addOnKillEvent(questId);
		qe.registerQuestNpc(241585).addOnKillEvent(questId);
		qe.registerQuestNpc(241212).addOnKillEvent(questId);
		qe.registerQuestNpc(243380).addOnKillEvent(questId);
		qe.registerQuestNpc(243381).addOnKillEvent(questId);
		qe.registerQuestNpc(243382).addOnKillEvent(questId);
		qe.registerQuestNpc(243383).addOnKillEvent(questId);
		qe.registerQuestNpc(243384).addOnKillEvent(questId);
		qe.registerQuestNpc(243385).addOnKillEvent(questId);
		qe.registerQuestNpc(243386).addOnKillEvent(questId);
		qe.registerQuestNpc(243387).addOnKillEvent(questId);
		qe.registerQuestNpc(243388).addOnKillEvent(questId);
		qe.registerQuestNpc(242087).addOnKillEvent(questId);
		qe.registerQuestNpc(242091).addOnKillEvent(questId);
		qe.registerQuestNpc(242095).addOnKillEvent(questId);
		qe.registerQuestNpc(242099).addOnKillEvent(questId);
		qe.registerQuestNpc(242103).addOnKillEvent(questId);
		qe.registerQuestNpc(242107).addOnKillEvent(questId);
		qe.registerQuestNpc(242111).addOnKillEvent(questId);
		qe.registerQuestNpc(242115).addOnKillEvent(questId);
		qe.registerQuestNpc(242119).addOnKillEvent(questId);
		qe.registerQuestNpc(242123).addOnKillEvent(questId);
		qe.registerQuestNpc(242127).addOnKillEvent(questId);
		qe.registerQuestNpc(242131).addOnKillEvent(questId);
		qe.registerQuestNpc(242135).addOnKillEvent(questId);
		qe.registerQuestNpc(242139).addOnKillEvent(questId);
		qe.registerQuestNpc(242143).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 806107) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806107) {
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
            if (targetId == 806107) {
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
				case 241580:
				case 241581:
				case 241582:
				case 241583:
				case 241584:
				case 241585:
				case 241212:
				case 243380:
				case 243381:
				case 243382:
				case 243383:
				case 243384:
				case 243385:
				case 243386:
				case 243387:
				case 243388:
				case 242087:
				case 242091:
				case 242095:
				case 242099:
				case 242103:
				case 242107:
				case 242111:
				case 242115:
				case 242119:
				case 242123:
				case 242127:
				case 242131:
				case 242135:
				case 242139:
				case 242143:
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