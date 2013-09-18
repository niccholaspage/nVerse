package com.niccholaspage.nVerse.command.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.SubCommand;

public class ReloadCommand extends SubCommand {
	private final nVerse plugin;
	
	public ReloadCommand(nVerse plugin) {
		super("reload", Phrase.COMMAND_RELOAD, "");
		
		this.plugin = plugin;
	}
	
	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		plugin.reloadConfig();
		
		Phrase.CONFIG_HAS_BEEN_RELOADED.sendWithPrefix(sender);
		
		return true;
	}
}
