package com.niccholaspage.nVerse.api;

import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.niccholaspage.nVerse.nVerse;

public class API {
	private final nVerse plugin;

	public API(nVerse plugin){
		this.plugin = plugin;
	}

	public void createWorld(World world){
		createWorld(world.getName(), getWorldOptions(world));
	}

	public World createWorld(String name, WorldOptions options){
		YamlConfiguration worldsConfig = plugin.getWorldsConfig();

		ConfigurationSection section = worldsConfig.createSection(name);

		WorldCreator creator = new WorldCreator(name);

		creator.environment(options.getEnvironment());
		creator.generateStructures(options.canGenerateStructures());
		creator.seed(options.getSeed());
		creator.type(options.getType());

		section.set("environment", creator.environment().name());
		section.set("generatestructures", creator.generateStructures());
		//TODO: Figure out generator stuff
		section.set("seed", creator.seed());
		section.set("type", creator.type().getName());
		section.set("pvp", options.getPVP());
		section.set("difficulty", options.getDifficulty().getValue());
		section.set("weather", options.getWeather());
		section.set("keepspawninmemory", options.getKeepSpawnInMemory());
		section.set("gamemode", options.getGameMode().getValue());
		section.set("respawnworld", options.getRespawnWorld());
		section.set("netherworld", options.getNetherWorld());
		section.set("endworld", options.getEndWorld());
		section.set("spawnanimals", options.getSpawnAnimals());
		section.set("spawnmonsters", options.getSpawnMonsters());

		plugin.saveWorldsConfig();

		World world = plugin.getServer().createWorld(creator);

		world.setPVP(options.getPVP());

		world.setDifficulty(options.getDifficulty());

		world.setKeepSpawnInMemory(options.getKeepSpawnInMemory());

		return world;
	}

	public boolean removeWorld(World world){
		String name = world.getName();

		if (isDefaultWorld(name)){
			return false;
		}

		YamlConfiguration worldsConfig = plugin.getWorldsConfig();

		if (!worldsConfig.contains(name)){
			return false;
		}

		worldsConfig.set(name, null);

		plugin.saveWorldsConfig();

		return true;
	}

	public WorldOptions getWorldOptions(World world){
		WorldOptions options = getWorldOptions(world.getName());

		options.setEnvironment(world.getEnvironment());
		options.setGenerateStructures(world.canGenerateStructures());
		options.setSeed(world.getSeed());
		options.setType(world.getWorldType());

		return options;
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

		if (section.contains("keepspawninmemory")){
			options.setKeepSpawnInMemory(section.getBoolean("keepspawninmemory"));
		}

		options.setGameMode(GameMode.getByValue(section.getInt("gamemode", 0)));

		options.setRespawnWorld(section.getString("respawnworld"));

		options.setNetherWorld(section.getString("netherworld"));

		options.setEndWorld(section.getString("endworld"));

		if (section.contains("spawnanimals")){
			options.setSpawnAnimals(section.getBoolean("spawnanimals"));
		}

		if (section.contains("spawnmonsters")){
			options.setSpawnMonsters(section.getBoolean("spawnmonsters"));
		}

		return options;
	}

	public boolean isDefaultWorld(String name){
		//TODO: Fix this, nether and end return false for some reason

		String defaultWorld = plugin.getServer().getWorlds().get(0).getName();

		String netherWorld = defaultWorld + "_nether";

		String endWorld = defaultWorld + "_the_end";

		if (defaultWorld.equals(name) || netherWorld.equals(name + "_nether") || endWorld.equals(name + "_the_end")){
			return true;
		}

		return false;
	}
}
