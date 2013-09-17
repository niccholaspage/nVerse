package com.niccholaspage.nVerse;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.niccholaspage.nVerse.command.nVerseCommandExecutor;

public class nVerse extends JavaPlugin {
	private API api;

	public void onEnable(){
		getConfig().options().copyDefaults(true);

		getConfig().options().header("nVerse config - nicholasnassar.com");

		saveConfig();

		api = new API(this);

		reloadWorlds();

		getCommand("nverse").setExecutor(new nVerseCommandExecutor(this));
	}

	public API getAPI(){
		return api;
	}

	public void reloadConfig(){
		super.reloadConfig();

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
		return YamlConfiguration.loadConfiguration(getWorldsFile());
	}
	
	public void saveWorldsConfig(YamlConfiguration config){
		try {
			config.save(getWorldsFile());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void reloadWorlds(){
		File worldsFile = getWorldsFile();

		if (worldsFile.exists()){
			YamlConfiguration worldsConfig = getWorldsConfig();

			Set<String> worlds = worldsConfig.getKeys(false);

			for (String world : worlds){
				ConfigurationSection section = worldsConfig.getConfigurationSection(world);

				WorldCreator creator = new WorldCreator(world);

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

				creator.environment(environment);

				boolean generateStructures = section.getBoolean("generatestructures", true);

				creator.generateStructures(generateStructures);

				long seed = section.getLong("seed");

				creator.seed(seed);

				String typeName = section.getString("type");

				WorldType type = WorldType.getByName(typeName);

				if (type == null){
					type = WorldType.NORMAL;
				}

				creator.type(type);

				getAPI().createWorld(creator);
				
				saveWorldsConfig(worldsConfig);
			}
		}
	}
}
