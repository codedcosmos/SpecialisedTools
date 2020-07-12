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
	
	public static void removeDurability(Player player, ItemStack item, int damageAmount) {
		// Don't remove durability if in creative
		if (player.getGameMode() == GameMode.CREATIVE) return;
		
		int unbreaking = item.getEnchantmentLevel(Enchantment.DURABILITY);
		
		if (unbreaking > 0) {
			Random random = new Random();
			
			// http://minecraft.gamepedia.com/Enchantment#Enchantments
			int chance = 100 / (unbreaking + 1);
			int oldDamage = damageAmount;
			
			for (int i = 0; i < oldDamage; i++) {
				if (random.nextInt(100) > chance) damageAmount--;
			}
		}
		
		item.setDurability((short) (item.getDurability() + damageAmount));
		
		if (item.getDurability() > item.getData().getItemType().getMaxDurability()) {
			item.setAmount(0);
			player.getInventory().setItemInMainHand(null);
		}
	}
}
