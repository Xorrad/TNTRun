package com.xorrad.tnt.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLeft implements Listener{
	
	@EventHandler
	public void onLeft(FoodLevelChangeEvent e){
		Player p = (Player) e.getEntity();
		e.setCancelled(true);
		p.setFoodLevel(20);		
	}

}
