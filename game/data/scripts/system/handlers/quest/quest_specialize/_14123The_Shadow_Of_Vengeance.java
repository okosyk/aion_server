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
import com.aionemu.gameserver.world.*;
import com.aionemu.gameserver.world.zone.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _14123The_Shadow_Of_Vengeance extends QuestHandler
{
    private final static int questId = 14123;
	
    public _14123The_Shadow_Of_Vengeance() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    @Override
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(203933).addOnQuestStart(questId); //Actaeon
        qe.registerQuestNpc(203933).addOnTalkEvent(questId); //Actaeon
		qe.registerQuestNpc(203991).addOnTalkEvent(questId); //Dionera
		qe.registerQuestNpc(206360).addOnKillEvent(questId); //Peddler Hippola
		qe.registerOnEnterZone(ZoneName.get("ELTNEN_OBSERVATORY_210020000"), questId);
    }
	
	@Override
    public boolean onDialogEvent(final QuestEnv env) {
        final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
		int var = qs.getQuestVarById(0);
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 203933) { //Actaeon
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 1011);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 203991) { //Dionera
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 0) {
							return sendQuestDialog(env, 1352);
						} else if (var == 1) {
							return sendQuestDialog(env, 2375);
						}
					} case STEP_TO_1: {
						qs.setQuestVar(0);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					} case SELECT_REWARD: {
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return sendQuestDialog(env, 5);
					} case FINISH_DIALOG: {
                        return sendQuestSelectionDialog(env);
                    }
				}
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 203991) { //Dionera
                return sendQuestEndDialog(env);
			}
		}
        return false;
    }
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("ELTNEN_OBSERVATORY_210020000")) {
				if (var == 0) {
					QuestService.addNewSpawn(210020000, 1, 206360, (float) 1768.16, (float) 924.47, (float) 422.02, (byte) 0);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
    public boolean onKillEvent(QuestEnv env) {
        return defaultOnKillEvent(env, 206360, 0, 1);
    }
}