package com.xorrad.tnt.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.xorrad.tnt.TNTRun;

public class BlockBreak implements Listener{
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(e.getPlayer() == null){
			e.setCancelled(true);
			return;
		}
		Player p = e.getPlayer();
		
		if(p.getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		
		if(TNTRun.getInstance().alive.contains(p)){
			e.setCancelled(true);
		}
	}

}
