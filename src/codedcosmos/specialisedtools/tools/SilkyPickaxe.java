package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.core.PlayerUtils;
import codedcosmos.specialisedtools.core.TickDelayer;
import codedcosmos.specialisedtools.enums.Orientation;
import codedcosmos.specialisedtools.utils.Vector;
import org.bukkit.GameMode;
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
import java.util.Random;

public class SilkyPickaxe implements Listener {
	
	public static final String PICKAXE_LORE = "Silky Pickaxe";
	public static final int DISTANCE_MINED = 5;
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_PICKAXE, PICKAXE_LORE);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		List<Material> mineMaterials = Arrays.asList(
				Material.STONE,
				Material.GRANITE,
				Material.DIORITE,
				Material.ANDESITE,
				Material.NETHERRACK,
				Material.END_STONE);
		
		Block block = e.getBlock();
		
		// Make sure it's the correct type of block
		if (!mineMaterials.contains(block.getType())) return;
		
		// If not correct lore ignore
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemmeta = item.getItemMeta();
		
		// If item or itemmeta is null ignore
		if (item == null || itemmeta == null || itemmeta.getLore() == null) return;
		if (itemmeta.getLore().size() == 0) return;
		
		if (!itemmeta.getLore().get(0).equals(PICKAXE_LORE)) return;
		
		
		
		// Prepare
		World world = e.getPlayer().getWorld();
		Vector origin = new Vector(block);
		
		ArrayList<Vector> positions = new ArrayList<Vector>(DISTANCE_MINED*9);
		
		Orientation orientation = PlayerUtils.getCardinalDirection(e.getPlayer());
		
		if (orientation == Orientation.N) {
			for (int x = 0; x < DISTANCE_MINED; x++) {
				for (int z = -1; z < 2; z++) {
					for (int y = -1; y < 2; y++) {
						positions.add(new Vector(origin).add( x-DISTANCE_MINED+1, y, z));
					}
				}
			}
		} else if (orientation == Orientation.S) {
			for (int x = 0; x < DISTANCE_MINED; x++) {
				for (int z = -1; z < 2; z++) {
					for (int y = -1; y < 2; y++) {
						positions.add(new Vector(origin).add( x, y, z));
					}
				}
			}
		} else if (orientation == Orientation.E) {
			for (int z = 0; z < DISTANCE_MINED; z++) {
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						positions.add(new Vector(origin).add( x, y, z-DISTANCE_MINED+1));
					}
				}
			}
		} else if (orientation == Orientation.W) {
			for (int z = 0; z < DISTANCE_MINED; z++) {
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						positions.add(new Vector(origin).add( x, y, z));
					}
				}
			}
		}
		
		int blocksBroken = 3;
		for (Vector position : positions) {
			Block check = position.getLocation(world).getBlock();
			
			if (mineMaterials.contains(check.getType())) {
				if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
					check.setType(Material.AIR);
				} else {
					check.breakNaturally(item);
				}
				blocksBroken++;
			}
		}
		blocksBroken*=5;
		
		ItemUtils.removeDurability(e.getPlayer(), item, blocksBroken);
	}
}
