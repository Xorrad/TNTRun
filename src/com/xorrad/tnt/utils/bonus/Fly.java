package com.xorrad.tnt.utils.bonus;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Fly extends Bonus {
	
	public Fly() {
		super("Fly", 3*20L);
	}
	
	@Override
	public void effect(Player p) {
		p.setAllowFlight(true);
	}
	
	@Override
	public void disable(Player p) {
		if(p.getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		p.setAllowFlight(false);
	}

}
