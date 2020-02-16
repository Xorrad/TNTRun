package com.xorrad.tnt.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.xorrad.tnt.TNTRun;
import com.xorrad.tnt.utils.title.Title;

public class Game {
	
	static Game instance;
	public static Game getInstance(){ return instance; }
	static int time = 4;
	
	public TNTRun main;
	
	public Game() {
		instance = this;
		main = TNTRun.getInstance();
	}
	
	public BukkitTask gametick;
	
	@SuppressWarnings("deprecation")
	public void startGame(){
		for(Player pls : Bukkit.getOnlinePlayers()){
			pls.teleport(main.getSpawn());
			Title.sendTitle(pls, 10, 1, 10, "§aLancement de la partie !");
			spawnRocket(pls);
		}
		
		main.setState(GameState.STARTING);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(time == 0){
					main.setState(GameState.START);
					for(Player pls : Bukkit.getOnlinePlayers()){
						pls.playSound(pls.getLocation(), Sound.AMBIENCE_THUNDER, 100, 100);
					}
					this.cancel();
				}	
				for(Player pls : Bukkit.getOnlinePlayers()){
					Title.sendTitle(pls, 10, 1, 10, "§aDebut de la partie dans §7" + time + "§as !");
					pls.playSound(pls.getLocation(), Sound.LEVEL_UP, 20, 10);
				}
				time--;
			}
		}.runTaskTimer(main, 20, 20);
	}
	
	public void stopGame(){	
		TNTRun.getInstance().unloadWorld();			
	}
	
	public void endGame(){
		main.setState(GameState.END);
		String v = ChatColor.GREEN + "V" + ChatColor.RED + "i" + ChatColor.BLUE + "c" + ChatColor.GOLD + "t" + ChatColor.DARK_AQUA + "o" + ChatColor.LIGHT_PURPLE + "i" + ChatColor.DARK_GREEN + "r" + ChatColor.YELLOW + "e" + ChatColor.DARK_RED + " !";
		for(Player pls : Bukkit.getOnlinePlayers()){
			if(pls != main.alive.get(0)){
				pls.teleport(main.alive.get(0));
			}
			Title.sendTitle(pls, 10, 200, 10, v, "§a!§c!§e!§d! §6" + main.alive.get(0).getName() + "§d!§e!§c!§a!");
		}
		
		time = 10*5;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(time==0){
					stopGame();
					this.cancel();
				}
				if(main.alive.get(0) != null)
				{
					spawnRocket(main.alive.get(0));
				}
				
				//System.out.println(time);
				time--;
			}
		}.runTaskTimer(main, 5, 5);
		
	}
	
	public void gameTick(){
		gametick = new BukkitRunnable() {
			
			@Override
			public void run() {
				
			}
		}.runTaskTimer(main, 20, 20);
	}
	
	public void spawnRocket(Player p){
	    Random r = new Random();   
		Location l = p.getLocation();
		l.setY(l.getY()-((r.nextInt(1)==0) ? 1 : 2));
		l.setX(l.getX() + ((r.nextInt(1)==0) ? r.nextInt(2) : -r.nextInt(2)));
		l.setZ(l.getZ() + ((r.nextInt(1)==0) ? r.nextInt(2) : -r.nextInt(2)));
        Firework fw = (Firework) p.getWorld().spawnEntity(l, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        //Get the type
        int rt = r.nextInt(4) + 1;
        Type type = Type.BALL;       
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
        if (rt == 5) type = Type.STAR;
       
        //Get our random colours   
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);
       
        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
       
        //Then apply the effect to the meta
        fwm.addEffect(effect);
       
        //Generate some random power and set it
        fwm.setPower(1);
       
        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);   
	}
	
	private Color getColor(int i) {
		Color c = null;
		if(i==1){
			c=Color.AQUA;
		}
		if(i==2){
			c=Color.BLACK;
		}
		if(i==3){
			c=Color.BLUE;
		}
		if(i==4){
			c=Color.FUCHSIA;
		}
		if(i==5){
			c=Color.GRAY;
		}
		if(i==6){
			c=Color.GREEN;
		}
		if(i==7){
			c=Color.LIME;
		}
		if(i==8){
			c=Color.MAROON;
		}
		if(i==9){
			c=Color.NAVY;
		}
		if(i==10){
			c=Color.OLIVE;
		}
		if(i==11){
			c=Color.ORANGE;
		}
		if(i==12){
			c=Color.PURPLE;
		}
		if(i==13){
			c=Color.RED;
		}
		if(i==14){
			c=Color.SILVER;
		}
		if(i==15){
			c=Color.TEAL;
		}
		if(i==16){
			c=Color.WHITE;
		}
		if(i==17){
			c=Color.YELLOW;
		}
		 
		return c;
		}
	//Unloading maps, to rollback maps. Will delete all player builds until last server save
    public static void unloadMap(String mapname){    	
    	World w = Bukkit.getWorld(mapname);
    	for(org.bukkit.Chunk c : w.getLoadedChunks()){
    		c.unload(false);
    	}
        if(Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(mapname), false)){
          System.out.println("Successfully unloaded " + mapname);
        }else{
        	System.out.println("COULD NOT UNLOAD " + mapname);
        }
    }
    //Loading maps (MUST BE CALLED AFTER UNLOAD MAPS TO FINISH THE ROLLBACK PROCESS)
    public static void loadMap(String mapname){
        Bukkit.getServer().createWorld(new WorldCreator(mapname));
    }
 
    //Maprollback method, because were too lazy to type 2 lines
    public static void rollback(String mapname){
    	for(Player pls : Bukkit.getOnlinePlayers()){
			pls.kickPlayer("§cFin de la partie !");
		}
        unloadMap(mapname);
        loadMap(mapname);
    }

}
