package com.niccholaspage.nVerse.command.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.command.SubCommand;

public class CreateWorldCommand extends SubCommand {
	public CreateWorldCommand() {
		super("createworld", Phrase.COMMAND_CREATEWORLD);
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		sender.sendMessage("Hi");
		
		return true;
	}
}
