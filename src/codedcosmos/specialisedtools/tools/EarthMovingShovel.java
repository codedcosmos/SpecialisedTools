package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.utils.Log;
import codedcosmos.specialisedtools.utils.Vector;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
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
		
		List<String> shovelMaterials = Arrays.asList(
				"minecraft:grass_block",
				"minecraft:dirt",
				"minecraft:coarse_dirt",
				"minecraft:gravel",
				"minecraft:sand",
				"minecraft:soul_sand",
				"minecraft:red_sand",
				"minecraft:soul_soil",
				"minecraft:snow",
				"minecraft:snow_block",
				"minecraft:clay",
				"minecraft:mycelium",
				"minecraft:podzol",
				"minecraft:concrete_powder");
		
		Block block = e.getBlock();
		
		// If not accepter material ignore
		Log.printErr(block.getBlockData().getAsString());
		String blockname = block.getBlockData().getAsString();
		// Remove [datatags]
		blockname = blockname.split("\\[")[0];
		if (!shovelMaterials.contains(blockname)) return;
		
		// If not correct lore ignore
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (itemmeta == null || itemmeta.getLore() == null) return;
		if (itemmeta.getLore().size() == 0) return;
		
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
		
		for (Vector position : positions) {
			Block check = position.getLocation(world).getBlock();
			
			String checkBlockName = check.getBlockData().getAsString();
			// Remove [datatags]
			checkBlockName = checkBlockName.split("\\[")[0];
			if (shovelMaterials.contains(blockname)) {
				check.breakNaturally(item);
			}
		}
	}
}
