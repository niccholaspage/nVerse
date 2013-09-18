package com.niccholaspage.nVerse.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;

public abstract class SubCommand {
	private final String name;

	private final Phrase description;

	private final String permission;
	
	private final String help;
	
	private final CommandType type;
	
	public SubCommand(String name, Phrase description, String help){
		this(name, description, name, CommandType.CONSOLE, help);
	}
	
	public SubCommand(String name, Phrase description, CommandType type, String help){
		this(name, description, name, type, help);
	}

	public SubCommand(String name, Phrase description, String permission, CommandType type, String help){
		this.name = name;

		this.description = description;

		this.permission = permission;
		
		this.help = help;
		
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDisplayName(){
		return name.split(",")[0];
	}
	
	public Phrase getDescription(){
		return description;
	}
	
	public String getPermission(){
		return permission;
	}
	
	public String getHelp(){
		return help;
	}
	
	public CommandType getType(){
		return type;
	}
	
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return true;
	}
}
