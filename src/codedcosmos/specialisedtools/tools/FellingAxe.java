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
		
		List<String> logMaterialNames = Arrays.asList(
				"minecraft:acacia_log", "minecraft:acacia_leaves",
				"minecraft:birch_log", "minecraft:birch_leaves",
				"minecraft:dark_oak_log", "minecraft:dark_oak_leaves",
				"minecraft:jungle_log", "minecraft:jungle_leaves",
				"minecraft:oak_log", "minecraft:oak_leaves",
				"minecraft:spruce_log", "minecraft:spruce_leaves",
				"minecraft:warped_stem", "minecraft:warped_wart_block",
				"minecraft:crimson_stem", "minecraft:nether_wart_block");
		
		MinerState minerState = new MinerState(e, logMaterialNames, AXE_LORE, MinerState.BreakDir.UP_DIAGONAL, 32);
	}
}
