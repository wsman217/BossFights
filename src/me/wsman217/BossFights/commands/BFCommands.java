package me.wsman217.BossFights.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;

public class BFCommands implements CommandExecutor, TabCompleter {

	BossFights plugin;
	Tools tools;

	public BFCommands(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {

		if (!(sender instanceof Player)) {
			if ((args.length == 1 && args[0].equalsIgnoreCase("Help")) || args.length == 0) {
				helpMsg(sender, false);
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("Reload")) {
					plugin.reloadConfig();
					sender.sendMessage(tools.color("&3You have just reloaded the Config.yml"));
					return true;
				}
				if (args[0].equalsIgnoreCase("KillAll")) {
					tools.addBosses();
					sender.sendMessage(tools.color("&cYou have Killed &6"
							+ Integer.toString(plugin.bossEntities.Bosses.size()) + " &cBosses."));
					for (LivingEntity e : plugin.bossEntities.Bosses) {
						e.remove();
					}
					plugin.bossEntities.Bosses.clear();
					return true;
				} else {
					helpMsg(sender, false);
					return true;
				}
			}
		}

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("buy")) {
					if (!tools.permCheck(player, "buy", true))
						return true;
					plugin.buyBoss.openGUI(player);
					return true;
				}

				if (args[0].equalsIgnoreCase("Reload")) {
					if (!tools.permCheck(player, "Reload", true))
						return true;
					plugin.reloadConfig();
					player.sendMessage(tools.color("&3You have just reloaded the Config.yml"));
					return true;
				}
				if (args[0].equalsIgnoreCase("KillAll")) {
					if (!tools.permCheck(player, "KillAll", true))
						return true;
					tools.addBosses();
					player.sendMessage(tools.color("&cYou have Killed &6"
							+ Integer.toString(plugin.bossEntities.Bosses.size()) + " &cBosses."));
					for (LivingEntity e : plugin.bossEntities.Bosses) {
						e.remove();
					}
					plugin.bossEntities.Bosses.clear();
					return true;
				}
			}
			if (!tools.permCheck(player, "Help", true))
				return true;
			helpMsg(sender, true);
			return true;

		}

		return false;
	}

	public void helpMsg(CommandSender sender, boolean ifPlayer) {
		if (ifPlayer) {
			Player player = (Player) sender;
			player.sendMessage(tools.color("&c&lCustom Bosses Help Menu"));
			if (tools.permCheck(player, "Buy", false))
				player.sendMessage(tools.color("&6/BossFights Buy &a- &7Opens the Boss Shop GUI."));
			player.sendMessage(tools.color("&6/BossFights Help &a- &7Shows this help menu."));
			if (tools.permCheck(player, "Reload", false))
				player.sendMessage(tools.color("&6/BossFights Reload &a- &7Reloads the Config.yml."));
			if (tools.permCheck(player, "Killall", false))
				player.sendMessage(tools.color("&6/BossFights KillAll &a- &7Kills all Bosses."));
			player.sendMessage(tools.color("&8Created By: &4BadBones69&8, Revamped By: &4wsman217"));
		} else if (!ifPlayer) {
			sender.sendMessage(tools.color("&c&lCustom Bosses Help Menu"));
			sender.sendMessage(tools.color("&6/BossFights Help &a- &7Gives you info on the Commands."));
			sender.sendMessage(tools.color("&6/BossFights Reload &a- &7Reloads the Config.yml."));
			sender.sendMessage(tools.color("&6/BossFights KillAll &a- &7Kills all Bosses."));
			sender.sendMessage(tools.color("&8Created By: &4BadBones69&8, Revamped By: &4wsman217"));
		}
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("bf") || command.getName().equalsIgnoreCase("bossfights")) {
			if (args.length == 1) {
				List<String> arguments = new ArrayList<String>();
				
				if (!(sender instanceof Player)) {
				arguments.add("reload");
				arguments.add("killall");
				}
				if (sender instanceof Player) {
					Player player = (Player)sender;
					if (tools.permCheck(player, "help", false))
							arguments.add("help");
					if (tools.permCheck(player, "reload", false))
						arguments.add("reload"); 
					if (tools.permCheck(player, "killall", false))
						arguments.add("killall");
				}
				return arguments;
			}
		}
		
		return null;
	}
}
