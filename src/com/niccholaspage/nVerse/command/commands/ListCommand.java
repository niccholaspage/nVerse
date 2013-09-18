package com.niccholaspage.nVerse.command.commands;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.SubCommand;

public class ListCommand extends SubCommand {
	private final nVerse plugin;

	public ListCommand(nVerse plugin) {
		super("list", Phrase.COMMAND_LIST, "");

		this.plugin = plugin;
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		int current = 1;
		
		String two = Phrase.SECONDARY_COLOR.parse();
		
		for (World world : plugin.getServer().getWorlds()){
			sender.sendMessage(two + current + ". " + Phrase.PRIMARY_COLOR.parse() + world.getName() + two + " - " + getEnvironment(world) + ", " + getWorldType(world));
			
			current += 1;
		}

		return true;
	}
	
	private String getEnvironment(World world){
		Environment environment = world.getEnvironment();
		
		if (Environment.THE_END.equals(environment)){
			return "The End";
		}
		
		String message = environment.name();
		
		message = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
		
		return message;
	}
	
	private String getWorldType(World world){
		WorldType type = world.getWorldType();
		
		String message = type.getName();
		
		message = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
		
		return message;
	}
}
