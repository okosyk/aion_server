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
package quest.high_daevanion;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _15325Courageous_Spirit extends QuestHandler
{
    private final static int questId = 15325;
	
	private final static int[] LF4C5DrakanFi = {215768, 215769, 215770, 215771, 215772,
	215773, 215774, 215775, 215776, 215777, 215778, 215779, 217043, 217044,
	217045, 217046, 217047, 217048, 217049, 217050, 217051, 217052, 217053,
	217054, 217055, 217056, 217057, 217059, 217060, 217061, 217062, 217063,
	217064, 216436, 216437};
	
    public _15325Courageous_Spirit() {
        super(questId);
    }
	
    public void register() {
        qe.registerQuestNpc(805331).addOnQuestStart(questId); //Machina.
        qe.registerQuestNpc(805331).addOnTalkEvent(questId); //Machina.
		for (int mob: LF4C5DrakanFi) {
			qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
        int targetId = env.getTargetId();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        QuestDialog dialog = env.getDialog();
        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            if (targetId == 805331) { //Machina.
                if (dialog == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 4762);
                } else {
                    return sendQuestStartDialog(env);
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 805331) { //Machina.
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
    public boolean onKillEvent(QuestEnv env) {
		final Player player = env.getPlayer();
        final QuestState qs = player.getQuestStateList().getQuestState(questId);
        int var = qs.getQuestVarById(0);
		int var1 = qs.getQuestVarById(1);
		if (qs == null || qs.getStatus() != QuestStatus.START) {
            return false;
        } if (var == 0 && var1 >= 0 && var1 < 29) {
			return defaultOnKillEvent(env, LF4C5DrakanFi, var1, var1 + 1, 1);
		} else if (var == 0 && var1 == 29) {
			qs.setQuestVarById(1, 0);
			qs.setQuestVar(1);
			qs.setStatus(QuestStatus.REWARD);
			updateQuestStatus(env);
			return true;
		}
		return false;
	}
}