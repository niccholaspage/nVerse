package com.niccholaspage.nVerse;

import org.bukkit.Difficulty;
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
		return createWorld(creator, new WorldOptions());
	}

	public World createWorld(WorldCreator creator, WorldOptions options){
		YamlConfiguration worldsConfig = plugin.getWorldsConfig();

		String name = creator.name();

		ConfigurationSection section = worldsConfig.createSection(name);

		section.set("environment", creator.environment().name());
		section.set("generatestructures", creator.generateStructures());
		//TODO: Figure out generator stuff
		section.set("seed", creator.seed());
		section.set("type", creator.type().getName());
		section.set("pvp", options.getPVP());
		section.set("difficulty", options.getDifficulty().getValue());
		section.set("weather", options.getWeather());

		plugin.saveWorldsConfig();

		World world = plugin.getServer().createWorld(creator);

		world.setPVP(options.getPVP());

		world.setDifficulty(options.getDifficulty());

		return world;
	}
	
	public WorldOptions getWorldOptions(World world){
		return getWorldOptions(world.getName());
	}

	public WorldOptions getWorldOptions(String name){
		WorldOptions options = new WorldOptions();

		ConfigurationSection section = plugin.getWorldsConfig().getConfigurationSection(name);

		if (section == null){
			return options;
		}

		if (section.contains("pvp")){
			options.setPVP(section.getBoolean("pvp"));
		}

		options.setDifficulty(Difficulty.getByValue(section.getInt("difficulty", -1)));

		if (section.contains("weather")){
			options.setWeather(section.getBoolean("weather"));
		}

		return options;
	}
}
