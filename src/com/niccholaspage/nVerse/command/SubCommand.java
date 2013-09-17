package com.niccholaspage.nVerse.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;

public abstract class SubCommand {
	private final String name;

	private final Phrase description;

	private final String permission;
	
	private final CommandType type;
	
	public SubCommand(String name, Phrase description){
		this(name, description, name, CommandType.CONSOLE);
	}
	
	public SubCommand(String name, Phrase description, CommandType type){
		this(name, description, name, type);
	}

	public SubCommand(String name, Phrase description, String permission, CommandType type){
		this.name = name;

		this.description = description;

		this.permission = permission;
		
		this.type = type;
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
	
	public CommandType getType(){
		return type;
	}
	
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return true;
	}
}
