package com.niccholaspage.nVerse.command.commands;

import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.SubCommand;

public class CreateWorldCommand extends SubCommand {
	private final nVerse plugin;

	public CreateWorldCommand(nVerse plugin) {
		super("createworld", Phrase.COMMAND_CREATEWORLD);

		this.plugin = plugin;
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 1){
			return false;
		}

		WorldCreator creator = new WorldCreator(args[0]);
		
		plugin.getServer().createWorld(creator);
		
		Phrase.YOU_HAVE_CREATED_WORLD.sendWithPrefix(sender, creator.name());

		return true;
	}
}
