package com.niccholaspage.nVerse;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class API {
	private final nVerse plugin;
	
	public API(nVerse plugin){
		this.plugin = plugin;
	}
	
	public World createWorld(WorldCreator creator){
		YamlConfiguration worldsConfig = plugin.getWorldsConfig();
		
		String name = creator.name();
		
		worldsConfig.set(name, null);
		
		ConfigurationSection section = worldsConfig.createSection(name);
		
		section.set("environment", creator.environment().name());
		section.set("generatestructures", creator.generateStructures());
		//TODO: Figure out generator stuff
		section.set("seed", creator.seed());
		section.set("type", creator.type().getName());
		
		plugin.saveWorldsConfig(worldsConfig);
		
		return plugin.getServer().createWorld(creator);
	}
}
