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

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _14041Abyssal_Abilities extends QuestHandler
{
    private final static int questId = 14041;
    private final static int[] npc_ids = {278627, 278628, 278629, 278630, 278631, 278632, 278633, 278554};
	
    public _14041Abyssal_Abilities() {
        super(questId);
    }
	
    @Override
    public void register() {
        for (int npc_id: npc_ids) {
            qe.registerQuestNpc(npc_id).addOnTalkEvent(questId);
        }
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterZoneMissionEnd(questId);
    }
	
    @Override
    public boolean onZoneMissionEndEvent(QuestEnv env) {
        return defaultOnZoneMissionEndEvent(env);
    }
	
    @Override
    public boolean onLvlUpEvent(QuestEnv env) {
        return defaultOnLvlUpEvent(env, 14040, true);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(0);
        int targetId = env.getTargetId();
        if (qs == null) {
            return false;
        } if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        } if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 278554) {
                if (env.getDialog() == QuestDialog.USE_OBJECT) {
                    return sendQuestDialog(env, 10002);
                } else if (env.getDialogId() == QuestDialog.SELECT_REWARD.id()) {
                    return sendQuestDialog(env, 5);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
            return false;
        } else if (qs.getStatus() != QuestStatus.START) {
            return false;
        } if (targetId == 278627) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 0) {
                        return sendQuestDialog(env, 1011);
                    }
				} case SELECT_ACTION_1012: {
                    if (var == 0) {
						playQuestMovie(env, 262);
                        return sendQuestDialog(env, 1012);
                    }
                } case STEP_TO_1: {
                    if (var == 0) {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
                    }
				}
            }
        } if (targetId == 278628) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 1) {
                        return sendQuestDialog(env, 1352);
                    }
				} case SELECT_ACTION_1353: {
                    if (var == 1) {
						playQuestMovie(env, 263);
                        return sendQuestDialog(env, 1353);
                    }
                } case STEP_TO_2: {
                    if (var == 1) {
                        changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
                    }
				}
            }
        } if (targetId == 278629) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 2) {
                        return sendQuestDialog(env, 1693);
                    }
				} case SELECT_ACTION_1694: {
                    if (var == 2) {
						playQuestMovie(env, 264);
                        return sendQuestDialog(env, 1694);
                    }
                } case STEP_TO_3: {
                    if (var == 2) {
                        changeQuestStep(env, 2, 3, false);
						return closeDialogWindow(env);
                    }
                }
            }
        } if (targetId == 278630) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 3) {
                        return sendQuestDialog(env, 2034);
                    }
				} case SELECT_ACTION_2035: {
                    if (var == 3) {
						playQuestMovie(env, 265);
                        return sendQuestDialog(env, 2035);
                    }
                } case STEP_TO_4: {
                    if (var == 3) {
                        changeQuestStep(env, 3, 4, false);
						return closeDialogWindow(env);
                    }
				}
            }
        } if (targetId == 278631) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 4) {
                        return sendQuestDialog(env, 2375);
                    }
				} case SELECT_ACTION_2376: {
                    if (var == 4) {
						playQuestMovie(env, 266);
                        return sendQuestDialog(env, 2376);
                    }
                } case STEP_TO_5: {
                    if (var == 4) {
                        changeQuestStep(env, 4, 5, false);
						return closeDialogWindow(env);
                    }
                }
            }
        } if (targetId == 278632) {
            switch (env.getDialog()) {
				case START_DIALOG: {
                    if (var == 5) {
                        return sendQuestDialog(env, 2716);
                    }
				} case SELECT_ACTION_2717: {
                    if (var == 5) {
						playQuestMovie(env, 267);
                        return sendQuestDialog(env, 2717);
                    }
                } case STEP_TO_6: {
                    if (var == 5) {
                        changeQuestStep(env, 5, 6, false);
						return closeDialogWindow(env);
                    }
				}
            }
        } if (targetId == 278633) {
            switch (env.getDialog()) {
                case START_DIALOG: {
                    if (var == 6) {
                        return sendQuestDialog(env, 3057);
                    }
				} case SELECT_ACTION_3058: {
                    if (var == 6) {
						playQuestMovie(env, 268);
                        return sendQuestDialog(env, 3058);
                    }
                } case SET_REWARD: {
                    if (var == 6) {
                        qs.setStatus(QuestStatus.REWARD);
                        updateQuestStatus(env);
						return closeDialogWindow(env);
                    }
				}
            }
        }
        return false;
    }
}