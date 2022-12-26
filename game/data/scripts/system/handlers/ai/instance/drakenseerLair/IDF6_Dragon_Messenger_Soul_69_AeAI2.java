/*
 * This file is part of aion-lightning <aion-lightning.com>.
 *
 *  aion-lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-lightning.  If not, see <http://www.gnu.org/licenses/>.
 */
package ai.instance.drakenseerLair;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.*;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("IDF6_Dragon_Messenger_Soul_69_Ae")
public class IDF6_Dragon_Messenger_Soul_69_AeAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		startEvent();
	}
	
	private void startEvent() {
		switch (getNpcId()) {
			case 220451: //IDF6_Dragon_Messenger_Soul_69_Ae.
				//You shame us, Bakarma! Fallen to a flock of weak-fleshed Daevas? Your honor is in shambles.
				sendMsg(1403082, getObjectId(), false, 2000);
				//The Balaur have ignited the Fire of Eternity in Drakenseer's Lair. Approach the flames at your peril.
				sendMsg(1403385, getObjectId(), false, 6000);
				//Seeing all enemies slain, the Balaur exits defensive stance.
				sendMsg(1403085, getObjectId(), false, 10000);
				//Hath our fated plans fallen prey to sabatoge? Impede the intruders, Bakarma. Our Lord depends upon it!
				sendMsg(1403067, getObjectId(), false, 14000);
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						AI2Actions.deleteOwner(IDF6_Dragon_Messenger_Soul_69_AeAI2.this);
				    }
			    }, 18000);
			break;
		}
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}