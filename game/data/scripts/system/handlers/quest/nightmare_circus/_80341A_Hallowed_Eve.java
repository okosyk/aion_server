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
package quest.nightmare_circus;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

/****/
/** Author Rinzler (Encom)
/****/

public class _80341A_Hallowed_Eve extends QuestHandler
{
    private final static int questId = 80341;
	
    public _80341A_Hallowed_Eve() {
        super(questId);
    }
	
	@Override
	public void register() {
		//Otherworldly Pucas.
		qe.registerQuestNpc(831541).addOnQuestStart(questId);
		qe.registerQuestNpc(831542).addOnQuestStart(questId);
		qe.registerQuestNpc(831543).addOnQuestStart(questId);
		qe.registerQuestNpc(831544).addOnQuestStart(questId);
		qe.registerQuestNpc(831545).addOnQuestStart(questId);
		qe.registerQuestNpc(831546).addOnQuestStart(questId);
		qe.registerQuestNpc(831547).addOnQuestStart(questId);
		qe.registerQuestNpc(831548).addOnQuestStart(questId);
		qe.registerQuestNpc(831541).addOnTalkEvent(questId); 
		qe.registerQuestNpc(831542).addOnTalkEvent(questId);
		qe.registerQuestNpc(831543).addOnTalkEvent(questId);
		qe.registerQuestNpc(831544).addOnTalkEvent(questId);
		qe.registerQuestNpc(831545).addOnTalkEvent(questId);
		qe.registerQuestNpc(831546).addOnTalkEvent(questId);
		qe.registerQuestNpc(831547).addOnTalkEvent(questId);
		qe.registerQuestNpc(831548).addOnTalkEvent(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			//Otherworldly Pucas.
			if (targetId == 831541 || targetId == 831542 ||
				targetId == 831543 || targetId == 831544 ||
				targetId == 831545 || targetId == 831546 ||
				targetId == 831547 || targetId == 831548) {
				switch (dialog) {
					case START_DIALOG:
						return sendQuestDialog(env, 1011);
					case ASK_ACCEPTION:
					    return sendQuestDialog(env, 4);
					case ACCEPT_QUEST:
					    QuestService.startQuest(env);
					    return sendQuestDialog(env, 1003);
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			//Otherworldly Pucas.
			switch (targetId) {
				case 831541:
				case 831542:
				case 831543:
				case 831544:
				case 831545:
				case 831546:
				case 831547:
				case 831548: {
					switch (dialog) {
						case START_DIALOG: {
							return sendQuestDialog(env, 2375);
						} case SELECT_REWARD: {
							changeQuestStep(env, 0, 0, true);
							return sendQuestEndDialog(env);
						}
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
		    //Otherworldly Pucas.
			if (targetId == 831541 || targetId == 831542 ||
				targetId == 831543 || targetId == 831544 ||
				targetId == 831545 || targetId == 831546 ||
				targetId == 831547 || targetId == 831548) {
			    return sendQuestEndDialog(env);
		    }
		}
		return false;
	}
}