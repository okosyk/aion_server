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
package quest.harbinger_landing;

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

public class _25474Ruins_Of_Roah_Occupation extends QuestHandler
{
    private final static int questId = 25474;
	
    public _25474Ruins_Of_Roah_Occupation() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(805815).addOnQuestStart(questId);
        qe.registerQuestNpc(805815).addOnTalkEvent(questId);
		qe.registerQuestNpc(883148).addOnKillEvent(questId);
		qe.registerQuestNpc(883160).addOnKillEvent(questId);
		qe.registerQuestNpc(883172).addOnKillEvent(questId);
		qe.registerQuestNpc(883016).addOnKillEvent(questId);
		qe.registerQuestNpc(883022).addOnKillEvent(questId);
		qe.registerQuestNpc(883028).addOnKillEvent(questId);
		qe.registerQuestNpc(883150).addOnKillEvent(questId);
		qe.registerQuestNpc(883162).addOnKillEvent(questId);
		qe.registerQuestNpc(883174).addOnKillEvent(questId);
		qe.registerQuestNpc(883018).addOnKillEvent(questId);
		qe.registerQuestNpc(883024).addOnKillEvent(questId);
		qe.registerQuestNpc(883030).addOnKillEvent(questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 805815) {
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 805815) {
                if (dialog == QuestDialog.START_DIALOG) {
                    if (qs.getQuestVarById(0) == 1) {
                        return sendQuestDialog(env, 2375);
                    }
                } if (dialog == QuestDialog.SELECT_REWARD) {
                    changeQuestStep(env, 1, 2, true);
                    return sendQuestEndDialog(env);
                }
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 805815) {
				if (env.getDialogId() == 1352) {
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
				case 883148:
				case 883160:
				case 883172:
				case 883016:
				case 883022:
				case 883028:
				case 883150:
				case 883162:
				case 883174:
				case 883018:
				case 883024:
				case 883030:
                if (qs.getQuestVarById(1) < 1) {
					qs.setQuestVarById(1, qs.getQuestVarById(1) + 1);
					updateQuestStatus(env);
				} if (qs.getQuestVarById(1) >= 1) {
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
				}
            }
        }
        return false;
    }
}