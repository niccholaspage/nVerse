package com.niccholaspage.nVerse;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerPortalEvent;
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

	private int getPortalType(Location loc){
		Block mainBlock = loc.getBlock();

		for (BlockFace face : new BlockFace[] { BlockFace.SELF, BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH_EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST, BlockFace.NORTH_WEST }){
			Material type = mainBlock.getRelative(face).getType();

			if (type == Material.ENDER_PORTAL){
				return 1;
			}else if (type == Material.PORTAL){
				return -1;
			}
		}

		return 0;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerPortal(PlayerPortalEvent event) {
		if (event.isCancelled()){
			return;
		}
		
	    EntityPortalEvent evt = new EntityPortalEvent(event.getPlayer(), event.getFrom(), event.getTo(), event.getPortalTravelAgent());
	    
	    evt.setCancelled(event.isCancelled());
	    
	    onEntityPortalEvent(evt);
	    
	    event.setCancelled(evt.isCancelled());
	    
	    event.setTo(evt.getTo());
	    
	    event.setFrom(evt.getFrom());
	    
	    event.setPortalTravelAgent(evt.getPortalTravelAgent());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityPortalEvent(EntityPortalEvent event){
		if (event.isCancelled()){
			return;
		}

		Location from = event.getFrom();

		World world = from.getWorld();

		WorldOptions options = plugin.getAPI().getWorldOptions(world);

		World endWorld = plugin.getServer().getWorld(options.getEndWorld());

		World netherWorld = plugin.getServer().getWorld(options.getNetherWorld());

		int mapType = getPortalType(event.getFrom());

		if (endWorld != null && mapType == 1){
			Environment toDim = endWorld.getEnvironment();

			if (toDim == Environment.THE_END){
				Location location = new Location(endWorld, 100.0D, 54.0D, 0.0D);

				location = event.getPortalTravelAgent().findOrCreate(location);

				event.setTo(location);
			} else {
				Entity entity = event.getEntity();

				Location location = null;

				if (entity instanceof Player){
					Player player = (Player) entity;
					
					location = player.getBedSpawnLocation();
				}

				if (location == null || !location.getWorld().equals(endWorld)){
					location = endWorld.getSpawnLocation();
				}

				if (location == null){
					location = event.getPortalTravelAgent().findOrCreate(location);
				}

				event.setTo(location);
			}

		} else if (netherWorld != null && mapType == -1){
			Environment toDim = netherWorld.getEnvironment();

			Environment fromDim = event.getFrom().getWorld().getEnvironment();
			
			float div;

			if (fromDim == toDim){
				div = 1.0F;
			} else {
				if (fromDim == Environment.NETHER){
					div = 8.0F;
				} else {
					if (toDim == Environment.NETHER){
						div = 0.125F;
					} else {
						div = 1.0F;
					}
				}
			}

			Location to = new Location(netherWorld, event.getFrom().getX() * div, event.getFrom().getY(), event.getFrom().getZ() * div, event.getFrom().getYaw(), event.getFrom().getPitch());

			to = event.getPortalTravelAgent().findOrCreate(to);

			event.setTo(to);
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
