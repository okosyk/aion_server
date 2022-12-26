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
package quest.fallen_poeta;

import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.*;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _28996Unstable_Cursed_Canyon_Fragment extends QuestHandler
{
    private final static int questId = 28996;
	private final static int[] npcs = {806079, 806253, 834035};
	private final static int[] IDLF1TBarricadeDragon01 = {703290}; //어두운 포자길 철책.
	private final static int[] IDLF1TBarricadeDragon03 = {703292}; //트몰리아 폐광 입구 철책.
	
    public _28996Unstable_Cursed_Canyon_Fragment() {
        super(questId);
    }
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
    public void register() {
		for (int npc: npcs) {
            qe.registerQuestNpc(npc).addOnTalkEvent(questId);
        } for (int mob: IDLF1TBarricadeDragon01) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		} for (int mob: IDLF1TBarricadeDragon03) {
		    qe.registerQuestNpc(mob).addOnKillEvent(questId);
		}
		qe.registerOnLevelUp(questId);
		qe.registerQuestNpc(243683).addOnKillEvent(questId); //군단장 타하바타.
		qe.registerQuestNpc(243684).addOnKillEvent(questId); //아티팩트를 지배하는 크로반.
    }
	
    @Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.START) {
            if (targetId == 806253) { //Vienste.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        return sendQuestDialog(env, 1011);
					} case ACCEPT_QUEST:
					case ACCEPT_QUEST_SIMPLE: {
						return sendQuestStartDialog(env);
					} case REFUSE_QUEST_SIMPLE: {
				        return closeDialogWindow(env);
					} case STEP_TO_1: {
                        changeQuestStep(env, 0, 1, false);
						return closeDialogWindow(env);
					}
                }
            } if (targetId == 834035) { //로라코.
                switch (env.getDialog()) {
                    case START_DIALOG: {
                        return sendQuestDialog(env, 1352);
					} case STEP_TO_2: {
                        changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
                }
            }
        } else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806079) { //Feregran.
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
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = env.getTargetId();
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var == 2) {
				int[] IDLF1TBarricadeDragon01 = {703290}; //어두운 포자길 철책.
				int[] IDLF1TBarricadeDragon03 = {703292}; //트몰리아 폐광 입구 철책.
				switch (targetId) {
					case 703290: { //어두운 포자길 철책.
						return defaultOnKillEvent(env, IDLF1TBarricadeDragon01, 0, 1, 1);
					} case 703292: { //트몰리아 폐광 입구 철책.
						qs.setQuestVar(3);
					    updateQuestStatus(env);
						return defaultOnKillEvent(env, IDLF1TBarricadeDragon03, 0, 1, 2);
					}
				}
			} else if (var == 3) {
				switch (targetId) {
                    case 243683: { //군단장 타하바타.
						qs.setQuestVar(4);
						updateQuestStatus(env);
						return true;
					}
                }
			} else if (var == 4) {
				switch (targetId) {
                    case 243684: { //아티팩트를 지배하는 크로반.
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(env);
						return true;
					}
                }
			}
		}
		return false;
	}
}