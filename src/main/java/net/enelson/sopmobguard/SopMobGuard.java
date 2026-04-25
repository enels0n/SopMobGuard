package net.enelson.sopmobguard;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import net.enelson.sopmobguard.commands.MainCommand;
import net.enelson.sopmobguard.configs.ConfigManager;

public class SopMobGuard extends JavaPlugin {
	private static SopMobGuard plugin;
	private ConfigManager configs;
	
	public void onEnable() {
		plugin = this;
		this.configs = new ConfigManager(this);
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		try {
			String pac = "net.enelson.sopmobguard.listeners";
			for (ClassPath.ClassInfo clazzInfo : ClassPath.from(getClassLoader()).getTopLevelClasses(pac)) {
				Class<?> clazz = Class.forName(clazzInfo.getName());
				if (Listener.class.isAssignableFrom(clazz)) {
					pluginManager.registerEvents((Listener) clazz.getDeclaredConstructor().newInstance(), this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
