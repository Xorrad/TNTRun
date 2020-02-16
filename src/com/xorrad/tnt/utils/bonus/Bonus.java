package com.xorrad.tnt.utils.bonus;

import org.bukkit.entity.Player;

public abstract class Bonus {
	
	public String name;
	public Long time;
	
	public Bonus(String name, Long time) {
		this.name = name;
		this.time = time;
	}
	
	public abstract void effect(Player p);
	
	public abstract void disable(Player p );

}
