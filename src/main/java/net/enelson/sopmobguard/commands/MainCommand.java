package net.enelson.sopmobguard.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.enelson.sopmobguard.SopMobGuard;
import net.enelson.sopmobguard.utils.Message;

public class MainCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.isOp())
			return true;
		
		if(args.length > 0 && args[0].equalsIgnoreCase("reload")) {
			SopMobGuard.getInstance().getConfigManager().reloadConfigs();
			if(sender instanceof Player)
				sender.sendMessage(Message.PLUGIN_RELOADED.getMessageWithPlaceholders((Player)sender));
			else
				sender.sendMessage(Message.PLUGIN_RELOADED.getMessage());
			
			return true;
		}
		return true;
	}
}
