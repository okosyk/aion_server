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

public class _15531Scout_Zenzen_Tribal_Grounds extends QuestHandler
{
    private final static int questId = 15531;
	
    public _15531Scout_Zenzen_Tribal_Grounds() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(806099).addOnQuestStart(questId);
        qe.registerQuestNpc(806099).addOnTalkEvent(questId);
		qe.registerQuestNpc(241625).addOnKillEvent(questId);
		qe.registerQuestNpc(241626).addOnKillEvent(questId);
		qe.registerQuestNpc(241627).addOnKillEvent(questId);
		qe.registerQuestNpc(241628).addOnKillEvent(questId);
		qe.registerQuestNpc(241629).addOnKillEvent(questId);
		qe.registerQuestNpc(241630).addOnKillEvent(questId);
		qe.registerQuestNpc(241631).addOnKillEvent(questId);
		qe.registerQuestNpc(241632).addOnKillEvent(questId);
		qe.registerQuestNpc(241633).addOnKillEvent(questId);
		qe.registerQuestNpc(241637).addOnKillEvent(questId);
		qe.registerQuestNpc(241638).addOnKillEvent(questId);
		qe.registerQuestNpc(241639).addOnKillEvent(questId);
		qe.registerQuestNpc(242347).addOnKillEvent(questId);
		qe.registerQuestNpc(242351).addOnKillEvent(questId);
		qe.registerQuestNpc(242355).addOnKillEvent(questId);
		qe.registerQuestNpc(242359).addOnKillEvent(questId);
		qe.registerQuestNpc(242363).addOnKillEvent(questId);
		qe.registerQuestNpc(242367).addOnKillEvent(questId);
		qe.registerQuestNpc(242371).addOnKillEvent(questId);
		qe.registerQuestNpc(242375).addOnKillEvent(questId);
		qe.registerQuestNpc(242379).addOnKillEvent(questId);
		qe.registerQuestNpc(242383).addOnKillEvent(questId);
		qe.registerQuestNpc(242387).addOnKillEvent(questId);
		qe.registerQuestNpc(242391).addOnKillEvent(questId);
		qe.registerQuestNpc(242395).addOnKillEvent(questId);
		qe.registerQuestNpc(242399).addOnKillEvent(questId);
		qe.registerQuestNpc(242403).addOnKillEvent(questId);
		qe.registerQuestNpc(242407).addOnKillEvent(questId);
		qe.registerQuestNpc(242411).addOnKillEvent(questId);
		qe.registerQuestNpc(242415).addOnKillEvent(questId);
		qe.registerQuestNpc(242419).addOnKillEvent(questId);
		qe.registerQuestNpc(242423).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 806099) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 806099) {
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
            if (targetId == 806099) {
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
				case 241625:
				case 241626:
				case 241627:
				case 241628:
				case 241629:
				case 241630:
				case 241631:
				case 241632:
				case 241633:
				case 241637:
				case 241638:
				case 241639:
				case 242347:
				case 242351:
				case 242355:
				case 242359:
				case 242363:
				case 242367:
				case 242371:
				case 242375:
				case 242379:
				case 242383:
				case 242387:
				case 242391:
				case 242395:
				case 242399:
				case 242403:
				case 242407:
				case 242411:
				case 242415:
				case 242419:
				case 242423:
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