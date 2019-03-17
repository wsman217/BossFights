package me.wsman217.BossFights.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;

public class BuyBoss {

	BossFights plugin;
	Tools tools;

	public BuyBoss(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
	}

	public void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, plugin.getConfig().getInt("Settings.InventorySlots"),
				tools.color(plugin.getConfig().getString("Settings.InventoryName")));
		for (String boss : plugin.getConfig().getConfigurationSection("Bosses").getKeys(false)) {
			String path = "Bosses." + boss + ".GUI.";
			List<String> lore = new ArrayList<String>();
			for (String l : plugin.getConfig().getStringList(path + "Lore")) {
				l = l.replaceAll("%Cost%", Integer.toString(tools.getCost(boss)));
				l = l.replaceAll("%cost%", Integer.toString(tools.getCost(boss)));
				lore.add(tools.color(l));
			}
			String ma = plugin.getConfig().getString(path + "Item");
			//int type = 0;
			//if (ma.contains(":")) {
			//	String[] b = ma.split(":");
			//	ma = b[0];
			//	type = Integer.parseInt(b[1]);
			//}
			String name = tools.color(plugin.getConfig().getString(path + "Name"));
			Material m = Material.matchMaterial(ma);
			int slot = plugin.getConfig().getInt(path + "Slot") - 1;
			inv.setItem(slot, tools.makeItem(m, 1, name, lore));
		}
		player.openInventory(inv);
	}
}