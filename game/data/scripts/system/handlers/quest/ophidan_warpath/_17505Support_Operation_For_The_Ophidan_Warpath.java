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
package quest.ophidan_warpath;

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.*;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.zone.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _17505Support_Operation_For_The_Ophidan_Warpath extends QuestHandler
{
    private final static int questId = 17505;
	
    public _17505Support_Operation_For_The_Ophidan_Warpath() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
	public void register() {
		qe.registerOnLevelUp(questId);
		qe.registerOnEnterWorld(questId);
		qe.registerQuestItem(182216067, questId); //슈고의 특수 물약.
		qe.registerQuestNpc(806266).addOnQuestStart(questId); //세베루스.
		qe.registerQuestNpc(806266).addOnTalkEvent(questId); //세베루스.
		qe.registerQuestNpc(806267).addOnTalkEvent(questId); //테루아.
		qe.registerQuestNpc(806270).addOnTalkEvent(questId); //히요린.
		qe.registerOnEnterZone(ZoneName.get("IDLDF5_UNDER_02_WAR_ITEMUSEAREA_17505A"), questId);
	}
	
	@Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(0);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 806266) { //세베루스.
				switch (env.getDialog()) {
                    case START_DIALOG: {
						return sendQuestDialog(env, 4762);
					} case ACCEPT_QUEST:
					case ACCEPT_QUEST_SIMPLE: {
						return sendQuestStartDialog(env);
					} case REFUSE_QUEST_SIMPLE: {
				        return closeDialogWindow(env);
					}
                }
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 806270) { //히요린.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 0) {
						   return sendQuestDialog(env, 1011);
						}
					} case SELECT_ACTION_1012: {
						if (var == 0) {
							return sendQuestDialog(env, 1012);
						}
					} case STEP_TO_1: {
						giveQuestItem(env, 182216067, 1); //슈고의 특수 물약.
                        return defaultCloseDialog(env, 0, 1);
					}
                }
			} else if (targetId == 806267) { //테루아.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 3) {
						   return sendQuestDialog(env, 2034);
						}
					} case SELECT_ACTION_2035: {
						if (var == 3) {
							return sendQuestDialog(env, 2035);
						}
					} case STEP_TO_4: {
						playQuestMovie(env, 945);
						return defaultCloseDialog(env, 3, 4);
					}
                }
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806266) { //세베루스.
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 10002);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
		return false;
	}
	
	@Override
    public boolean onEnterWorldEvent(QuestEnv env) {
        Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (player.getWorldId() == 301670000) {
			if (qs != null && qs.getStatus() == QuestStatus.START) {
                int var = qs.getQuestVars().getQuestVars();
                if (var == 1) {
                    changeQuestStep(env, 1, 2, false);
				}
			}
		}
        return false;
    }
	
	@Override
	public HandlerResult onItemUseEvent(QuestEnv env, Item item) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getQuestVarById(0) == 2) {
			removeQuestItem(env, 182216067, 1); //슈고의 특수 물약.
			return HandlerResult.fromBoolean(useQuestItem(env, item, 2, 3, false));
		}
		return HandlerResult.FAILED;
	}
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("IDLDF5_UNDER_02_WAR_ITEMUSEAREA_17505A")) {
				if (var == 4) {
					qs.setQuestVar(5);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
					return true;
				}
			}
		}
		return false;
	}
}