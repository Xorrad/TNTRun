package com.xorrad.tnt.utils.bonus;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoFall extends Bonus{
	
	public NoFall() {
		super("NoFall",  5*20L);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void effect(Player p) {
		p.playEffect(p.getLocation(), Effect.MAGIC_CRIT, 1);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5*20, 0));
	}
	
	@Override
	public void disable(Player p) {
		p.removePotionEffect(PotionEffectType.SLOW);
	}

}
