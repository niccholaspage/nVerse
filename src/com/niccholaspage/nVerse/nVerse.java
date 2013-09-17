package com.niccholaspage.nVerse;

import org.bukkit.plugin.java.JavaPlugin;

public class nVerse extends JavaPlugin {
	public void onEnable(){
		getConfig().options().copyDefaults(true);

		getConfig().options().header("nVerse config - nicholasnassar.com\n" +
				"swag - Swag option");

		saveConfig();
	}
}
