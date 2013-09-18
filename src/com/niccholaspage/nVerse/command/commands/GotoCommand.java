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
		super("goto,tp,teleport", Phrase.COMMAND_GOTO, CommandType.CONSOLE_WITH_ARGUMENTS, "[world|name world]");

		this.plugin = plugin;
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (args.length < 1){
			return false;
		}
		
		if (args.length < 2 && !(sender instanceof Player)){
			Phrase.COMMAND_NEEDS_ARGUMENTS.sendWithPrefix(sender);
			
			return true;
		}
		
		String name = args[0];

		if (args.length > 1){
			name = args[1];
		}

		World world = plugin.getServer().getWorld(name);

		if (world == null){
			Phrase.WORLD_DOES_NOT_EXIST.sendWithPrefix(sender);

			return true;
		}
		
		Player player = null;
		
		if (args.length > 1){
			player = plugin.getServer().getPlayer(args[0]);
		}else {
			player = (Player) sender;
		}
		
		if (player == null){
			Phrase.PLAYER_DOES_NOT_EXIST.sendWithPrefix(sender);
			
			return true;
		}

		player.teleport(world.getSpawnLocation());

		Phrase.YOU_HAVE_BEEN_TELEPORTED.sendWithPrefix(player, world.getName());
		
		if (!player.getName().equals(sender.getName())){
			Phrase.YOU_HAVE_TELEPORTED.sendWithPrefix(sender, player.getName(), world.getName());
		}

		return true;
	}
}
