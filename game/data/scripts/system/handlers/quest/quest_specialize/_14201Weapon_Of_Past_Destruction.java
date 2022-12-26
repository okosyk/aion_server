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

public class _14201Weapon_Of_Past_Destruction extends QuestHandler
{
    private final static int questId = 14201;
	
    public _14201Weapon_Of_Past_Destruction() {
        super(questId);
    }    
	
    @Override
    public void register() {
        qe.registerQuestNpc(798155).addOnQuestStart(questId); //Atropos.
        qe.registerQuestNpc(798155).addOnTalkEvent(questId); //Atropos.
		qe.registerQuestNpc(800407).addOnTalkEvent(questId); //Hongras.
		qe.registerQuestNpc(798212).addOnTalkEvent(questId); //Serimnir.
    }
	
	@Override
    public boolean onDialogEvent(final QuestEnv env) {
        final Player player = env.getPlayer();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int var = qs.getQuestVarById(0);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
            if (targetId == 798155) { //Atropos.
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 1011);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 800407) { //Hongras.
				switch (dialog) {
					case START_DIALOG: {
						if (var == 0) {
							return sendQuestDialog(env, 1352);
						}
					} case STEP_TO_1: {
						qs.setQuestVar(1);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					}
				}
			} else if (targetId == 798212) { //Serimnir.
				switch (dialog) {
					case START_DIALOG: {
						if (var == 1) {
							return sendQuestDialog(env, 1693);
						}
					} case STEP_TO_2: {
						qs.setQuestVar(2);
						updateQuestStatus(env);
						return closeDialogWindow(env);
					}
				}
			} else if (targetId == 798155) { //Atropos.
				switch (dialog) {
					case START_DIALOG: {
						if (var == 2) {
							return sendQuestDialog(env, 2375);
						}
					} case CHECK_COLLECTED_ITEMS_SIMPLE: {
						if (QuestService.collectItemCheck(env, true)) {
							changeQuestStep(env, 2, 2, true);
							return sendQuestDialog(env, 5);
                        } else {
							return closeDialogWindow(env);
						}
					}
				}
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 798155) { //Atropos.
                return sendQuestEndDialog(env);
			}
		}
        return false;
    }
}