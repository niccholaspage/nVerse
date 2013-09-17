package com.niccholaspage.nVerse.command.commands;

import org.bukkit.World;
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
		for (World world : plugin.getServer().getWorlds()){
			sender.sendMessage(world.getName());
		}
		
		return true;
	}
}
