package me.wsman217.BossFights.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;
import net.milkbowl.vault.economy.EconomyResponse;

public class BuyBossListener implements Listener {

	BossFights plugin;
	Tools tools;

	public BuyBossListener(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		int s = e.getSlot();
		if (inv == null)
			return;
		if (inv.getName().equals(tools.color(plugin.getConfig().getString("Settings.InventoryName")))) {
			e.setCancelled(true);
			for (String boss : plugin.getConfig().getConfigurationSection("Bosses").getKeys(false)) {
				String path = "Bosses." + boss + ".GUI.";
				String path2 = "Bosses." + boss + ".SpawnEgg.";
				if (plugin.getConfig().getInt(path + "Slot") - 1 == s) {

					if (plugin.getConfig().getBoolean("Testing")) {
						String ma = plugin.getConfig().getString(path2 + "Item");
						Material m = Material.matchMaterial(ma);
						String n = tools.color(plugin.getConfig().getString(path2 + "Name"));
						List<String> lore = new ArrayList<String>();
						for (String l : plugin.getConfig().getStringList(path2 + "Lore")) {
							lore.add(tools.color(l));
						}
						player.getInventory().addItem(tools.makeItem(m, 1, n, lore));
						player.sendMessage(tools.color(plugin.getConfig().getString(path + "BuyingMessage")));
						player.closeInventory();
						return;
					}

					if (tools.getCost(boss) <= tools.getMoney(player)) {

						EconomyResponse r = plugin.econ.withdrawPlayer(player, tools.getCost(boss));
						if (r.transactionSuccess()) {
							String ma = plugin.getConfig().getString(path2 + "Item");
							Material m = Material.matchMaterial(ma);
							String n = tools.color(plugin.getConfig().getString(path2 + "Name"));
							List<String> lore = new ArrayList<String>();
							for (String l : plugin.getConfig().getStringList(path2 + "Lore")) {
								lore.add(tools.color(l));
							}
							player.getInventory().addItem(tools.makeItem(m, 1, n, lore));
							player.sendMessage(tools.color(plugin.getConfig().getString(path + "BuyingMessage")));
							player.closeInventory();
							return;
						}
					} else {
						player.closeInventory();
						player.sendMessage(tools.color("&cYou do not have enough money for that."));
						return;
					}
				}
			}
		}
	}
}
