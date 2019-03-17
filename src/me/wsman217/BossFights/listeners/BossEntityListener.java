package me.wsman217.BossFights.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.wsman217.BossFights.BossFights;
import me.wsman217.BossFights.Tools;

public class BossEntityListener implements Listener {

	BossFights plugin;
	Tools tools;

	public BossEntityListener(BossFights plugin) {
		this.plugin = plugin;
		tools = plugin.tools;
	}

	@EventHandler
	public void onSpawn(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.hasItem()) {
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (e.getClickedBlock().getType() == Material.AIR) {
					return;
				}
				Location l = e.getClickedBlock().getLocation().add(0, 1, 0);
				ItemStack i = e.getItem();
				if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
					for (String boss : plugin.getConfig().getConfigurationSection("Bosses").getKeys(false)) {
						String path = "Bosses." + boss + ".SpawnEgg.";
						String name = tools.color(plugin.getConfig().getString(path + "Name"));
						if (i.getItemMeta().getDisplayName().equals(name)) {
							e.setCancelled(true);
							if (player.getGameMode() != GameMode.CREATIVE)
								tools.removeItem(i, player);
							if (l.clone().add(0, 0, 0).getBlock().getType() != Material.AIR
									|| l.clone().add(0, 1, 0).getBlock().getType() != Material.AIR) {
								player.sendMessage(tools.color("&cYou can not spawn a boss there."));
								return;
							}
							plugin.bossEntities.spawnBoss(l, boss);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onEntityCombust(EntityCombustEvent e) {
		Entity entity = e.getEntity();
		if (entity instanceof LivingEntity) {
			LivingEntity en = (LivingEntity) entity;
			if (plugin.bossEntities.Bosses.contains(en)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBossDeath(EntityDeathEvent e) {
		LivingEntity en = e.getEntity();
		if (plugin.bossEntities.Bosses.contains(en)) {
			plugin.bossEntities.Bosses.remove(en);
		}
		if (en.getKiller() instanceof Player) {
			Player player = en.getKiller();
			for (String boss : plugin.getConfig().getConfigurationSection("Bosses").getKeys(false)) {
				String path = "Bosses." + boss + ".Boss.";
				String name = tools.color(plugin.getConfig().getString(path + "Name"));
				if (en.getCustomName() != null && en.getCustomName().equals(name)) {
					if (plugin.getConfig().getBoolean(path + "NatualDrops") == false) {
						e.getDrops().clear();
						e.getDrops().addAll(tools.getItems(boss));
						List<String> msgs = plugin.getConfig().getStringList(path + "MessageToKiller");
						for (String msg : msgs) {
							msg = msg.replaceAll("%Player%", player.getName());
							msg = msg.replaceAll("%player%", player.getName());
							player.sendMessage(tools.color(msg));
						}
						List<String> commands = plugin.getConfig().getStringList(path + "Commands");
						for (String cmd : commands) {
							cmd = cmd.replaceAll("%Player%", player.getName());
							cmd = cmd.replaceAll("%player%", player.getName());
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
						}
						return;
					}
				}
			}
		}
	}
}
