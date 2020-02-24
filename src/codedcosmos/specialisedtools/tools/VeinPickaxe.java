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
	
	public static final String PICKAXE_LORE = "Vein Pickaxe";
	
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
		
		List<Material> oreMaterials = Arrays.asList(
				Material.COAL_ORE,
				Material.DIAMOND_ORE,
				Material.EMERALD_ORE,
				Material.GOLD_ORE,
				Material.IRON_ORE,
				Material.LAPIS_ORE,
				Material.NETHER_QUARTZ_ORE,
				Material.REDSTONE_ORE);
		
		MinerState minerState = new MinerState(e, oreMaterials, PICKAXE_LORE, MinerState.BreakDir.DIRECT, 16);
	}
}
