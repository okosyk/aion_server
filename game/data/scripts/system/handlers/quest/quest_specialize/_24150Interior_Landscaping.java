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

/****/
/** Author Rinzler (Encom)
/****/

public class _24150Interior_Landscaping extends QuestHandler
{
    private final static int questId = 24150;
	
    public _24150Interior_Landscaping() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(204702).addOnQuestStart(questId); //Nerita
        qe.registerQuestNpc(204702).addOnTalkEvent(questId); //Nerita
        qe.registerQuestNpc(204733).addOnTalkEvent(questId); //Bestla
        qe.registerQuestNpc(204734).addOnTalkEvent(questId); //Horu
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 204702) { //Nerita
            	switch (env.getDialog()) {
            		case START_DIALOG: {
            			return sendQuestDialog(env, 1011);
            		} case ASK_ACCEPTION: {
            			return sendQuestDialog(env, 4);
            		} case ACCEPT_QUEST: {
            			return sendQuestStartDialog(env);
            		} case REFUSE_QUEST: {
            			return sendQuestDialog(env, 1004);
            		}
            	}
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            switch (targetId) {
            	case 204733: { //Bestla
            		switch (env.getDialog()) {
            			case START_DIALOG: {
           					return sendQuestDialog(env, 1352);
            			} case STEP_TO_1: {
            				giveQuestItem(env, 182215460, 1);
            				qs.setQuestVar(1);
            				updateQuestStatus(env);
            				return closeDialogWindow(env);
            			}
            		}
            	} case 204734: { //Horu
            		switch (env.getDialog()) {
        				case START_DIALOG: {
        					return sendQuestDialog(env, 1693);
        				} case STEP_TO_2: {
        					qs.setQuestVar(2);
                            updateQuestStatus(env);
        					return closeDialogWindow(env);
        				}
        			}
            	} case 204702: { //Nerita
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
            if (targetId == 204702) { //Nerita
            	if (env.getDialog() == QuestDialog.SELECT_REWARD) {
            		return sendQuestDialog(env, 5);
            	} else {
            		return sendQuestEndDialog(env);
            	}
            }
        }
        return false;
    }
}