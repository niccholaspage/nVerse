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

	public WorldOptions(nVerse plugin){
		pvp = true;

		difficulty = Difficulty.EASY;

		weather = true;

		keepSpawnInMemory = true;
		
		gameMode = plugin.getServer().getDefaultGameMode();
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
}
