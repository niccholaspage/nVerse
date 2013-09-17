package com.niccholaspage.nVerse.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;

public abstract class SubCommand implements CommandExecutor {
	private final String name;

	private final Phrase description;

	private final String permission;
	
	public SubCommand(String name, Phrase description){
		this(name, description, name);
	}

	public SubCommand(String name, Phrase description, String permission){
		this.name = name;

		this.description = description;

		this.permission = permission;
	}
	
	public String getName(){
		return name;
	}
	
	public Phrase getDescription(){
		return description;
	}
	
	public String getPermission(){
		return permission;
	}

	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (!sender.hasPermission("nVerse.command." + getPermission())){
			Phrase.YOU_DO_NOT_HAVE_PERMISSION_TO_USE_THIS_COMMAND.sendWithPrefix(sender);
			
			return true;
		}
		
		if (!run(sender, cmd, commandLabel, args)){
			
		}
		
		return true;
	}
	
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return true;
	}
}
