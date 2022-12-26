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
package ai.instance.shugoImperialTomb;

import com.aionemu.commons.network.util.ThreadPoolManager;

import com.aionemu.gameserver.ai2.*;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.utils.*;

/****/
/** Author Rinzler (Encom)
/****/

@AIName("Emperor_Admirer")
public class Emperor_AdmirerAI2 extends NpcAI2
{
	private boolean isInstanceDestroyed;
	
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		if (dialogId == 10000) {
			switch (getNpcId()) {
				case 831112: //Emperor's Admirer.
					startTombWaveC1();
					//Pillagers incoming. Guard the Emperor's Monument!
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1401584));
					spawn(831130, 452.43765f, 106.14462f, 212.20023f, (byte) 68); //Crown Prince's Monument.
					spawn(831250, 452.92874f, 85.73192f, 214.3359f, (byte) 75); //Imperial Obelisk.
					spawn(831251, 435.34854f, 120.64044f, 214.336f, (byte) 62); //Imperial Obelisk.
				break;
			}
		}
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		AI2Actions.deleteOwner(this);
		return true;
	}
	
	private void attackEvent(final Npc npc, float x, float y, float z, boolean despawn) {
		((AbstractAI) npc.getAi2()).setStateIfNot(AIState.WALKING);
		npc.setState(1);
		npc.getMoveController().moveToPoint(x, y, z);
		PacketSendUtility.broadcastPacket(npc, new SM_EMOTION(npc, EmotionType.START_EMOTE2, 0, npc.getObjectId()));
	}
	
	private void startTombWaveC1() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 10000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 20000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 40000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 50000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 70000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 80000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 100000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 110000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 130000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 140000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 160000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 170000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 190000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 200000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 220000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219522, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219522, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219522, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 230000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 250000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219523, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219523, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219523, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 260000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 280000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				attackEvent((Npc)spawn(219521, 425.89014f, 58.281246f, 222.14124f, (byte) 15), 451.03403f, 84.020836f, 214.33578f, false);
				attackEvent((Npc)spawn(219521, 419.153f, 91.08885f, 214.33856f, (byte) 9), 449.21487f, 104.769394f, 212.20023f, false);
				attackEvent((Npc)spawn(219521, 395.60565f, 117.61483f, 222.1441f, (byte) 1), 433.06375f, 120.66558f, 214.33475f, false);
			}
		}, 290000);
	}
	
	public void onInstanceDestroy() {
		isInstanceDestroyed = true;
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}