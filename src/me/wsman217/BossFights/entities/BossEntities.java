package me.wsman217.BossFights.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;
import me.wsman217.BossFights.enums.Armor;
import me.wsman217.BossFights.enums.BossesList;

public class BossEntities {
	BossFights plugin;
	Tools tools;
	public ArrayList<LivingEntity> Bosses = new ArrayList<LivingEntity>();

	public BossEntities(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
	}

	@SuppressWarnings("deprecation")
	public void spawnBoss(Location l, String b) {
		LivingEntity boss = null;
		l = l.add(.5, 0, .5);
		String path = "Bosses." + b + ".Boss.";
		String bosstype = plugin.getConfig().getString(path + "BossType");

		for (BossesList bosses : BossesList.values()) {
			if (bosses.type.equalsIgnoreCase(bosstype))
				boss = l.getWorld().spawn(l, bosses.clazz);
		}
		Bosses.add(boss);
		String name = tools.color(plugin.getConfig().getString(path + "Name"));
		boss.setCustomName(name);
		boss.setMaxHealth(plugin.getConfig().getInt(path + "Health"));
		boss.setHealth(plugin.getConfig().getInt(path + "Health"));
		String type = plugin.getConfig().getString(path + "ArmorType");
		HashMap<Enchantment, Integer> ArmorEnchantments = new HashMap<Enchantment, Integer>();
		for (String en : plugin.getConfig().getStringList(path + "ArmorEnchantments")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			ArmorEnchantments.put(Enchantment.getByName(enchantment), lvl);
		}
		HashMap<Enchantment, Integer> WeaponEnchantments = new HashMap<Enchantment, Integer>();
		for (String en : plugin.getConfig().getStringList(path + "WeaponEnchantments")) {
			String[] breakdown = en.split(":");
			String enchantment = breakdown[0];
			int lvl = Integer.parseInt(breakdown[1]);
			WeaponEnchantments.put(Enchantment.getByName(enchantment), lvl);
		}
		boss.getEquipment().setItemInMainHand(
				tools.makeItem(Material.matchMaterial(plugin.getConfig().getString(path + "WeaponType")), 1, "", null,
						WeaponEnchantments));

		if (!(type.equalsIgnoreCase("none"))) {
			for (Armor armor : Armor.values()) {
				if (armor.type.equalsIgnoreCase(type)) {
					armor.Helm.addUnsafeEnchantments(ArmorEnchantments);
					armor.Chest.addUnsafeEnchantments(ArmorEnchantments);
					armor.Pants.addUnsafeEnchantments(ArmorEnchantments);
					armor.Boots.addUnsafeEnchantments(ArmorEnchantments);
					boss.getEquipment().setHelmet(armor.Helm);
					boss.getEquipment().setChestplate(armor.Chest);
					boss.getEquipment().setLeggings(armor.Chest);
					boss.getEquipment().setBoots(armor.Chest);
				}
			}
		}
	}
}