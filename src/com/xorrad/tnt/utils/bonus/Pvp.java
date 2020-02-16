package com.xorrad.tnt.utils.bonus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.xorrad.tnt.TNTRun;

public class Pvp extends Bonus{
	
	public Pvp() {
		super("PVP Enabled",  10*20L);
	}
	
	@Override
	public void effect(Player p) {
		TNTRun.getInstance().PVP = true;
		
		Bukkit.broadcastMessage("§aPVP Enabled !");
	}
	
	@Override
	public void disable(Player p) {
		TNTRun.getInstance().PVP = false;
	}

}
