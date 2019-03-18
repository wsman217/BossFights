package me.wsman217.BossFights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.wsman217.BossFights.enums.Enchantments;

public class Tools {

	BossFights plugin;

	public Tools(BossFights plugin) {
		this.plugin = plugin;
	}

	public String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public String strip(String msg) {
		return ChatColor.stripColor(msg);
	}

	public ItemStack makeItem(Material material, int amount, String name, List<String> lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}

	public ItemStack makeItem(Material material, int amount, String name, List<String> lore,
			Map<Enchantment, Integer> enchants) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		item.addUnsafeEnchantments(enchants);
		return item;
	}

	public ItemStack addLore(ItemStack item, String i) {
		ArrayList<String> lore = new ArrayList<String>();
		ItemMeta m = item.getItemMeta();
		if (item.getItemMeta().hasLore()) {
			lore.addAll(item.getItemMeta().getLore());
		}
		lore.add(i);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public Player getPlayer(String name) {
		return Bukkit.getServer().getPlayer(name);
	}

	public Location getLoc(Player player) {
		return player.getLocation();
	}

	public void runCMD(Player player, String CMD) {
		player.performCommand(CMD);
	}

	public boolean isOnline(String name, CommandSender p) {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		p.sendMessage(color("&cThat player is not online at this time."));
		return false;
	}

	public boolean permCheck(Player player, String perm, boolean sendMsg) {
		if (!player.hasPermission("bf." + perm)) {
			if (sendMsg)
				player.sendMessage(color("&cYou do not have permission to use that command!"));
			return false;
		}
		return true;
	}

	public void removeItem(ItemStack item, Player player) {
		if (item.getAmount() <= 1) {
			player.getInventory().removeItem(item);
		}
		if (item.getAmount() > 1) {
			ItemStack i = item;
			i.setAmount(item.getAmount() - 1);
		}
	}

	public int getCost(String boss) {
		return plugin.getConfig().getInt("Bosses." + boss + ".GUI.Cost");
	}

	public double getMoney(Player player) {
		return plugin.econ.getBalance(player);
	}

	public void addBosses() {
		for (World w : Bukkit.getServer().getWorlds()) {
			for (Entity e : w.getEntities()) {
				if (e instanceof LivingEntity) {
					LivingEntity en = (LivingEntity) e;
					for (String boss : plugin.getConfig().getConfigurationSection("Bosses").getKeys(false)) {
						String name = plugin.tools.color(plugin.getConfig().getString("Bosses." + boss + ".Boss.Name"));
						if (en.getCustomName() != null) {
							if (!plugin.bossEntities.Bosses.contains(en)) {
								if (en.getCustomName().equals(name)) {
									plugin.bossEntities.Bosses.add(en);
								}
							}
						}
					}
				}
			}
		}
	}

	public ArrayList<ItemStack> getItems(String path) {
		path = path + "CustomDrops";
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		for (String l : plugin.getConfig().getConfigurationSection(path).getKeys(false)) {
			HashMap<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
			System.out.println(l);
			ArrayList<String> lore = new ArrayList<String>();
			ItemStack item = new ItemStack(
					Material.matchMaterial(plugin.getConfig().getString(path + "." + l + ".Type")));
			String name = color(plugin.getConfig().getString(path + "." + l + ".Name"));

			for (String a : plugin.getConfig().getStringList(path + "." + l + ".Lore")) {
				a = a.replaceAll("_", " ");
				lore.add(color(a));
				System.out.println(a);
			}
			for (String en : plugin.getConfig().getStringList(path + "." + l + ".Enchants")) {
				System.out.println(en);
				String[] breakdown = en.split(":");
				String enchantment = breakdown[0];
				int lvl = Integer.parseInt(breakdown[1]);
				String key = "null";
				for (Enchantments enc : Enchantments.values()) {
					if (enc.name.equalsIgnoreCase(enchantment)) {
						System.out.println(enc.name);
						key = enc.key;
					}
					if (!(key.equals("null")))
						enchants.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
				}
			}
			item.addUnsafeEnchantments(enchants);
			ItemMeta m = item.getItemMeta();
			m.setDisplayName(name);
			m.setLore(lore);
			item.setItemMeta(m);
			items.add(item);
		}
		return items;
	}
}
