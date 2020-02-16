package com.xorrad.tnt.utils.bonus;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Invis extends Bonus{
	
	public Invis() {
		super("Invisibilité",  5*20L);
	}
	
	@Override
	public void effect(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5*20, 1));
	}
	
	@Override
	public void disable(Player p) {
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
	}

}
