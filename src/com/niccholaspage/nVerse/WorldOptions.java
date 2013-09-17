package com.niccholaspage.nVerse;

import org.bukkit.Difficulty;

public class WorldOptions {
	private boolean pvp;

	private Difficulty difficulty;

	private boolean weather;

	private boolean keepSpawnInMemory;

	public WorldOptions(){
		pvp = true;

		difficulty = Difficulty.EASY;

		weather = true;

		keepSpawnInMemory = true;
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
}
