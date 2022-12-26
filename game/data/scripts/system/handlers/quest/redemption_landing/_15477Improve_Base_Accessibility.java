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
package quest.redemption_landing;

import java.util.*;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/****/
/** Author Rinzler (Encom)
/****/

public class _15477Improve_Base_Accessibility extends QuestHandler
{
    private final static int questId = 15477;
	private static final Set<Integer> ab1BLv4L02;
	
    public _15477Improve_Base_Accessibility() {
        super(questId);
    }
	
	static {
		ab1BLv4L02 = new HashSet<Integer>();
		ab1BLv4L02.add(805812);
		ab1BLv4L02.add(805813);
		ab1BLv4L02.add(805814);
	}
	
	@Override
	public void register() {
		Iterator<Integer> iter = ab1BLv4L02.iterator();
		while (iter.hasNext()) {
			int ab1Id = iter.next();
			qe.registerQuestNpc(ab1Id).addOnQuestStart(questId);
			qe.registerQuestNpc(ab1Id).addOnTalkEvent(questId);
			qe.registerQuestNpc(883301).addOnKillEvent(questId);
			qe.registerQuestNpc(883302).addOnKillEvent(questId);
			qe.registerQuestNpc(883304).addOnKillEvent(questId);
			qe.registerQuestNpc(883305).addOnKillEvent(questId);
			qe.registerQuestNpc(883308).addOnKillEvent(questId);
		}
	}
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        int targetId = env.getTargetId();
		final Player player = env.getPlayer();
		if (!ab1BLv4L02.contains(targetId)) {
			return false;
		}
        QuestDialog dialog = env.getDialog();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
            switch (dialog) {
				case START_DIALOG: {
					return sendQuestDialog(env, 4762);
				} case ACCEPT_QUEST:
				case ACCEPT_QUEST_SIMPLE: {
					return sendQuestStartDialog(env);
				} case REFUSE_QUEST_SIMPLE: {
				    return closeDialogWindow(env);
				}
			}
        } else if (qs.getStatus() == QuestStatus.START) {
            switch (dialog) {
				case START_DIALOG: {
					return sendQuestDialog(env, 2375);
				} case SELECT_REWARD: {
					changeQuestStep(env, 8, 9, true);
					return sendQuestEndDialog(env);
				}
			}
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (env.getDialog() == QuestDialog.START_DIALOG) {
                return sendQuestDialog(env, 10002);
		    } else {
				return sendQuestEndDialog(env);
			}
		}
        return false;
    }
	
    public boolean onKillEvent(QuestEnv env) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            switch (env.getTargetId()) {
				case 883301:
				case 883302:
				case 883304:
				case 883305:
				case 883308:
                if (qs.getQuestVarById(1) < 8) {
					qs.setQuestVarById(1, qs.getQuestVarById(1) + 1);
					updateQuestStatus(env);
				} if (qs.getQuestVarById(1) >= 8) {
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
				}
            }
        }
        return false;
    }
}