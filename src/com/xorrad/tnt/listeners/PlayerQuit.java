package com.xorrad.tnt.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.xorrad.tnt.TNTRun;
import com.xorrad.tnt.utils.GameState;

public class PlayerQuit implements Listener{
	
	@EventHandler
	public void onLeft(PlayerQuitEvent e){
		TNTRun main = TNTRun.getInstance();
		Player p = e.getPlayer();
		
		if(main.alive.contains(p)){
			e.setQuitMessage("§7" + p.getName() + " §Ca quitté la partie !");
			main.alive.remove(p);
			
			if(main.alive.size() <= 1 && main.getState().equals(GameState.START)){
				main.getGame().endGame();
			}
		} else if(main.specs.contains(p)){
			main.specs.remove(p);
		}
	}

}
