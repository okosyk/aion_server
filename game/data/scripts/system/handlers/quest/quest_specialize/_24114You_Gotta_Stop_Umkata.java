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
package quest.quest_specialize;
 
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;
import com.aionemu.gameserver.services.*;
 
/****/
/** Author Rinzler (Encom)
/****/
 
public class _24114You_Gotta_Stop_Umkata extends QuestHandler
{
    private final static int questId = 24114;
	
	private final static int[] heroSpirit = {210722, 210588}; //Hero Spirit
	
    public _24114You_Gotta_Stop_Umkata() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    @Override
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(203649).addOnQuestStart(questId); //Gulkalla
        qe.registerQuestNpc(203649).addOnTalkEvent(questId); //Gulkalla
		qe.registerQuestNpc(700097).addOnTalkEvent(questId); //Umkata's Jewel Box
		qe.registerQuestNpc(700098).addOnTalkEvent(questId); //Umkata's Grave
		qe.registerQuestNpc(210752).addOnKillEvent(questId); //Umkata
		for (int mob: heroSpirit) {
            qe.registerQuestNpc(mob).addOnKillEvent(questId);
        }
    }
	
	@Override
    public boolean onDialogEvent(final QuestEnv env) {
        final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
		int var = qs.getQuestVarById(0);
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 203649) { //Gulkalla
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 203649) { //Gulkalla
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 3) {
							return sendQuestDialog(env, 1011);
						} else if (var == 1) {
							return sendQuestDialog(env, 2375);
						}
					} case STEP_TO_1: {
						return defaultCloseDialog(env, 3, 4);
					}
				}
			} else if (targetId == 700097) {
                if (env.getDialog() == QuestDialog.USE_OBJECT && var == 4) {
					giveQuestItem(env, 182215475, 1);
                    return false;
                }
			} else if (targetId == 700098) { //Umkata's Grave
				switch (env.getDialog()) {
					case USE_OBJECT: {
						if (var == 4) {
							QuestService.addNewSpawn(220030000, 1, 210752, 2889.9834f, 1741.3108f, 254.75f, (byte) 0);
							return sendQuestDialog(env, 1693);
						}
					}
				}
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 203649) { //Gulkalla
				switch (env.getDialog()) {
					case START_DIALOG: {
						return sendQuestDialog(env, 1352);
					} case SELECT_REWARD: {
						return sendQuestDialog(env, 5);
					} default: {
                        return sendQuestEndDialog(env);
					}
				}
			}
		}
		return false;
	}
	
	@Override
    public boolean onKillEvent(QuestEnv env) {
		final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(0);
        if (qs == null || qs.getStatus() != QuestStatus.START) {
            return false;
        } if (var >= 0 && var < 3) {
			return defaultOnKillEvent(env, heroSpirit, var, var + 1);
		} else if (var == 4) {
			return defaultOnKillEvent(env, 210752, 4, true);
		}
		return false;
	}
}