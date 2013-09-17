package com.niccholaspage.nVerse.command.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.command.SubCommand;

public class HelpCommand extends SubCommand {
	public HelpCommand() {
		super("help,?", Phrase.COMMAND_HELP);
	}
	
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		//TODO: Make help
		
		sender.sendMessage("help");
		
		return true;
	}
}
