package com.xorrad.tnt.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.xorrad.tnt.TNTRun;
import com.xorrad.tnt.utils.GameState;
import com.xorrad.tnt.utils.SavedBlock;
import com.xorrad.tnt.utils.bonus.Null;
import com.xorrad.tnt.utils.title.Title;

public class PlayerMove implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		TNTRun main = TNTRun.getInstance();
		Player p = e.getPlayer();
		Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()-1, p.getLocation().getZ());
		Location l2 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()-2, p.getLocation().getZ());
		Location l3 = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()-3, p.getLocation().getZ());
		
		if(!main.getState().equals(GameState.START)){
			return;
		}
		if(!main.alive.contains(p)){
			return;
		}
		
		if(p.getLocation().getY() <= 0){ //&& main.alive.size() > 1
			main.addSpec(p);			
			main.alive.remove(p);			
			Title.sendTitle(p, 10, 5, 10, "§cVous etes mort !", "Il restait §7" + main.alive.size() + " joueur(s) en vie.");
			if(main.alive.size() <= 1){
				main.getGame().endGame();
			}
			p.getWorld().strikeLightning(p.getLocation());
			Bukkit.broadcastMessage("§e" + p.getName() + " §aest mort, mais vraiment comme un gros nul !");
		}
		
		if(l.getBlock().getType().equals(Material.SEA_LANTERN) || l2.getBlock().getType().equals(Material.SEA_LANTERN)){
			
			if(main.bonus.get(p) instanceof Null)
			{
				main.brun.get(p).cancel();
				Random ran = new Random();
				int n = ran.nextInt(main.blist.size()-1);
				main.bonus.get(p).disable(p);
				main.bonus.put(p, main.blist.get(n));
				Title.sendTitle(p, 10, 1, 10, "§aVous avez eu le bonus: §7" + main.bonus.get(p).name);
				main.bonus.get(p).effect(p);
				
				main.brun.put(p, new BukkitRunnable() {
					
					@Override
					public void run() {
						main.bonus.get(p).disable(p);
						main.bonus.put(p, new Null());
						this.cancel();
					}
				}.runTaskTimer(main, main.bonus.get(p).time, main.bonus.get(p).time));
			}
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if(!l.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l.getBlock().getState()));
						l.getBlock().setType(Material.AIR);
					}
					if(!l2.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l2.getBlock().getState()));
						l2.getBlock().setType(Material.AIR);
					}
					if(!l3.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l3.getBlock().getState()));
						l3.getBlock().setType(Material.AIR);
					}
				}
			}.runTaskTimer(main, 7, 7);
			
		} else if(!main.bonus.get(p).name.equalsIgnoreCase("NoFall") && !main.bonus.get(p).name.equalsIgnoreCase("Fly")){
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if(!l.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l.getBlock().getState()));
						l.getBlock().setType(Material.AIR);
					}
					if(!l2.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l2.getBlock().getState()));
						l2.getBlock().setType(Material.AIR);
					}
					if(!l3.getBlock().getType().equals(Material.AIR))
					{
						TNTRun.getInstance().mapRestore.add(new SavedBlock(l3.getBlock().getState()));
						l3.getBlock().setType(Material.AIR);
					}
				}
			}.runTaskTimer(main, 7, 7);
		}
	}

}
