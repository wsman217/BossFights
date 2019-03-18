package me.wsman217.BossFights.entities;

import java.util.HashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;
import me.wsman217.BossFights.enums.Enchantments;

public class AddAccessories {
	HashMap<Enchantment, Integer> HelmEnchantments = new HashMap<Enchantment, Integer>();
	HashMap<Enchantment, Integer> ChestEnchantments = new HashMap<Enchantment, Integer>();
	HashMap<Enchantment, Integer> LeggingEnchantments = new HashMap<Enchantment, Integer>();
	HashMap<Enchantment, Integer> BootsEnchantments = new HashMap<Enchantment, Integer>();

	HashMap<Enchantment, Integer> MainHandEnchantments = new HashMap<Enchantment, Integer>();
	HashMap<Enchantment, Integer> OffHandEnchantments = new HashMap<Enchantment, Integer>();

	BossFights plugin;
	Tools tools;

	public AddAccessories(BossFights plugin, Tools tools) {
		this.plugin = plugin;
		this.tools = tools;
	}

	public HashMap<Enchantment, Integer> helmEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.Armor.Helment.Enchants")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				HelmEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return HelmEnchantments;
	}
	
	public HashMap<Enchantment, Integer> chestEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.Armor.Chestplate.Enchants")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				ChestEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return ChestEnchantments;
	}
	
	public HashMap<Enchantment, Integer> leggingEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.Armor.Leggings.Enchants")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				LeggingEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return LeggingEnchantments;
	}
	
	public HashMap<Enchantment, Integer> bootEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.Armor.Boots.Enchants")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				BootsEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return BootsEnchantments;
	}
	
	public HashMap<Enchantment, Integer> mainHandEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.MainHand.Enchants")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				MainHandEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return MainHandEnchantments;
	}
	
	public HashMap<Enchantment, Integer> offHandEnchants(String path) {
		for (String en : plugin.getConfig().getStringList(path + ".Boss.OffHand.Enchantments")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			String key = null;
			for (Enchantments enchant : Enchantments.values()) {
				if (enchant.name.equalsIgnoreCase(enchantment)) {
					key = enchant.key;
				}
			}
			if (!(key.equals("null")))
				OffHandEnchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), lvl);
		}
		return OffHandEnchantments;
	}
}
