package com.xorrad.tnt.utils.bonus;

import org.bukkit.entity.Player;

public class Null extends Bonus{
	
	public Null() {
		super("null",  0L);
	}

	@Override
	public void effect(Player p) {
	}

	@Override
	public void disable(Player p) {
	}

}
