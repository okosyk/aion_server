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
package quest.theobomos;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Rinzler (Encom)
/****/

public class _3018Fork_Ear_Rokes extends QuestHandler
{
    private final static int questId = 3018;
	
    public _3018Fork_Ear_Rokes() {
        super(questId);
    }
	
    @Override
    public void register() {
        qe.registerQuestNpc(730105).addOnQuestStart(questId);
        qe.registerQuestNpc(730105).addOnTalkEvent(questId);
        qe.registerQuestNpc(798150).addOnTalkEvent(questId);
        qe.registerGetingItem(182208009, questId);
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        final Player player = env.getPlayer();
        int targetId = 0;
        if (env.getVisibleObject() instanceof Npc) {
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
        }
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 730105) {
                switch (dialog) {
                    case START_DIALOG: {
                        return sendQuestDialog(env, 4762);
                    } case STEP_TO_1: {
                        QuestService.startQuest(env);
                        PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(0, 0));
                        return true;
                    } default:
                        return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
            if (targetId == 798150) {
                switch (dialog) {
                    case START_DIALOG: {
                        if (var == 0) {
                            return sendQuestDialog(env, 1352);
                        }
					} case CHECK_COLLECTED_ITEMS: {
                        if (QuestService.collectItemCheck(env, true)) {
                            if (!giveQuestItem(env, 182208009, 1)) {
                                return true;
                            }
                            qs.setStatus(QuestStatus.REWARD);
                            updateQuestStatus(env);
                            PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
                            return true;
                        } else {
                            return sendQuestDialog(env, 5);
                        }
                    }
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 798150) {
                removeQuestItem(env, 182208009, 1);
                return sendQuestEndDialog(env);
            }
        }
        return false;
    }
	
    @Override
    public boolean onGetItemEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            changeQuestStep(env, 0, 0, true);
            return true;
        }
        return false;
    }
}