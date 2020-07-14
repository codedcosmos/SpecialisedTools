package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.MinerState;
import org.bukkit.Material;
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

public class VeinPickaxe implements Listener {
	
	public static final String PICKAXE_LORE = "Vein Miner";
	
	public static void giveTool(Player player) {
		ItemStack veinPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		
		veinPickaxe.setAmount(1);
		
		ItemMeta meta = veinPickaxe.getItemMeta();
		List<String> lore = new ArrayList<String>(1);
		lore.add(PICKAXE_LORE);
		meta.setLore(lore);
		
		veinPickaxe.setItemMeta(meta);
		
		player.getInventory().addItem(veinPickaxe);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		List<String> oreMaterials = Arrays.asList(
				"minecraft:coal_ore",
				"minecraft:diamond_ore",
				"minecraft:emerald_ore",
				"minecraft:gold_ore",
				"minecraft:iron_ore",
				"minecraft:lapis_ore",
				"minecraft:nether_quartz_ore",
				"minecraft:redstone_ore");
		
		MinerState minerState = new MinerState(e, oreMaterials, PICKAXE_LORE, MinerState.BreakDir.DIRECT, 16);
	}
}
