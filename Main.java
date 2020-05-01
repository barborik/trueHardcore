import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	boolean death = false;
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		if(death) {
			for(World w : Bukkit.getWorlds()) {
				Bukkit.unloadWorld(w, true);
				File worldFolder = new File(w.getName());
				try {
					FileUtils.deleteDirectory(worldFolder);
				} catch (Exception e) {
				}
			}
		}
	}
	
	@EventHandler
	public void deleteWorld(PlayerDeathEvent event) {
		death = true;
		Bukkit.shutdown();
	}
}
