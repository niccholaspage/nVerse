package com.niccholaspage.nVerse;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
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
	
	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent event){
		if (event.isCancelled() || event.getSpawnReason() != SpawnReason.NATURAL){
			return;
		}
		
		WorldOptions options = plugin.getAPI().getWorldOptions(event.getLocation().getWorld());
		
		EntityType type = event.getEntityType();
		
		if (isPeaceful(type)){
			if (!options.getSpawnAnimals()){
				event.setCancelled(true);
			}
		}else {
			if (!options.getSpawnMonsters()){
				event.setCancelled(true);
			}
		}
	}
	
	private boolean isPeaceful(EntityType type){
		return type == EntityType.BAT ||
				type == EntityType.CHICKEN ||
				type == EntityType.COW ||
				type == EntityType.MUSHROOM_COW ||
				type == EntityType.HORSE ||
				type == EntityType.OCELOT ||
				type == EntityType.PIG ||
				type == EntityType.SHEEP ||
				type == EntityType.SNOWMAN ||
				type == EntityType.SQUID ||
				type == EntityType.VILLAGER;
	}
}
