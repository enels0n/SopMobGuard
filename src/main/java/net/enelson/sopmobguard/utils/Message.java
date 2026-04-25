package net.enelson.sopmobguard.utils;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.enelson.sopmobguard.SopMobGuard;
import net.md_5.bungee.api.ChatColor;

public enum Message {

	PLUGIN_RELOADED("plugin-reloaded", "The plugin has been reloaded.");
	
	private String path;
	private String message;
	
	Message(String path, String message) {
		this.path = path;
		this.message = message;
	}
	
	public String getMessage() {
		String result = SopMobGuard.getInstance().getConfigManager().getLocale().getString(this.path);
		if(result != null) return result;
		return this.message;
	}
	
	public String getDefautMessage() {
		return this.message;
	}
	
	public String getMessageWithPlaceholders(Player player) {
		return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, this.getMessage()));
	}
}
