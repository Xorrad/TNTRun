package com.xorrad.tnt.utils.bonus;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Speed extends Bonus{
	
	public Speed() {
		super("Speed",  5*20L);
	}
	
	@Override
	public void effect(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 1));
	}
	
	@Override
	public void disable(Player p) {
		p.removePotionEffect(PotionEffectType.SPEED);
	}

}
