package me.wsman217.BossFights.enums;

public enum Enchantments {
	
	PROTECTION_ENVIROMENTAL("Protection", "protection"),
	PROTECTION_FIRE("Fire Protection", "fire_protection"),
	PROTECTION_FALL("Feather Falling", "feather_falling"),
	PROTECTION_EXPLOSIONS("Blast Protection", "blast_protection"),
	PROTECTION_PROJECTILE("Projectile Protection", "projectile_protection"),
	OXYGEN("Respiration", "respiration"),
	WATER_WORKER("Aqua Infinity", "aqua_affinity"),
	THORNS("Thorns", "thorns"),
	DEPTH_STRIDER("Depth Strider", "depth_strider"),
	FROST_WALKER("Frost Walker", "frost_walker"),
	BINDING_CURSE("Curse of Binding", "binding_curse"),
	DAMAGE_ALL("Sharpness", "sharpness"),
	DAMAGE_UNDEAD("Smite", "smite"),
	DAMAGE_ARTHROPODS("Bane of Arthropods", "bane_of_arthropods"),
	KNOCKBACK("Knockback", "knockback"),
	FIRE_ASPECT("Fire Aspect", "fire_aspect"),
	LOOT_BONUS_MOBS("Looting", "looting"),
	SWEEPING_EDGE("Sweeping Edge", "sweeping"),
	DIG_SPEED("Efficiency", "efficiency"),
	SILK_TOUCH("Silk touch", "silk_touch"),
	DURABILITY("Unbreaking", "unbreaking"),
	LOOT_BONUS_BLOCKS("Fortune", "fortune"),
	ARROW_DAMAGE("Power", "power"),
	ARROW_KNOCKBACK("Punch", "punch"),
	ARROW_FIRE("Flame", "flame"),
	ARROW_INFINITE("Infinity", "infinity"),
	LUCK("Luck of the Sea", "luck_of_the_sea"),
	LURE("Lure", "lure"),
	LOYALTY("Loyalty", "loyalty"),
	IMPALING("Impaling", "impaling"),
	RIPTIDE("Riptide", "riptide"),
	CHANNELING("Channeling", "channeling"),
	MENDING("Mending", "mending"),
	VANISHING_CURSE("Curse of Vanishing", "vanishing_curse"),
	
	NONE("None", "null");
	
	public String name;
	public String key;
	
	private Enchantments(String name, String key) {
		this.name = name;
		this.key = key;
	}
}
