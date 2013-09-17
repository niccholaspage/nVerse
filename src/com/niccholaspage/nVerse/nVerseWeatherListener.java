package com.niccholaspage.nVerse;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class nVerseWeatherListener implements Listener {
	private final nVerse plugin;
	
	public nVerseWeatherListener(nVerse plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event){
		if (event.isCancelled() || !event.toWeatherState()){
			return;
		}
		
		WorldOptions options = plugin.getAPI().getWorldOptions(event.getWorld());
		
		if (!options.getWeather()){
			event.setCancelled(true);
		}
	}
}
