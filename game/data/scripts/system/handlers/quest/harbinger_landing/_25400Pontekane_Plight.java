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

import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Rinzler (Encom)
/****/

public class _25400Pontekane_Plight extends QuestHandler
{
	public static final int questId = 25400;
	
	public _25400Pontekane_Plight() {
		super(questId);
	}
	
    @Override
    public void register() {
        int[] npcs = {805356, 805357, 805358, 805359, 805360, 702830, 702831, 702832};
        for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        }
		qe.registerOnEnterWorld(questId);
		qe.registerQuestItem(182215900, questId); //Beritra Supply Mark.
		qe.registerQuestItem(182215901, questId); //Repair Device Gear.
		qe.registerQuestItem(182215902, questId); //Ereshkigal Legion Mark.
		qe.registerQuestNpc(883643).addOnKillEvent(questId); //Ereshkigal's Searcher.
		qe.registerOnEnterZone(ZoneName.get("MIREN_ISLAND_400010000"), questId);
    }
	
    @Override
    public boolean onEnterWorldEvent(QuestEnv env) {
        Player player = env.getPlayer();
        if (player.getWorldId() == 400010000) { //Reshanta.
            QuestState qs = player.getQuestStateList().getQuestState(questId);
            if (qs == null) {
                env.setQuestId(questId);
                if (QuestService.startQuest(env)) {
                    return true;
                }
            }
        }
        return false;
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null) {
            return false;
        }
        int var = qs.getQuestVarById(0);
        int targetId = 0;
        if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        } if (qs.getStatus() == QuestStatus.START) {
            if (targetId == 805356) { //Pontekane.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1011);
                        }
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 805357) { //Darmian.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 1) {
                            return sendQuestDialog(env, 1352);
                        } else if (var == 8) {
                            playQuestMovie(env, 297);
							qs.setStatus(QuestStatus.REWARD);
							updateQuestStatus(env);
							return closeDialogWindow(env);
                        }
					} case STEP_TO_2: {
                        changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 805358) { //Batai.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 2) {
                            return sendQuestDialog(env, 1693);
                        }
					} case STEP_TO_3: {
                        changeQuestStep(env, 2, 3, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 702830) { //Beritra Supply Unit Box.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
						switch (player.getRace()) {
							case ASMODIANS:
								giveQuestItem(env, 182215900, 1); //Beritra Supply Mark.
							break;
						}
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 702831) { //Destroyed Gate Reinforcer.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
						switch (player.getRace()) {
							case ASMODIANS:
								giveQuestItem(env, 182215901, 1); //Repair Device Gear.
							break;
						}
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 702832) { //Heavy Bomb Box.
                switch (env.getDialog()) {
                    case USE_OBJECT: {
						switch (player.getRace()) {
							case ASMODIANS:
								giveQuestItem(env, 182215902, 1); //Ereshkigal Legion Mark.
							break;
						}
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 805359) { //Dukas.
                switch (env.getDialog()) {
                    case START_DIALOG: {
						if (var == 3) {
                            return sendQuestDialog(env, 2034);
                        }
					} case STEP_TO_4: {
                        changeQuestStep(env, 4, 5, false);
						return closeDialogWindow(env);
					} case CHECK_COLLECTED_ITEMS: {
						if (QuestService.collectItemCheck(env, true)) {
							changeQuestStep(env, 3, 4, false);
							return sendQuestDialog(env, 10000);
						} else {
							return sendQuestDialog(env, 10001);
						}
					}
                }
            } if (targetId == 805360) { //Lanpaz.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        if (var == 6) {
                            return sendQuestDialog(env, 2716);
                        }
					} case STEP_TO_7: {
                        playQuestMovie(env, 296);
						changeQuestStep(env, 7, 8, false);
						return closeDialogWindow(env);
					}
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 805356) { //Pontekane.
				if (env.getDialog() == QuestDialog.USE_OBJECT) {
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
			if (zoneName == ZoneName.get("MIREN_ISLAND_400010000")) {
				if (var == 5) {
					QuestService.addNewSpawn(400010000, 1, 883643, 1659.9512f, 2418.4272f, 2930.4697f, (byte) 97); //Ereshkigal's Searcher.
					QuestService.addNewSpawn(400010000, 1, 883643, 1666.3044f, 2419.7336f, 2931.3823f, (byte) 88); //Ereshkigal's Searcher.
					changeQuestStep(env, 5, 6, false);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
    public boolean onKillEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (var == 6) {
                int var1 = qs.getQuestVarById(1);
                if (var1 >= 0 && var1 < 1) {
                    return defaultOnKillEvent(env, 883643, var1, var1 + 1, 1); //Ereshkigal's Searcher.
                } else if (var1 == 1) {
                    qs.setQuestVar(7);
					updateQuestStatus(env);
                    return true;
                }
            }
        }
        return false;
    }
}