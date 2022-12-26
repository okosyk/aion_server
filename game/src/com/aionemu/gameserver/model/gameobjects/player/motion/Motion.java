package com.aionemu.gameserver.model.gameobjects.player.motion;

import java.util.HashMap;
import java.util.Map;

import com.aionemu.gameserver.model.IExpirable;
import com.aionemu.gameserver.model.gameobjects.player.Player;

public class Motion implements IExpirable
{
	static final Map<Integer, Integer> motionType = new HashMap<Integer, Integer>();
	static {
		motionType.put(1, 1);
		motionType.put(2, 2);
		motionType.put(3, 3);
		motionType.put(4, 4);
		////////////////////
		motionType.put(5, 1);
		motionType.put(6, 2);
		motionType.put(7, 3);
		motionType.put(8, 4);
		////////////////////
		//Motion 3.9
		motionType.put(10, 1);
		motionType.put(19, 2);
		////////////////////
		//Motion 4.5
		motionType.put(11, 1);
		motionType.put(12, 2);
		motionType.put(13, 3);
		motionType.put(14, 4);
		////////////////////
		//Motion 4.7
		motionType.put(15, 1);
		motionType.put(16, 2);
		motionType.put(17, 3);
		motionType.put(18, 4);
		motionType.put(20, 1);
		////////////////////
		motionType.put(21, 1);
		motionType.put(22, 2);
		////////////////////
		//Motion 4.8
		motionType.put(23, 1);
		motionType.put(24, 2);
		motionType.put(25, 3);
		motionType.put(26, 4);
		////////////////////
		//Motion 5.0
		motionType.put(27, 1);
		motionType.put(28, 2);
		motionType.put(29, 3);
		motionType.put(30, 4);
		//Shop 2
		motionType.put(31, 1);
		//CHN Vip Shop 5.1
		motionType.put(32, 1);
	}
	
	private int id;
	private int deletionTime = 0;
	private boolean active = false;
	
	public Motion(int id, int deletionTime, boolean isActive) {
		this.id = id;
		this.deletionTime = deletionTime;
		this.active = isActive;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRemainingTime() {
		if (deletionTime == 0) {
			return 0;
		}
		return deletionTime-(int)(System.currentTimeMillis()/1000);
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public int getExpireTime() {
		return deletionTime;
	}
	
	@Override
	public void expireEnd(Player player) {
		player.getMotions().remove(id);
	}
	
	@Override
	public void expireMessage(Player player, int time) {
	}
	
	@Override
	public boolean canExpireNow() {
		return true;
	}
}