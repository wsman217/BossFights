package me.wsman217.BossFights.enums;

import org.bukkit.entity.*;

public enum BossesList {
	
	ENDER_DRAGON("Ender Dragon", EnderDragon.class),
	SILVER_FISH("Silver Fish", Silverfish.class),
	BLAZE("Blaze", Blaze.class),
	ENDERMAN("Endermand", Enderman.class),
	SPIDER("Spider", Spider.class),
	IRON_GOLEM("Iron Golem", IronGolem.class),
	WITCH("Witch", Witch.class),
	SLIME("Slime", Slime.class),
	SKELETON("Skeleton", Skeleton.class),
	ZOMBIE("Zombie", Zombie.class),
	WITHER("Wither Boss", Wither.class),
	WITHER_SKELLY("Wither", WitherSkeleton.class);
	
	public String type;
	public Class<? extends LivingEntity> clazz;
	
	private BossesList(String type, Class<? extends LivingEntity> clazz) {
		this.type = type;
		this.clazz = clazz;
	}
}
