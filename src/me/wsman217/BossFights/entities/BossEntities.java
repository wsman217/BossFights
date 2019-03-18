package me.wsman217.BossFights.entities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;
import me.wsman217.BossFights.enums.Armor;
import me.wsman217.BossFights.enums.BossesList;

public class BossEntities {
	BossFights plugin;
	Tools tools;
	AddAccessories aa;
	public ArrayList<LivingEntity> Bosses = new ArrayList<LivingEntity>();

	public BossEntities(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
		aa = new AddAccessories(plugin, tools);
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

		String mainHandType = plugin.getConfig().getString(path + "MainHand.Type");
		String offHandType = plugin.getConfig().getString(path + "OffHand.Type");

		if (!(mainHandType.equalsIgnoreCase("none")))
			boss.getEquipment().setItemInMainHand(
					tools.makeItem(Material.matchMaterial(plugin.getConfig().getString(path + "MainHand.Type")), 1, "",
							null, aa.mainHandEnchants(path)));
		if (!(offHandType.equalsIgnoreCase("none")))
			boss.getEquipment().setItemInOffHand(
					tools.makeItem(Material.matchMaterial(plugin.getConfig().getString(path + "OffHand.Type")), 1, "",
							null, aa.offHandEnchants(path)));

		String helmentType = plugin.getConfig().getString(path + "Armor.Helment.Type");
		String chestType = plugin.getConfig().getString(path + "Armor.Chestplate.Type");
		String leggingType = plugin.getConfig().getString(path + "Armor.Leggings.Type");
		String bootsType = plugin.getConfig().getString(path + "Armor.Boots.Type");
		
		if (!(helmentType.equalsIgnoreCase("none"))) {
			for (Armor armor : Armor.values()) {
				if (armor.type.equalsIgnoreCase(helmentType)) {
					armor.Helm.addUnsafeEnchantments(aa.helmEnchants(path));
					boss.getEquipment().setHelmet(armor.Helm);
				}
			}
		}
		
		if (!(chestType.equalsIgnoreCase("none"))) {
			for (Armor armor : Armor.values()) {
				if (armor.type.equalsIgnoreCase(chestType)) {
					armor.Chest.addUnsafeEnchantments(aa.chestEnchants(path));
					boss.getEquipment().setChestplate(armor.Chest);
				}
			}
		}
		
		if (!(leggingType.equalsIgnoreCase("none"))) {
			System.out.println("Test");
			for (Armor armor : Armor.values()) {
				if (armor.type.equalsIgnoreCase(leggingType)) {
					armor.Pants.addUnsafeEnchantments(aa.leggingEnchants(path));
					boss.getEquipment().setLeggings(armor.Pants);
				}
			}
		}
		
		if (!(bootsType.equalsIgnoreCase("none"))) {
			for (Armor armor : Armor.values()) {
				if (armor.type.equalsIgnoreCase(bootsType)) {
					armor.Boots.addUnsafeEnchantments(aa.bootEnchants(path));
					boss.getEquipment().setBoots(armor.Boots);
				}
			}
		}
	}
}