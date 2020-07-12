package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.utils.Log;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class AetherSword implements Listener {
	
	public static final String SWORD_LORE = "Aether Sword";
	public static final double VELOCITY_BUFFER = 0.25;
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_SWORD, SWORD_LORE);
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		// Check for left click event
		if (!(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR)) return;
		
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		// If not correct lore ignore
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (item == null || itemmeta == null || itemmeta.getLore() == null) return;
		if (itemmeta.getLore().size() == 0) return;
		
		if (!itemmeta.getLore().get(0).equals(SWORD_LORE)) return;
		
		// Check if player is not moving up or down
		Vector vel = e.getPlayer().getVelocity();
		double speed = Math.abs(vel.getX()+vel.getY()+vel.getZ());
		if (speed > VELOCITY_BUFFER) return;
		Log.print(speed);
		
		Vector vector = e.getPlayer().getLocation().getDirection();
		vector.multiply(3);
		vector.setY(vector.getY()/2+1);
		e.getPlayer().setVelocity(vector);
		
		ItemUtils.removeDurability(e.getPlayer(), item, 5);
	}
	
	
}