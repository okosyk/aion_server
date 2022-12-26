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
package ai.siege;

import ai.ActionItemNpcAI2;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.cache.HTMLCache;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.HTMLService;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("emptyaethericcannon")
public class EmptyAethericCannonAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		super.handleDialogStart(player);
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		Npc owner = getOwner();
		HTMLService.showHTML(player, HTMLCache.getInstance().getHTML("weapon_siege/weaponSiege.xhtml"));
		SkillEngine.getInstance().getSkill(player, 21385, 1, player).useNoAnimationSkill();
		AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}