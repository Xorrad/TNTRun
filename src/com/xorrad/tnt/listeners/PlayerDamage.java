package com.xorrad.tnt.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.xorrad.tnt.TNTRun;

public class PlayerDamage implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			if(e.getCause().equals(DamageCause.ENTITY_ATTACK))
			{
				e.setDamage(0.0);
				if(!TNTRun.getInstance().PVP)
				{
					e.setCancelled(true);
				}
			}
			else
			{
				e.setCancelled(true);
			}
		}
	}

}
