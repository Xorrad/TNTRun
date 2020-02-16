package com.xorrad.tnt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.xorrad.tnt.commands.EndCommand;
import com.xorrad.tnt.commands.StartCommand;
import com.xorrad.tnt.commands.StopCommand;
import com.xorrad.tnt.listeners.BlockBreak;
import com.xorrad.tnt.listeners.FoodLeft;
import com.xorrad.tnt.listeners.PlayerDamage;
import com.xorrad.tnt.listeners.PlayerJoin;
import com.xorrad.tnt.listeners.PlayerMove;
import com.xorrad.tnt.listeners.PlayerQuit;
import com.xorrad.tnt.utils.Game;
import com.xorrad.tnt.utils.GameState;
import com.xorrad.tnt.utils.SavedBlock;
import com.xorrad.tnt.utils.bonus.Bonus;
import com.xorrad.tnt.utils.bonus.Fly;
import com.xorrad.tnt.utils.bonus.Invis;
import com.xorrad.tnt.utils.bonus.NoFall;
import com.xorrad.tnt.utils.bonus.Nosee;
import com.xorrad.tnt.utils.bonus.Null;
import com.xorrad.tnt.utils.bonus.Pvp;
import com.xorrad.tnt.utils.bonus.Speed;

public class TNTRun extends JavaPlugin{
	
	public GameState state;
	public GameState getState(){ return state; }
	static TNTRun instance;
	public static TNTRun getInstance(){ return instance; }
	public Game game;
	public Game getGame(){ return game; }
	
	public Location spawn;
	public Location getSpawn(){ return spawn; }
	
	public ArrayList<Player> alive;
	public ArrayList<Player> specs;
	public ArrayList<Bonus> blist;
	public HashMap<Player, Bonus> bonus;
	public HashMap<Player, BukkitTask> brun;
	
	public ArrayList<SavedBlock> mapRestore;
	
	public boolean PVP = false;
	
	@Override
	public void onLoad() {	
		
	}
	
	@Override
	public void onEnable() {	
		
		instance = this;
		state = GameState.HUB;
		game = new Game();
		spawn = getMapSpawn();	
		alive = new ArrayList<>();
		specs = new ArrayList<>();
		blist = new ArrayList<>();
		mapRestore = new ArrayList<>();
		blist.add(new Fly());
		blist.add(new Invis());
		blist.add(new NoFall());
		blist.add(new Pvp());
		blist.add(new Nosee());
		blist.add(new Speed());
		blist.add(new Null());
		bonus = new HashMap<>();
		brun = new HashMap<>();
	
		registerListeners();
		registerCommands();
		
		for(Player p : Bukkit.getOnlinePlayers()){
			p.teleport(getSpawn());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.updateInventory();		
			
			alive.add(p);
			bonus.put(p, new Null());
			brun.put(p, new BukkitRunnable() {
				
				@Override
				public void run() {
					
				}
			}.runTask(this));
			
			this.game.spawnRocket(p);
		}
	}
	
	@Override
	public void onDisable() {		
		unloadWorld();
	}
	
	private void registerListeners(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new FoodLeft(), this);
		pm.registerEvents(new PlayerDamage(), this);
	}
	
	private void registerCommands(){
		getCommand("start_game").setExecutor(new StartCommand());
		getCommand("stop_game").setExecutor(new StopCommand());
		getCommand("end_game").setExecutor(new EndCommand());
	}
	
	public Location getMapSpawn(){
		Random ran = new Random();
		ArrayList<Location> locs = new ArrayList<>();
		locs.add(new Location(Bukkit.getWorlds().get(0), 0, 51, 0));
		locs.add(new Location(Bukkit.getWorlds().get(0), 1.5, 21, 42.5)); 
		locs.add(new Location(Bukkit.getWorlds().get(0), 28, 56, 0));
		locs.add(new Location(Bukkit.getWorlds().get(0), -39, 63, 44));
		locs.add(new Location(Bukkit.getWorlds().get(0), 18, 51, -22));
		locs.add(new Location(Bukkit.getWorlds().get(0), -5, 47, -74));
		//locs.add(new Location(Bukkit.getWorlds().get(0), -30, 71, -2));
		
		int n = ran.nextInt(locs.size());
		return locs.get(n);
	}
	
	@SuppressWarnings("deprecation")
	public void unloadWorld(){
		for(SavedBlock b : mapRestore)
		{
			b.getLocation().getBlock().setType(b.getMaterial());
			b.getLocation().getBlock().setData(b.getData());
		}
		
		Bukkit.reload();
	}

	public ArrayList<Player> getAlive() {
		return alive;
	}

	public ArrayList<Player> getSpecs() {
		return specs;
	}

	public void setState(GameState state) {
		this.state = state;
	}
	
	public void addSpec(Player p){
		specs.add(p);
		bonus.put(p, blist.get(3));			
		p.setGameMode(GameMode.SPECTATOR);
		p.setAllowFlight(true);
		p.teleport(getSpawn());
		p.getInventory().clear();
		p.updateInventory();		
		//p.setPlayerListName("§7" + p.getName());
	}

}
