package net.enelson.sopmobguard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.enelson.sopmobguard.commands.MainCommand;
import net.enelson.sopmobguard.configs.ConfigManager;
import net.enelson.sopmobguard.listeners.Handler;

public class SopMobGuard extends JavaPlugin {
	private static SopMobGuard plugin;
	private ConfigManager configs;
	
	public void onEnable() {
		plugin = this;
		this.configs = new ConfigManager(this);
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new Handler(), this);
		
		getCommand("sopmobguard").setExecutor(new MainCommand());
	}
	
	public static SopMobGuard getInstance() {
		return plugin;
	}
	
	public ConfigManager getConfigManager() {
		return this.configs;
	}
	
	public void onDisable() {
		
	}
}
