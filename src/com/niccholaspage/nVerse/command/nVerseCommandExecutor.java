package com.niccholaspage.nVerse.command;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.commands.*;

public class nVerseCommandExecutor implements CommandExecutor {
	private final Set<SubCommand> commands;

	public nVerseCommandExecutor(nVerse plugin){
		commands = new HashSet<SubCommand>();

		commands.add(new CreateWorldCommand(plugin));
		commands.add(new GotoCommand(plugin));
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

		if (!sender.hasPermission("nVerse.command." + command.getPermission())){
			Phrase.YOU_DO_NOT_HAVE_PERMISSION_TO_USE_THIS_COMMAND.sendWithPrefix(sender);

			return true;
		}

		//TODO: Console args
		if (command.getType() == CommandType.PLAYER && !(sender instanceof Player)){
			Phrase.COMMAND_NOT_CONSOLE.sendWithPrefix(sender, command.getName());

			return true;
		}

		String[] realArgs = new String[args.length - 1];

		for (int i = 1; i < args.length; i++){
			realArgs[i - 1] = args[i];
		}

		command.run(sender, cmd, commandLabel, realArgs);

		return true;
	}
}
