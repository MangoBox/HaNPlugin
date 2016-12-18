package mangobox.main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class HaNFoodIntakeController implements Listener {

	HaNMain mainClass;
	HaNValueManagement valueManagement;
	
	public HaNFoodIntakeController(HaNMain plugin) {
		mainClass = plugin;
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		Material itemMat = item.getType();
		valueManagement.addPlayerFishLevel(player, valueManagement.getFoodFishValue(itemMat));
		valueManagement.addPlayerFruitLevel(player, valueManagement.getFoodFishValue(itemMat));
		valueManagement.addPlayerMeatLevel(player, valueManagement.getFoodFishValue(itemMat));
		valueManagement.addPlayerGrainLevel(player, valueManagement.getFoodFishValue(itemMat));
		valueManagement.addPlayerVegetableLevel(player, valueManagement.getFoodFishValue(itemMat));
		
	}

}
