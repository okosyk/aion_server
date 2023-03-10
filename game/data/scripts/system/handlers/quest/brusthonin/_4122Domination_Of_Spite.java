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
package quest.brusthonin;

import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.*;
import com.aionemu.gameserver.questEngine.model.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.world.zone.*;

/****/
/** Author Rinzler (Encom)
/****/

public class _4122Domination_Of_Spite extends QuestHandler
{
	private final static int questId = 4122;
	
	public _4122Domination_Of_Spite() {
		super(questId);
	}
	
	@Override
	public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
		return defaultOnEnterZoneEvent(env, zoneName, ZoneName.get("ADMA_STRONGHOLD_INTERIOR_320130000"));
	}
	
	@Override
	public void register() {
		qe.registerQuestNpc(798155).addOnTalkEvent(questId);
		qe.registerQuestNpc(237245).addOnKillEvent(questId); //Suspicious Pot.
		qe.registerQuestNpc(237240).addOnKillEvent(questId); //Enthralled Gutorum.
		qe.registerQuestNpc(237241).addOnKillEvent(questId); //Enthralled Karemiwen.
		qe.registerQuestNpc(237243).addOnKillEvent(questId); //Enthralled Zeeturun.
		qe.registerQuestNpc(237244).addOnKillEvent(questId); //Enthralled Lannok.
		qe.registerQuestNpc(237239).addOnKillEvent(questId); //Death Reaper.
		qe.registerOnEnterZone(ZoneName.get("ADMA_STRONGHOLD_INTERIOR_320130000"), questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (env.getVisibleObject() instanceof Npc) {
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		} if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			if (targetId == 0) {
				switch (dialog) {
					case START_DIALOG:
						return sendQuestDialog(env, 4762);
					case ACCEPT_QUEST:
					case ACCEPT_QUEST_SIMPLE:
						return sendQuestStartDialog(env);
					case REFUSE_QUEST_SIMPLE:
				        return closeDialogWindow(env);
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			switch (targetId) {
				case 205225: {
					switch (dialog) {
						case START_DIALOG: {
							return sendQuestDialog(env, 10002);
						} case SELECT_REWARD: {
							return sendQuestEndDialog(env);
						} default:
							return sendQuestEndDialog(env);
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
		    if (targetId == 205225) {
			    switch (dialog) {
					case SELECT_REWARD: {
						return sendQuestDialog(env, 5);
					} default:
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
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var == 0) { //Suspicious Pot.
				return defaultOnKillEvent(env, 237245, 0, 1);
			} else if(var == 1) { //Enthralled Gutorum.
				return defaultOnKillEvent(env, 237240, 1, 2);
			} else if(var == 2) { //Enthralled Karemiwen.
				return defaultOnKillEvent(env, 237241, 2, 3);
			} else if(var == 3) { //Enthralled Zeeturun.
				return defaultOnKillEvent(env, 237243, 3, 4);
			} else if(var == 4) { //Enthralled Lannok.
				return defaultOnKillEvent(env, 237244, 4, 5);
			} else if(var == 5) { //Death Reaper.
				return defaultOnKillEvent(env, 237239, 5, true);
			}
		}
		return false;
	}
}