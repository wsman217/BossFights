package me.wsman217.BossFights;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.wsman217.BossFights.commands.BFCommands;
import me.wsman217.BossFights.entities.BossEntities;
import me.wsman217.BossFights.gui.BuyBoss;
import me.wsman217.BossFights.listeners.BossEntityListener;
import me.wsman217.BossFights.listeners.BuyBossListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class BossFights extends JavaPlugin {

	public Tools tools;
	public BossEntities bossEntities;
	public BuyBoss buyBoss;

	public Economy econ = null;
	public EconomyResponse r;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		if (checkDependencies()) {
			if (setupEconomy()) {

				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "BossFights has been enabled!");

				tools = new Tools(this);
				bossEntities = new BossEntities(this);
				buyBoss = new BuyBoss(this);

				getServer().getPluginManager().registerEvents(new BuyBossListener(this), this);
				getServer().getPluginManager().registerEvents(new BossEntityListener(this), this);

				getCommand("bossfights").setExecutor(new BFCommands(this));
				getCommand("bf").setExecutor(new BFCommands(this));
			}
		}
	}

	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "BossFights has been disabled");
	}

	public boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			Bukkit.getServer().getLogger().log(Level.SEVERE,
					"Please install a plugin with economy that is supported by Vault!");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public boolean checkDependencies() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Install Vault to use BossFights!");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return false;
		}
		return true;
	}
}
