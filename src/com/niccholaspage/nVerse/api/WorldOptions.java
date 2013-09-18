package com.niccholaspage.nVerse.api;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;

public class WorldOptions {
	private Environment environment;

	private boolean generateStructures;

	private long seed;

	private WorldType type;

	private boolean pvp;

	private Difficulty difficulty;

	private boolean weather;

	private boolean keepSpawnInMemory;

	private GameMode gameMode;

	private String respawnWorld;

	private String netherWorld;

	private String endWorld;

	private boolean spawnAnimals;

	private boolean spawnMonsters;

	public WorldOptions(){
		environment = Environment.NORMAL;

		generateStructures = true;

		seed = new Random().nextLong();

		type = WorldType.NORMAL;

		pvp = true;

		difficulty = Difficulty.EASY;

		weather = true;

		keepSpawnInMemory = true;

		gameMode = Bukkit.getDefaultGameMode();

		respawnWorld = "";

		netherWorld = "";

		endWorld = "";

		spawnAnimals = true;

		spawnMonsters = true;
	}

	public void setEnvironment(Environment environment){
		if (environment == null){
			environment = Environment.NORMAL;
		}

		this.environment = environment;
	}

	public Environment getEnvironment(){
		return environment;
	}

	public void setGenerateStructures(boolean generateStructures){
		this.generateStructures = generateStructures;
	}

	public boolean canGenerateStructures(){
		return generateStructures;
	}

	public void setSeed(long seed){
		this.seed = seed;
	}

	public long getSeed(){
		return seed;
	}

	public void setType(WorldType type){
		if (type == null){
			type = WorldType.NORMAL;
		}

		this.type = type;
	}

	public WorldType getType(){
		return type;
	}

	public void setPVP(boolean pvp){
		this.pvp = pvp;
	}

	public boolean getPVP(){
		return pvp;
	}

	public void setDifficulty(Difficulty difficulty){
		if (difficulty == null){
			difficulty = Difficulty.NORMAL;
		}

		this.difficulty = difficulty;
	}

	public Difficulty getDifficulty(){
		return difficulty;
	}

	public boolean getWeather(){
		return weather;
	}

	public void setWeather(boolean weather){
		this.weather = weather;
	}

	public boolean getKeepSpawnInMemory(){
		return keepSpawnInMemory;
	}

	public void setKeepSpawnInMemory(boolean keepSpawnInMemory){
		this.keepSpawnInMemory = keepSpawnInMemory;
	}

	public GameMode getGameMode(){
		return gameMode;
	}

	public void setGameMode(GameMode gameMode){
		this.gameMode = gameMode;
	}

	public String getRespawnWorld(){
		return respawnWorld;
	}

	public void setRespawnWorld(String respawnWorld){
		if (respawnWorld == null){
			respawnWorld = "";
		}

		this.respawnWorld = respawnWorld;
	}

	public String getNetherWorld(){
		return netherWorld;
	}

	public void setNetherWorld(String netherWorld){
		if (netherWorld == null){
			netherWorld = "";
		}
		
		this.netherWorld = netherWorld;
	}

	public String getEndWorld(){		
		return endWorld;
	}

	public void setEndWorld(String endWorld){
		if (endWorld == null){
			endWorld = "";
		}
		
		this.endWorld = endWorld;
	}

	public boolean getSpawnAnimals(){
		return spawnAnimals;
	}

	public void setSpawnAnimals(boolean spawnAnimals){
		this.spawnAnimals = spawnAnimals;
	}

	public boolean getSpawnMonsters(){
		return spawnMonsters;
	}

	public void setSpawnMonsters(boolean spawnMonsters){
		this.spawnMonsters = spawnMonsters;
	}
}
