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

		commands.add(new CreateCommand(plugin));
		commands.add(new GotoCommand(plugin));
		commands.add(new HelpCommand(this));
		commands.add(new ListCommand(plugin));
		commands.add(new RemoveCommand(plugin));
		commands.add(new ReloadCommand(plugin));
	}

	private SubCommand getCommand(String name){
		for (SubCommand command : commands){
			String[] names = command.getName().split(",");
			
			for (String commandName : names){
				if (commandName.equalsIgnoreCase(name)){
					return command;
				}
			}
		}

		return null;
	}
	
	public Set<SubCommand> getCommands(){
		return commands;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 1){
			args = new String[]{"help"};
		}

		SubCommand command = getCommand(args[0]);

		if (command == null){
			command = getCommand("help");
		}

		if (!sender.hasPermission("nVerse.command." + command.getPermission())){
			Phrase.YOU_DO_NOT_HAVE_PERMISSION_TO_USE_THIS_COMMAND.sendWithPrefix(sender);

			return true;
		}
		
		if (command.getType() == CommandType.PLAYER && !(sender instanceof Player)){
			Phrase.COMMAND_NOT_CONSOLE.sendWithPrefix(sender, args[0].toLowerCase());

			return true;
		}

		String[] realArgs = new String[args.length - 1];

		for (int i = 1; i < args.length; i++){
			realArgs[i - 1] = args[i];
		}

		if (!command.run(sender, cmd, commandLabel, realArgs)){
			Phrase.TRY_COMMAND.sendWithPrefix(sender, "/" + commandLabel.toLowerCase() + " " + args[0].toLowerCase() + " " + command.getHelp());
		}

		return true;
	}
}
