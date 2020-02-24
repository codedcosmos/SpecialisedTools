package codedcosmos.specialisedtools.commands;

import codedcosmos.specialisedtools.tools.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GetSpecialTool implements CommandExecutor, TabExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				
				// Make sure the player requested a tool
				if (args.length == 0) {
					player.sendMessage("You must enter the tool you want.");
					return false;
				}
				
				// Make sure its a valid input
				if (args[0].equals("axe")) {
					FellingAxe.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " axe");
				} else if (args[0].equals("fortunepick")) {
					VeinPickaxe.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " fortunepick");
				} else if (args[0].equals("hoe")) {
					HarvestHoe.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " hoe");
				} else if (args[0].equals("shovel")) {
					EarthMovingShovel.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " shovel");
				} else if (args[0].equals("sword")) {
					AetherSword.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " sword");
				} else if (args[0].equals("silkpick")) {
					SilkyPickaxe.giveTool(player);
					player.sendMessage("Giving " + player.getName() + " silkpick");
				}
				
				return true;
			} else {
				player.sendMessage("You must be op to use this command");
			}
		}
		return false;
	}
	
	
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		List<String> result = new ArrayList<String>();
		result.add("axe");
		result.add("fortunepick");
		result.add("hoe");
		result.add("shovel");
		result.add("sword");
		result.add("silkpick");
		return result;
	}
}
