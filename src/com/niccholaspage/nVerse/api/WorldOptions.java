package com.niccholaspage.nVerse.api;

import org.bukkit.Difficulty;
import org.bukkit.GameMode;

import com.niccholaspage.nVerse.nVerse;

public class WorldOptions {
	private boolean pvp;

	private Difficulty difficulty;

	private boolean weather;

	private boolean keepSpawnInMemory;
	
	private GameMode gameMode;
	
	private String respawnWorld;
	
	private boolean spawnAnimals;
	
	private boolean spawnMonsters;

	public WorldOptions(nVerse plugin){
		pvp = true;

		difficulty = Difficulty.EASY;

		weather = true;

		keepSpawnInMemory = true;
		
		gameMode = plugin.getServer().getDefaultGameMode();
		
		respawnWorld = "";
		
		spawnAnimals = true;
		
		spawnMonsters = true;
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
