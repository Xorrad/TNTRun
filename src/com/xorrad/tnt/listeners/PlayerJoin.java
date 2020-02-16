package com.xorrad.tnt.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.xorrad.tnt.TNTRun;
import com.xorrad.tnt.utils.GameState;
import com.xorrad.tnt.utils.bonus.Null;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		TNTRun main = TNTRun.getInstance();
		
		if(main.getState().equals(GameState.HUB)){
			e.setJoinMessage("§7" + p.getName() + " §aa rejoint la partie !");
			//p.sendMessage("Location: " + main.getSpawn().toString());
			p.teleport(main.getSpawn());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.updateInventory();		
			
			main.alive.add(p);
			main.bonus.put(p, new Null());
			main.brun.put(p, new BukkitRunnable() {
				
				@Override
				public void run() {
					
				}
			}.runTask(main));
			
		} else {
			main.addSpec(p);
		}
	}

}
