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

/****/
/** Author Rinzler (Encom)
/****/

public class _14012Dukaki_Mischief extends QuestHandler
{
    private final static int questId = 14012;
	
    public _14012Dukaki_Mischief() {
        super(questId);
    }
	
    @Override
    public void register() {
        int[] mobs = {210145, 210146, 210157, 210740};
        qe.registerOnEnterZoneMissionEnd(questId);
        qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(203129).addOnTalkEvent(questId);
        qe.registerQuestNpc(203098).addOnTalkEvent(questId);
        for (int mob: mobs) {
            qe.registerQuestNpc(mob).addOnKillEvent(questId);
        }
    }
	
    @Override
    public boolean onZoneMissionEndEvent(QuestEnv env) {
        return defaultOnZoneMissionEndEvent(env);
    }
	
    @Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 14011, true);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null) {
            return false;
        } if (qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            int var1 = qs.getQuestVarById(1);
            int var2 = qs.getQuestVarById(2);
            switch (targetId) {
                case (203129): {
                    switch (dialog) {
                        case START_DIALOG: {
                            if (var == 0) {
                                return sendQuestDialog(env, 1011);
                            } else if (var == 1 && var1 == 5 && var2 == 3) {
                                return sendQuestDialog(env, 1693);
                            }
                        } case STEP_TO_1: {
                            if (var == 0) {
                                return defaultCloseDialog(env, 0, 1);
                            }
						} case STEP_TO_3: {
                            if (var == 1) {
                                qs.setStatus(QuestStatus.REWARD);
                                updateQuestStatus(env);
                                return closeDialogWindow(env);
							}
                        }
                    }
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 203098) {
                return sendQuestEndDialog(env);
            }
        }
        return false;
    }
	
    @Override
    public boolean onKillEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (var == 1) {
                int[] dukaki = {210145, 210146};
                int[] tursin = {210157, 210740};
                switch (targetId) {
                    case 210145:
                    case 210146: {
                        return defaultOnKillEvent(env, dukaki, 0, 5, 1);
                    }
					case 210157:
					case 210740: {
                        return defaultOnKillEvent(env, tursin, 0, 3, 2);
                    }
                }
            }
        }
        return false;
    }
}