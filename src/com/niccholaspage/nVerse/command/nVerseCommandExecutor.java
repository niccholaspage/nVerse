package com.niccholaspage.nVerse.command;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.command.commands.CreateWorldCommand;

public class nVerseCommandExecutor implements CommandExecutor {
	private final Set<SubCommand> commands;
	
	public nVerseCommandExecutor(){
		commands = new HashSet<SubCommand>();
		
		commands.add(new CreateWorldCommand());
	}
	
	private SubCommand getCommand(String name){
		for (SubCommand command : commands){
			if (command.getName().equalsIgnoreCase(name)){
				return command;
			}
		}
		
		return null;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 1){
			//TODO: Display help
			
			return true;
		}
		
		SubCommand command = getCommand(args[0]);
		
		if (command == null){
			//TODO: Display help
			
			return true;
		}
		
		command.onCommand(sender, cmd, commandLabel, args);
		
		return true;
	}
}
