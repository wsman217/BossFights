package me.wsman217.BossFights;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	ArrayList<String> getEnchants() {
		ArrayList<String> en = new ArrayList<String>();
		en.add("PROTECTION_ENVIRONMENTAL");
		en.add("PROTECTION_FIRE");
		en.add("PROTECTION_FALL");
		en.add("PROTECTION_EXPLOSIONS");
		en.add("ROTECTION_PROJECTILE");
		en.add("OXYGEN");
		en.add("WATER_WORKER");
		en.add("DAMAGE_ALL");
		en.add("DAMAGE_UNDEAD");
		en.add("DAMAGE_ARTHROPODS");
		en.add("KNOCKBACK");
		en.add("FIRE_ASPECT");
		en.add("LOOT_BONUS_MOBS");
		en.add("DIG_SPEED");
		en.add("SILK_TOUCH");
		en.add("DURABILITY");
		en.add("LOOT_BONUS_BLOCKS");
		en.add("ARROW_DAMAGE");
		en.add("ARROW_KNOCKBACK");
		en.add("ARROW_FIRE");
		en.add("ARROW_INFINITE");
		return en;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<ItemStack> getItems(String boss) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		String path = "Bosses." + boss + ".Boss.";
		for (String l : plugin.getConfig().getStringList(path + "CustomDrops")) {
			ArrayList<String> lore = new ArrayList<String>();
			ItemStack item = new ItemStack(Material.AIR);
			String name = "";
			for (String i : l.split(" ")) {
				if (i.contains("Type:")) {
					i = i.replaceAll("Type:", "");
					item.setType(Material.matchMaterial(i));
				}
				if (i.contains("Name:")) {
					i = i.replaceAll("Name:", "");
					i = i.replaceAll("_", " ");
					name = color(i);
				}
				if (i.contains("Lore:")) {
					i = i.replaceAll("Lore:", "");
					for (String L : i.split(",")) {
						L = color(L);
						L = L.replaceAll("_", " ");
						lore.add(L);
					}
				}
				for (String enc : getEnchants()) {
					if (i.contains(enc + ":")) {
						String[] breakdown = i.split(":");
						String enchantment = breakdown[0];
						int lvl = Integer.parseInt(breakdown[1]);
						item.addUnsafeEnchantment(Enchantment.getByName(enchantment), lvl);
					}
				}
			}
			ItemMeta m = item.getItemMeta();
			m.setDisplayName(name);
			m.setLore(lore);
			item.setItemMeta(m);
			items.add(item);
		}
		return items;
	}
}
