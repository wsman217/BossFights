package me.wsman217.BossFights.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Armor {
	
	LEATHER("Leather", Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS),
	CHAIN("Chain", Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS),
	GOLD("Gold", Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS),
	IRON("Iron", Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS),
	DIAMOND("Diamond", Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);

	
	public String type;
	public ItemStack Helm;
	public ItemStack Chest;
	public ItemStack Pants;
	public ItemStack Boots;
	
	private Armor (String type, Material Helm, Material Chest, Material Pants, Material Boots) {
		this.type = type;
		this.Helm = new ItemStack(Helm);
		this.Chest = new ItemStack(Chest);
		this.Pants = new ItemStack(Pants);
		this.Boots = new ItemStack(Boots);
	}
}
