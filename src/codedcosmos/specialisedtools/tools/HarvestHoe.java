package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.core.MinerState;
import codedcosmos.specialisedtools.utils.Log;
import codedcosmos.specialisedtools.utils.Vector;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HarvestHoe implements Listener {
	
	public static final String HOE_LORE = "Harvest Hoe";
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_HOE, HOE_LORE);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		Block block = e.getClickedBlock();
		
		// If not accepter material ignore
		boolean type = block.getType().equals(Material.DIRT) || block.getType().equals(Material.GRASS_BLOCK);
		if (!type) return;
		
		// Make sure it's a hoe
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		Material tool = item.getType();
		boolean isHoe = tool.equals(Material.DIAMOND_HOE) || tool.equals(Material.IRON_HOE) || tool.equals(Material.GOLDEN_HOE) || tool.equals(Material.STONE_HOE) || tool.equals(Material.WOODEN_HOE);
		if (!isHoe) return;
		
		// If not correct lore ignore
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (item == null || itemmeta == null) return;
		
		if (!itemmeta.getLore().get(0).equals(HOE_LORE)) return;
		
		
		
		
		// Prepare
		World world = e.getPlayer().getWorld();
		Vector origin = new Vector(block);
		
		// Set current block as farmland
		block.setType(Material.FARMLAND);
		
		ArrayList<Vector> positions = new ArrayList<Vector>(8);
		
		positions.add(new Vector(origin).add( 1, 0, 1));
		positions.add(new Vector(origin).add(-1, 0,-1));
		
		positions.add(new Vector(origin).add( 1, 0,-1));
		positions.add(new Vector(origin).add(-1, 0, 1));
		
		positions.add(new Vector(origin).add( 1, 0, 0));
		positions.add(new Vector(origin).add(-1, 0, 0));
		
		positions.add(new Vector(origin).add( 0, 0, 1));
		positions.add(new Vector(origin).add( 0, 0,-1));
		
		int blocksConvertedToFarmland = 1;
		for (Vector position : positions) {
			Block check = position.getLocation(world).getBlock();
			type = check.getType().equals(Material.DIRT) || check.getType().equals(Material.GRASS_BLOCK);
			
			if (type) {
				check.setType(Material.FARMLAND);
				blocksConvertedToFarmland++;
			}
		}
		
		ItemUtils.removeDurability(e.getPlayer(), item, blocksConvertedToFarmland);
	}
}
