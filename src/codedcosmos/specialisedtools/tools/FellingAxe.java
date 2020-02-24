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
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.util.ArrayStack;

import java.util.*;

public class FellingAxe implements Listener {
	
	public static final String AXE_LORE = "Felling Axe";
	
	public static void giveTool(Player player) {
		ItemUtils.givePlayerSpecialItem(player, Material.DIAMOND_AXE, AXE_LORE);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
		// Make sure the player is shifting
		if (!e.getPlayer().isSneaking()) return;
		
		List<Material> logMaterials = Arrays.asList(
				Material.ACACIA_LOG,
				Material.BIRCH_LOG,
				Material.DARK_OAK_LOG,
				Material.JUNGLE_LOG,
				Material.OAK_LOG,
				Material.SPRUCE_LOG);
		
		MinerState minerState = new MinerState(e, logMaterials, AXE_LORE, MinerState.BreakDir.UP_DIAGONAL, 32);
	}
}
