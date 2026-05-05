package net.enelson.sopmobguard.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import net.enelson.sopli.lib.SopLib;
import net.enelson.sopmobguard.SopMobGuard;

public class Handler implements Listener {
	private final Set<Entity> monsters;
	private final int cooldown = 2;

	public Handler() {
		this.monsters = new HashSet<Entity>();
	}

	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}

		if (!(e.getEntity() instanceof Monster)) {
			return;
		}

		Entity monster = e.getEntity();
		if (!this.checkEntityName(monster)) {
			return;
		}

		Player player = null;
		if (e.getDamager() instanceof Player) {
			player = (Player) e.getDamager();
		} else if (e.getDamager() instanceof Tameable) {
			Tameable pet = (Tameable) e.getDamager();
			if (!pet.isTamed()) {
				return;
			}

			if (pet.getOwner() instanceof Player) {
				player = (Player) pet.getOwner();
			}
		} else {
			ProjectileSource source = null;
			if (e.getDamager() instanceof Projectile) {
				source = ((Projectile) e.getDamager()).getShooter();
			}

			if (source instanceof Player) {
				player = (Player) source;
			}
		}

		if (player == null) {
			return;
		}

		if (!this.checkPlayer(monster, player)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onTarget(EntityTargetLivingEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}

		if (e.getEntity() instanceof Monster) {
			if (this.monsters.contains(e.getEntity())) {
				e.setCancelled(true);
				return;
			}

			Entity monster = e.getEntity();
			if (!this.checkEntityName(monster)) {
				return;
			}

			Player player = null;
			if (e.getTarget() instanceof Player) {
				player = (Player) e.getTarget();
			} else if (e.getTarget() instanceof Tameable) {
				Tameable pet = (Tameable) e.getTarget();
				if (!pet.isTamed()) {
					return;
				}

				if (pet.getOwner() instanceof Player) {
					player = (Player) pet.getOwner();
				}
			}

			if (player == null) {
				return;
			}

			if (!this.checkPlayer(monster, player)) {
				e.setCancelled(true);
				this.monsters.add(e.getEntity());
				Bukkit.getScheduler().runTaskLater(SopMobGuard.getInstance(), new Runnable() {
					@Override
					public void run() {
						monsters.remove(e.getEntity());
					}
				}, this.cooldown * 20L);
			}
		} else if (e.getEntity() instanceof Tameable) {
			if (this.monsters.contains(e.getTarget())) {
				e.setCancelled(true);
				return;
			}

			Tameable pet = (Tameable) e.getEntity();
			if (!pet.isTamed()) {
				return;
			}

			if (!(pet.getOwner() instanceof Player)) {
				return;
			}

			Entity monster = e.getTarget();
			Player player = (Player) pet.getOwner();
			if (!this.checkPlayer(monster, player)) {
				e.setCancelled(true);
				this.monsters.add(e.getTarget());
				Bukkit.getScheduler().runTaskLater(SopMobGuard.getInstance(), new Runnable() {
					@Override
					public void run() {
						monsters.remove(e.getTarget());
					}
				}, this.cooldown * 20L);
			}
		}
	}

	private boolean checkEntityName(Entity entity) {
		if (entity.getCustomName() == null) {
			return false;
		}

		String name = entity.getCustomName().replaceAll("§[0-9a-fk-or]", "");
		return name.equals(SopMobGuard.getInstance().getConfigManager().getConfigs().getString("protected-monster-name"));
	}

	private boolean checkPlayer(Entity entity, Player player) {
		if (SopLib.getInstance() == null) {
			return true;
		}
		return SopLib.getInstance().getProtectionService().isRegionMemberOrOwner(player, entity.getLocation());
	}
}
