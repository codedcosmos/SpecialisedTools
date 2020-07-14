package codedcosmos.specialisedtools.core;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemUtils {
	public static void givePlayerSpecialItem(Player player, Material material, String lore) {
		ItemStack item = new ItemStack(material);
		
		item.setAmount(1);
		
		ItemMeta meta = item.getItemMeta();
		List<String> loreList = new ArrayList<String>(1);
		loreList.add(lore);
		meta.setLore(loreList);
		
		item.setItemMeta(meta);
		
		player.getInventory().addItem(item);
	}
}
