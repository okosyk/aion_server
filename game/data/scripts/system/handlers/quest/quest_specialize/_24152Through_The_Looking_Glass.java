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

public class _24152Through_The_Looking_Glass extends QuestHandler
{
    private final static int questId = 24152;

    public _24152Through_The_Looking_Glass() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    @Override
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(204768).addOnQuestStart(questId); //Sleipnir
        qe.registerQuestNpc(204768).addOnTalkEvent(questId); //Sleipnir
        qe.registerQuestNpc(204739).addOnTalkEvent(questId); //Baugi
        qe.registerQuestNpc(802364).addOnTalkEvent(questId); //Dojer
        qe.addHandlerSideQuestDrop(questId, 213739, 182215461, 1, 100);
    }
	
    @Override
    public boolean onDialogEvent(final QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 204768) { //Sleipnir
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
            	case 204739: {	//Baugi
            		switch (env.getDialog()) {
            			case START_DIALOG: {
            				return sendQuestDialog(env, 1352);
            			} case STEP_TO_1: {
            				qs.setQuestVar(1);
            				updateQuestStatus(env);
            				return closeDialogWindow(env);
            			}
            		}
            	} case 802364: { //Dojer
            		switch (env.getDialog()) {
        				case START_DIALOG: {
        					return sendQuestDialog(env, 2375);
        				} case CHECK_COLLECTED_ITEMS_SIMPLE: {
        					if (player.getInventory().getItemCountByItemId(182215461) == 1) {
        						qs.setQuestVar(2);
        						qs.setStatus(QuestStatus.REWARD);
        						updateQuestStatus(env);
        						removeQuestItem(env, 182215461, 1);
        						return sendQuestDialog(env, 5);
        					} else {
        						return closeDialogWindow(env);
        					}
        				}
            		}
            	}
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 802364) { //Dojer
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