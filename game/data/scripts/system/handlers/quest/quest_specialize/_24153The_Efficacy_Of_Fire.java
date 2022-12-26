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

public class _24153The_Efficacy_Of_Fire extends QuestHandler
{
    private final static int questId = 24153;
	
    private final static int[] mob_ids = {213730, 213788, 213789, 213790, 213791};
	
    public _24153The_Efficacy_Of_Fire() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    @Override
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(204787).addOnQuestStart(questId); //Delris
        qe.registerQuestNpc(204787).addOnTalkEvent(questId); //Chieftain Akagitan
        qe.registerQuestNpc(204784).addOnTalkEvent(questId); //Delris
        qe.registerQuestItem(182215462, questId);
        for (int mob_id: mob_ids) {
            qe.registerQuestNpc(mob_id).addOnKillEvent(questId);
        }
    }
	
    @Override
    public boolean onDialogEvent(final QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 204787) { //Chieftain Akagitan
            	switch (env.getDialog()) {
        			case START_DIALOG: {
        				return sendQuestDialog(env, 1011);
        			} case ASK_ACCEPTION: {
        				return sendQuestDialog(env, 4);
        			} case ACCEPT_QUEST: {
        				QuestService.startQuest(env);
                        qs.setQuestVarById(5, 1);
                        updateQuestStatus(env);
                        return closeDialogWindow(env);
        			} case REFUSE_QUEST: {
        				return sendQuestDialog(env, 1004);
        			}
            	}
            }
        } else if (qs.getStatus() == QuestStatus.START) {
        	switch (targetId) {
        		case 204784: { //Delris
        			switch (env.getDialog()) {
        				case START_DIALOG: {
        					return sendQuestDialog(env, 1352);
        				} case STEP_TO_2: {
        					giveQuestItem(env, 182215462, 1); 
        					qs.setQuestVar(0);
        					updateQuestStatus(env);
        					return closeDialogWindow(env);
        				}
        			}
        		} case 204787: { //Chieftain Akagitan
        			switch (env.getDialog()) {
    					case START_DIALOG: {
    						return sendQuestDialog(env, 2375);
    					} case SELECT_REWARD: {
    						qs.setStatus(QuestStatus.REWARD);
                            updateQuestStatus(env);
    						return sendQuestDialog(env, 5);
    					}
        			}
        		}
        	}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 204787) { //Chieftain Akagitan
            	if (env.getDialog() == QuestDialog.SELECT_REWARD) {
            		return sendQuestDialog(env, 5);
            	} else {
            		return sendQuestEndDialog(env);
            	}
            }
        }
        return false;
    }
	
    @Override
    public boolean onKillEvent(QuestEnv env) {
		int targetId = env.getTargetId();
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        int var = qs.getQuestVarById(0);
        int var1 = qs.getQuestVarById(1);
        int var2 = qs.getQuestVarById(2);
        int var3 = qs.getQuestVarById(3);
        int var4 = qs.getQuestVarById(4);
		if (qs == null || qs.getStatus() != QuestStatus.START) {
            return false;
        } if (targetId == 213730 && var == 0 && var < 1) { //Glaciont The Hardy
            qs.setQuestVarById(0, 1);
            updateQuestStatus(env);
        } else if (targetId == 213788 && var1 == 0 && var1 < 1) { //Frostfist
            qs.setQuestVarById(1, 1);
            updateQuestStatus(env);
        } else if (targetId == 213789 && var2 == 0 && var2 < 1) { //Iceback
            qs.setQuestVarById(2, 1);
            updateQuestStatus(env);
        } else if (targetId == 213790 && var3 == 0 && var3 < 1) { //Chillblow
            qs.setQuestVarById(3, 1);
            updateQuestStatus(env);
        } else if (targetId == 213791 && var4 == 0 && var4 < 1) { //Snowfury
            qs.setQuestVarById(4, 1);
            updateQuestStatus(env);
        }
        return false;
    }
}