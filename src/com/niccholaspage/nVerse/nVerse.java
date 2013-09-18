package com.niccholaspage.nVerse;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.niccholaspage.nVerse.api.API;
import com.niccholaspage.nVerse.api.WorldOptions;
import com.niccholaspage.nVerse.command.nVerseCommandExecutor;

public class nVerse extends JavaPlugin {
	private API api;

	private YamlConfiguration worldsConfig;

	public void onEnable(){
		getConfig().options().copyDefaults(true);

		getConfig().options().header("nVerse config - nicholasnassar.com");

		saveConfig();

		api = new API(this);

		reloadWorlds();

		new nVerseListener(this);

		getCommand("nverse").setExecutor(new nVerseCommandExecutor(this));
	}

	public API getAPI(){
		return api;
	}

	public void reloadConfig(){
		super.reloadConfig();

		if (worldsConfig != null){
			reloadWorldsConfig();
			
			reloadWorlds();
		}

		File phrasesFile = new File(getDataFolder(), "phrases.yml");

		for (Phrase phrase : Phrase.values()){
			phrase.reset();
		}

		if (phrasesFile.exists()){
			YamlConfiguration phrasesConfig = YamlConfiguration.loadConfiguration(phrasesFile);

			for (Phrase phrase : Phrase.values()){
				String phraseConfigName = phrase.getConfigName();

				String phraseMessage = phrasesConfig.getString(phraseConfigName);

				if (phraseMessage == null){
					phraseMessage = phrase.parse();
				}

				phrase.setMessage(phraseMessage);
			}
		}
	}

	private File getWorldsFile(){
		return new File(getDataFolder(), "worlds.yml");
	}
	
	public YamlConfiguration getWorldsConfig(){
		if (worldsConfig == null){
			reloadWorldsConfig();
		}
		
		return worldsConfig;
	}
	
	private void reloadWorldsConfig(){
		worldsConfig = YamlConfiguration.loadConfiguration(getWorldsFile());
	}

	public void saveWorldsConfig(){
		try {
			getWorldsConfig().save(getWorldsFile());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void reloadWorlds(){
		YamlConfiguration worldsConfig = getWorldsConfig();

		Set<String> worlds = worldsConfig.getKeys(false);

		for (String world : worlds){
			ConfigurationSection section = worldsConfig.getConfigurationSection(world);

			String environmentName = section.getString("environment");

			Environment environment = null;

			for (Environment env : Environment.values()){
				if (env.name().equalsIgnoreCase(environmentName)){
					environment = env;

					break;
				}
			}

			if (environment == null){
				environment = Environment.NORMAL;
			}

			WorldOptions options = getAPI().getWorldOptions(world);
			
			options.setEnvironment(environment);

			boolean generateStructures = section.getBoolean("generatestructures", true);

			options.setGenerateStructures(generateStructures);

			long seed = section.getLong("seed");

			options.setSeed(seed);

			String typeName = section.getString("type");

			WorldType type = WorldType.getByName(typeName);

			if (type == null){
				type = WorldType.NORMAL;
			}

			options.setType(type);

			getAPI().createWorld(world, options);
		}

		for (World world : getServer().getWorlds()){
			String name = world.getName();

			if (!worldsConfig.contains(name)){
				getAPI().createWorld(world);
			}
		}
	}
}
