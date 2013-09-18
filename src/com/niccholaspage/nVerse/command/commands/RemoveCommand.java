package com.niccholaspage.nVerse.command.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.SubCommand;

public class RemoveCommand extends SubCommand {
	private final nVerse plugin;

	public RemoveCommand(nVerse plugin) {
		super("remove,delete", Phrase.COMMAND_GOTO, "[world]");

		this.plugin = plugin;
	}
	
	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 1){
			return false;
		}
		
		String name = args[0];
		
		World world = plugin.getServer().getWorld(name);
		
		if (world == null){
			Phrase.WORLD_DOES_NOT_EXIST.sendWithPrefix(sender);
			
			return true;
		}
		
		if (plugin.getAPI().isDefaultWorld(name)){
			Phrase.CANNOT_DELETE_DEFAULT_WORLDS.sendWithPrefix(sender);
			
			return true;
		}
		
		plugin.getServer().unloadWorld(world, true);
		
		plugin.getAPI().removeWorld(world);
		
		Phrase.WORLD_HAS_BEEN_DELETED.sendWithPrefix(sender, world.getName());
		
		return true;
	}
}
