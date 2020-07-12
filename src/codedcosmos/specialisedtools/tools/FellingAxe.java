package codedcosmos.specialisedtools.tools;

import codedcosmos.specialisedtools.core.ItemUtils;
import codedcosmos.specialisedtools.core.MinerState;
import codedcosmos.specialisedtools.utils.Log;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.util.ArrayStack;

import java.util.*;

public class FellingAxe implements Listener {
	
	public static final String AXE_LORE = "Tree Lopper";
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_AXE, AXE_LORE);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		List<Material> logMaterials = Arrays.asList(
				Material.ACACIA_LOG, Material.ACACIA_LEAVES,
				Material.BIRCH_LOG, Material.BIRCH_LEAVES,
				Material.DARK_OAK_LOG, Material.DARK_OAK_LEAVES,
				Material.JUNGLE_LOG, Material.JUNGLE_LEAVES,
				Material.OAK_LOG, Material.OAK_LEAVES,
				Material.SPRUCE_LOG, Material.SPRUCE_LEAVES);
		
		MinerState minerState = new MinerState(e, logMaterials, AXE_LORE, MinerState.BreakDir.UP_DIAGONAL, 32);
	}
}
