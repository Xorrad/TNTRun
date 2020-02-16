package com.xorrad.tnt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.xorrad.tnt.TNTRun;
import com.xorrad.tnt.utils.GameState;

public class StartCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		TNTRun main = TNTRun.getInstance();
		if(main.getState().equals(GameState.HUB)){
			main.getGame().startGame();
		} else {
			sender.sendMessage("§cLa partie est deja lancé !");
		}
		
		return false;
	}

}
