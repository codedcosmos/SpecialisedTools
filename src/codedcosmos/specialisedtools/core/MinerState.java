package codedcosmos.specialisedtools.core;

import codedcosmos.specialisedtools.utils.Log;
import codedcosmos.specialisedtools.utils.Vector;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerState {
	
	// Enum
	public enum BreakDir {
		DIRECT(),
		UP_DIAGONAL();
	}
	
	// Basics
	private List<String> materials;
	private int max_blocks;
	
	// Locations
	private ArrayList<Vector> parsedLocations;
	private ArrayList<Vector> blockLocations;
	private ArrayList<Vector> breakLocations;
	
	// Working
	private World world;
	private Material currentLogType;
	
	private int blocksBroken = 0;
	
	public MinerState(BlockBreakEvent e, List<String> materials, String lore, BreakDir dir, int max_blocks) {
		this.materials = materials;
		this.max_blocks = max_blocks;
		
		Block block = e.getBlock();
		
		// If not accepter material ignore
		Log.printErr(block.getBlockData().getAsString());
		String blockname = block.getBlockData().getAsString();
		// Remove [datatags]
		blockname = blockname.split("\\[")[0];
		if (!materials.contains(blockname)) return;
		
		// If not correct lore ignore
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (item == null || itemmeta == null || itemmeta.getLore() == null) return;
		if (itemmeta.getLore().size() == 0) return;
		
		if (!itemmeta.getLore().get(0).equals(lore)) return;
		
		// Prepare
		currentLogType = block.getType();
		world = e.getPlayer().getWorld();
		Player p = e.getPlayer();
		
		parsedLocations = new ArrayList<Vector>(max_blocks*2);
		blockLocations = new ArrayList<Vector>(max_blocks);
		blockLocations.add(new Vector(block.getLocation()));
		
		breakLocations = new ArrayList<Vector>(max_blocks);
		
		// Iterate
		for (int i = 0; i < max_blocks; i++) {
			if (blockLocations.isEmpty()) break;
			
			Vector origin = blockLocations.get(0);
			blockLocations.remove(0);
			
			if (dir == BreakDir.DIRECT || dir == BreakDir.UP_DIAGONAL) {
				processLocation(new Vector(origin).add( 1, 0, 0));
				processLocation(new Vector(origin).add(-1, 0, 0));
				
				processLocation(new Vector(origin).add( 0, 0, 1));
				processLocation(new Vector(origin).add( 0, 0,-1));
				
				processLocation(new Vector(origin).add( 0, 1, 0));
				processLocation(new Vector(origin).add( 0,-1, 0));
			}
			
			if (dir == BreakDir.UP_DIAGONAL) {
				processLocation(new Vector(origin).add( 1, 1, 0));
				processLocation(new Vector(origin).add(-1, 1, 0));
				processLocation(new Vector(origin).add( 0, 1, 1));
				processLocation(new Vector(origin).add( 0, 1,-1));
			}
		}
		
		for (Vector location : breakLocations) {
			blocksBroken++;
			Block log = location.getLocation(world).getBlock();
			if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
				log.setType(Material.AIR);
			} else {
				log.breakNaturally(item);
			}
			
		}
	}
	
	public void processLocation(Vector location) {
		for (Vector check : parsedLocations) {
			if (check.equalsVector(location)) {
				
				// Already processed
				return;
			}
		}
		parsedLocations.add(location);
		
		if (location.getLocation(world).getBlock().getType().equals(currentLogType)) {
			breakLocations.add(location);
			blockLocations.add(location);
		}
	}
}
