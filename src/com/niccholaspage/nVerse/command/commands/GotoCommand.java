package com.niccholaspage.nVerse.command.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.niccholaspage.nVerse.Phrase;
import com.niccholaspage.nVerse.nVerse;
import com.niccholaspage.nVerse.command.CommandType;
import com.niccholaspage.nVerse.command.SubCommand;

public class GotoCommand extends SubCommand {
	private final nVerse plugin;

	public GotoCommand(nVerse plugin) {
		super("goto", Phrase.COMMAND_GOTO, CommandType.PLAYER);

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
		
		Player player = (Player) sender;
		
		player.teleport(world.getSpawnLocation());

		return true;
	}
}
