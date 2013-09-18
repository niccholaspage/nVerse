package com.niccholaspage.nVerse;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.niccholaspage.nVerse.api.WorldOptions;

public class nVerseListener implements Listener {
	private final nVerse plugin;

	public nVerseListener(nVerse plugin){
		this.plugin = plugin;

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event){
		if (event.isCancelled() || !event.toWeatherState()){
			return;
		}

		WorldOptions options = plugin.getAPI().getWorldOptions(event.getWorld());

		if (!options.getWeather()){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event){
		Player player = event.getPlayer();

		WorldOptions options = plugin.getAPI().getWorldOptions(player.getWorld());

		if (!player.hasPermission("nVerse.bypass")){
			player.setGameMode(options.getGameMode());
		}
	}

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event){
		if (event.isBedSpawn()){
			return;
		}

		Player player = event.getPlayer();

		WorldOptions options = plugin.getAPI().getWorldOptions(player.getWorld());

		World world = plugin.getServer().getWorld(options.getRespawnWorld());
		
		if (world != null){
			event.setRespawnLocation(world.getSpawnLocation());
		}
	}
}
