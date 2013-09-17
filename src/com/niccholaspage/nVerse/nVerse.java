package com.niccholaspage.nVerse;

import org.bukkit.plugin.java.JavaPlugin;

import com.niccholaspage.nVerse.command.nVerseCommandExecutor;

public class nVerse extends JavaPlugin {
	private API api;
	
	public void onEnable(){
		getConfig().options().copyDefaults(true);

		getConfig().options().header("nVerse config - nicholasnassar.com\n" +
				"swag - Swag option");

		saveConfig();
		
		api = new API();
		
		getCommand("nverse").setExecutor(new nVerseCommandExecutor(this));
	}
	
	public API getAPI(){
		return api;
	}
}
