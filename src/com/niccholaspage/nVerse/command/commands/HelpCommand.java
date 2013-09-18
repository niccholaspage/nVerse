package com.niccholaspage.nVerse.command.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.command.CommandType;
import com.niccholaspage.nVerse.command.SubCommand;
import com.niccholaspage.nVerse.command.nVerseCommandExecutor;

public class HelpCommand extends SubCommand {
	private final nVerseCommandExecutor command;
	
	public HelpCommand(nVerseCommandExecutor command){
		super("?,help", Phrase.COMMAND_HELP, "");
		
		this.command = command;
	}
	
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		String operatorColor = Phrase.TERTIARY_COLOR.parse();
		
		String textColor = Phrase.SECONDARY_COLOR.parse();
		
		sender.sendMessage(textColor + Phrase.HELP_ARGUMENTS.parse(operatorColor + "[]" + textColor, operatorColor + "()" + textColor));
		
		List<SubCommand> commands = new ArrayList<SubCommand>(command.getCommands());
		
		Collections.sort(commands, new Comparator<SubCommand>(){
			@Override
			public int compare(SubCommand command1, SubCommand command2) {
				return command1.getDisplayName().compareTo(command2.getDisplayName());
			}
		});
		
		for (SubCommand command : commands){
			if (command.getName().equalsIgnoreCase(getName())){
				continue;
			}
			
			if (!sender.hasPermission("nVerse.command." + command.getPermission())){
				continue;
			}
			
			if (!(sender instanceof Player) && command.getType() == CommandType.PLAYER){
				continue;
			}
			
			String message = Phrase.ARGUMENT_COLOR.parse() + commandLabel.toLowerCase() + " " + command.getDisplayName() + " " + command.getHelp() + " - " + command.getDescription().parse();
			
			if (sender instanceof Player){
				message = Phrase.ARGUMENT_COLOR.parse() + "/" + message;
			}else {
				message = Phrase.ARGUMENT_COLOR.parse() + message;
			}
			
			sender.sendMessage(message);
		}
		
		return true;
	}
}
