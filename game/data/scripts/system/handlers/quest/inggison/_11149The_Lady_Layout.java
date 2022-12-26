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
package quest.inggison;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Rinzler (Encom)
/****/

public class _11149The_Lady_Layout extends QuestHandler
{
	private final static int questId = 11149;

	public _11149The_Lady_Layout() {
		super(questId);
	}
	
	public void register() {
		qe.registerQuestNpc(296491).addOnQuestStart(questId);
		qe.registerQuestNpc(296491).addOnTalkEvent(questId);
		qe.registerOnEnterZone(ZoneName.get("LF4_SENSORYAREA_Q11149A_206155_1_210050000"), questId);
		qe.registerOnEnterZone(ZoneName.get("LF4_SENSORYAREA_Q11149B_206156_1_210050000"), questId);
		qe.registerOnEnterZone(ZoneName.get("LF4_SENSORYAREA_Q11149C_206157_12_210050000"), questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		QuestDialog dialog = env.getDialog();
		int targetId = env.getTargetId();
		if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 296491) { 
				if (dialog == QuestDialog.START_DIALOG) {
					return sendQuestDialog(env, 4762);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 296491) {
				if (dialog == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 10002);
				}
				return sendQuestEndDialog(env);
			}
		}
		return false;
	}
	
	@Override
	public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
		Player player = env.getPlayer();
		if (player == null)
			return false;
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
  		    int var = qs.getQuestVarById(0);
			int var1 = qs.getQuestVarById(1);
			int var2 = qs.getQuestVarById(2);
			if (zoneName == ZoneName.get("LF4_SENSORYAREA_Q11149A_206155_1_210050000")) {
				if (var == 0) {
					qs.setQuestVarById(1, 1);
					updateQuestStatus(env);
					cheakReward(env);
					return true;
				}
			} else if (zoneName == ZoneName.get("LF4_SENSORYAREA_Q11149B_206156_1_210050000")) {
				if (var1 == 0) {
					qs.setQuestVarById(2, 1);
					updateQuestStatus(env);
					cheakReward(env);
					return true;
				}
			} else if (zoneName == ZoneName.get("LF4_SENSORYAREA_Q11149C_206157_12_210050000")) {
				if (var2 == 0) {
					qs.setQuestVarById(3, 1);
					updateQuestStatus(env);
					cheakReward(env);
					return true;
				}
			}
		}
		return false;
	}
	
	private void cheakReward(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		int var = qs.getQuestVarById(1);
		int var1 = qs.getQuestVarById(2);
		int var2 = qs.getQuestVarById(3);
		if (var == 1 && var1 == 1 && var2 == 1) {
			qs.setStatus(QuestStatus.REWARD);
			updateQuestStatus(env);
		}
	}
}