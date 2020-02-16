package com.xorrad.tnt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.xorrad.tnt.TNTRun;

public class EndCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		TNTRun main = TNTRun.getInstance();
		main.getGame().endGame();
		
		return false;
	}

}
