package com.niccholaspage.nVerse.command.commands;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.SubCommand;

public class CreateWorldCommand extends SubCommand {
	private final nVerse plugin;

	public CreateWorldCommand(nVerse plugin) {
		super("createworld", Phrase.COMMAND_CREATEWORLD, "[name] [environment]");

		this.plugin = plugin;
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 2){
			return false;
		}
		
		String name = args[0];
		
		World world = plugin.getServer().getWorld(name);
		
		if (world != null){
			Phrase.WORLD_ALREADY_EXISTS.sendWithPrefix(sender);
			
			return true;
		}

		WorldCreator creator = new WorldCreator(name);
		
		world = plugin.getAPI().createWorld(creator);
		
		Phrase.YOU_HAVE_CREATED_WORLD.sendWithPrefix(sender, world.getName());

		return true;
	}
}
