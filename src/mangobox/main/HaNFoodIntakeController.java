package mangobox.main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class HaNFoodIntakeController implements Listener {

	HaNMain mainClass;
	
	public HaNFoodIntakeController(HaNMain plugin) {
		mainClass = plugin;
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		Material itemMat = item.getType();
	}

}
