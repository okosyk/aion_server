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
package quest.gelkmaros;

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

public class _21250SpyLeague_Greatsword_Fate extends QuestHandler
{
	private final static int questId = 21250;
	
	//Guardian General Temple Of Scales.
	//Guardian General Altar Of Avarice.
	private final static int[] mob_ids = {257300, 257312};
	
	public _21250SpyLeague_Greatsword_Fate() {
		super(questId);
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(799340).addOnQuestStart(questId); //Athana.
		qe.registerQuestNpc(799340).addOnTalkEvent(questId); //Athana.
		for (int mob_id: mob_ids)
		qe.registerQuestNpc(mob_id).addOnKillEvent(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		if (env.getTargetId() == 799340) { //Athana.
			if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
				switch (dialog) {
					case START_DIALOG:
						return sendQuestDialog(env, 4762);
					case ASK_ACCEPTION:
					    return sendQuestDialog(env, 4);
					case ACCEPT_QUEST:
					    QuestService.startQuest(env);
					    return sendQuestDialog(env, 1003);
				}
			} else if (qs.getStatus() == QuestStatus.REWARD) {
			    if (env.getTargetId() == 799340) { //Athana.
				    if (env.getDialog() == QuestDialog.START_DIALOG)
					    return sendQuestDialog(env, 5);
				    else if (env.getDialogId() == 1009)
					    return sendQuestDialog(env, 5);
				    else
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
		if (qs == null || qs.getStatus() != QuestStatus.START)
			return false;
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		switch (targetId) {
			case 257300: //Guardian General Temple Of Scales.
			case 257312: //Guardian General Altar Of Avarice.
			if (qs.getQuestVarById(0) < 1) {
				qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
				qs.setStatus(QuestStatus.REWARD);
				updateQuestStatus(env);
				return true;
			}
			break;
		}
		return false;
	}
}