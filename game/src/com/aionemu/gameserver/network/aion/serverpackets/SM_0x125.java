package com.aionemu.gameserver.network.aion.serverpackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_0x125 extends AionServerPacket {

	private int unk;
	private static final Logger log = LoggerFactory.getLogger(SM_0x125.class);
	
	public SM_0x125(int unk) {
		this.unk = unk;
	}
	
	@Override
    protected void writeImpl(AionConnection con) {
		writeH(unk);
		log.info("SM_0x125 : "+unk);
	}
}
