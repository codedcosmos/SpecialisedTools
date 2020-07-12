package codedcosmos.specialisedtools.core;

import codedcosmos.specialisedtools.commands.GetSpecialTool;
import codedcosmos.specialisedtools.tools.*;
import codedcosmos.specialisedtools.utils.Log;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolsPlugin extends JavaPlugin {
	
	public static JavaPlugin mainPlugin;
	
	@Override
	public void onEnable(){
		Log.print("Enabling SpecialisedTools");
		
		getCommand("getspecialtool").setExecutor(new GetSpecialTool());
		getCommand("getspecialtool").setTabCompleter(new GetSpecialTool());
		
		getServer().getPluginManager().registerEvents(new FellingAxe(), this);
		getServer().getPluginManager().registerEvents(new VeinPickaxe(), this);
		getServer().getPluginManager().registerEvents(new HarvestHoe(), this);
		getServer().getPluginManager().registerEvents(new EarthMovingShovel(), this);
		getServer().getPluginManager().registerEvents(new AetherSword(), this);
		getServer().getPluginManager().registerEvents(new SilkyPickaxe(), this);
	}
	
	public void archiveLoop() {
	
	}
	
	@Override
	public void onDisable(){
		//Fired when the server stops and disables all plugins
		Log.print("Disabiling EnderBot");
	}
}