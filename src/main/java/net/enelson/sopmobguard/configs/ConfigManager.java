package net.enelson.sopmobguard.configs;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	private YamlConfiguration config;
	private Plugin plugin;

	public ConfigManager(Plugin plugin) {
		this.plugin = plugin;
		this.reloadConfigs();
	}
	
	public void reloadConfigs() {
		File file = new File(this.plugin.getDataFolder(), "config.yml");
		if(!file.exists()) this.plugin.saveResource("config.yml", true);
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public YamlConfiguration getConfigs() {
		return this.config;
	}
}
