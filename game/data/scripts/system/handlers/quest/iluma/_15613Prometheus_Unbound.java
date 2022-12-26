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

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.*;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.services.teleport.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.zone.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _15613Prometheus_Unbound extends QuestHandler
{
    public static final int questId = 15613;
	
    public _15613Prometheus_Unbound() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    @Override
    public void register() {
		qe.registerOnLevelUp(questId);
        qe.registerQuestNpc(806113).addOnQuestStart(questId); //Volter.
		qe.registerQuestNpc(806113).addOnTalkEvent(questId); //Volter.
		qe.registerQuestNpc(806166).addOnTalkEvent(questId); //세다르.
		qe.registerQuestNpc(806198).addOnTalkEvent(questId); //프로메테우스.
		qe.registerQuestNpc(731686).addOnTalkEvent(questId); //프로메테우스.
		qe.registerOnEnterZone(ZoneName.get("DF6_SENSORY_AREA_Q15613_A_220110000"), questId);
		qe.registerOnEnterZone(ZoneName.get("DF6_SENSORY_AREA_Q15613_B_220110000"), questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(0);
		int targetId = env.getTargetId();
		final Npc npc = (Npc) env.getVisibleObject();
		if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        } if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 806113) { //Volter.
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
			if (targetId == 806166) { //세다르.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 1) {
                            return sendQuestDialog(env, 1353);
                        }
					} case STEP_TO_2: {
						playQuestMovie(env, 875);
						changeQuestStep(env, 1, 2, false);
						TeleportService2.teleportTo(player, 220110000, 1254.2067f, 2899.5266f, 251.91998f, (byte) 118);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 731686) { //프로메테우스.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 3) {
                            return sendQuestDialog(env, 2034);
                        }
					} case STEP_TO_4: {
						QuestService.addNewSpawn(220110000, 1, 806198, 1278.2848f, 2896.9226f, 252.33464f, (byte) 58);
						changeQuestStep(env, 3, 4, false);
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 806198) { //프로메테우스.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 4) {
                            return sendQuestDialog(env, 2375);
                        }
					} case STEP_TO_5: {
						changeQuestStep(env, 4, 5, false);
						npc.getController().onDelete();
						return closeDialogWindow(env);
					}
				}
			} if (targetId == 806113) { //Volter.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 5) {
                            return sendQuestDialog(env, 2716);
                        }
					} case CHECK_COLLECTED_ITEMS: {
						if (QuestService.collectItemCheck(env, true)) {
							qs.setStatus(QuestStatus.REWARD);
							updateQuestStatus(env);
							return sendQuestDialog(env, 10000);
						} else {
							return sendQuestDialog(env, 10001);
						}
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806113) { //Volter.
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
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("DF6_SENSORY_AREA_Q15613_A_220110000")) {
				if (var == 0) {
					changeQuestStep(env, 0, 1, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("DF6_SENSORY_AREA_Q15613_B_220110000")) {
				if (var == 2) {
					changeQuestStep(env, 2, 3, false);
					return true;
				}
			}
		}
		return false;
	}
}