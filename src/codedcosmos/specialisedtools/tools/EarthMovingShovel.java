package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.utils.Vector;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EarthMovingShovel implements Listener {
	
	public static final String SHOVEL_LORE = "Earth Moving Shovel";
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_SHOVEL, SHOVEL_LORE);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		List<Material> shovelMaterials = Arrays.asList(
				Material.GRASS_BLOCK,
				Material.DIRT,
				Material.COARSE_DIRT,
				Material.GRAVEL,
				Material.SAND,
				Material.SOUL_SAND);
		
		Block block = e.getBlock();
		
		// Make sure it's the correct type of block
		if (!shovelMaterials.contains(block.getType())) return;
		
		// If not correct lore ignore
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (item == null || itemmeta == null) return;
		
		if (!itemmeta.getLore().get(0).equals(SHOVEL_LORE)) return;
		
		
		
		// Prepare
		World world = e.getPlayer().getWorld();
		Vector origin = new Vector(block);
		
		ArrayList<Vector> positions = new ArrayList<Vector>(8);
		
		positions.add(new Vector(origin).add( 1, 0, 1));
		positions.add(new Vector(origin).add(-1, 0,-1));
		
		positions.add(new Vector(origin).add( 1, 0,-1));
		positions.add(new Vector(origin).add(-1, 0, 1));
		
		positions.add(new Vector(origin).add( 1, 0, 0));
		positions.add(new Vector(origin).add(-1, 0, 0));
		
		positions.add(new Vector(origin).add( 0, 0, 1));
		positions.add(new Vector(origin).add( 0, 0,-1));
		
		int blocksBroken = 1;
		for (Vector position : positions) {
			Block check = position.getLocation(world).getBlock();
			
			if (shovelMaterials.contains(check.getType())) {
				check.setType(Material.AIR);
				blocksBroken++;
			}
		}
		
		ItemUtils.removeDurability(e.getPlayer(), item, blocksBroken);
	}
}
