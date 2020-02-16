package com.xorrad.tnt.utils.bonus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Nosee extends Bonus{
	
	public Nosee() {
		super("Nausée",  5*20L);
	}
	
	@Override
	public void effect(Player p) {
		for(Player pls : Bukkit.getOnlinePlayers())
		{
			if(pls != p)
			{
				pls.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5*20, 0));
			}
		}
	}
	
	@Override
	public void disable(Player p) {
		for(Player pls : Bukkit.getOnlinePlayers())
		{
			if(pls != p)
			{
				pls.removePotionEffect(PotionEffectType.CONFUSION);
			}
		}
	}

}
