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
	WITHER_SKELLY("Wither", WitherSkeleton.class),
	
	//Added
	MAGMA_CUBE("Magma Cube", MagmaCube.class),
	ILLUSIONER("Illusioner", Illusioner.class),
	VINDICATOR("Vindicator", Vindicator.class),
	SILVERFISH("Silverfish", Silverfish.class),
	DROWNED("Drowned", Drowned.class),
	SHULKER("Shulker", Shulker.class),
	STRAY("Stray", Stray.class),
	EVOKER("Evoker", Evoker.class),
	ENDERMITE("Endermite", Endermite.class),
	VEX("Vex", Vex.class),
	PHANTOM("Phantom", Phantom.class),
	GUARDIAN("Guardian", Guardian.class),
	ELDER_GUARDIAN("Elder Guardian", ElderGuardian.class),
	HUSK("Husk", Husk.class);
	
	public String type;
	public Class<? extends LivingEntity> clazz;
	
	private BossesList(String type, Class<? extends LivingEntity> clazz) {
		this.type = type;
		this.clazz = clazz;
	}
}
