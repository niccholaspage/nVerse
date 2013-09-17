package com.niccholaspage.nVerse;

import org.bukkit.plugin.java.JavaPlugin;

import com.niccholaspage.nVerse.command.nVerseCommandExecutor;

public class nVerse extends JavaPlugin {
	public void onEnable(){
		getConfig().options().copyDefaults(true);

		getConfig().options().header("nVerse config - nicholasnassar.com\n" +
				"swag - Swag option");

		saveConfig();
		
		getCommand("nverse").setExecutor(new nVerseCommandExecutor());
	}
}
