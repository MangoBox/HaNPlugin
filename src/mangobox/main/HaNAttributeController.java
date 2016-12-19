package mangobox.main;

import org.bukkit.entity.Player;

public class HaNAttributeController {

	HaNMain mainClass;
	public HaNAttributeController(HaNMain plugin) {
		mainClass = plugin;
	}
	
	//This class should be used for adding certain aspects ("attributes") to a player based on their overall health.
	
	//Adds to the players max health. Negative values can also be used here.
	public void playerAddMaxHealth(Player player, double amount) {
		double currentMaxHealth = player.getMaxHealth();
		//Makes sure the amount (reassigned to clampedAmount) is clamped between maxHealth and minHealth.
		double clampedAmount = Math.min(mainClass.getConfig().getDouble("maxHealth"), Math.max(mainClass.getConfig().getDouble("minHealth") + 1, amount));
		//If the number is odd, adds another one to make a full heart, as long as the config permits it.
		if((clampedAmount % 2) != 0 && mainClass.getConfig().getBoolean("roundMaxHealthUp")) {
			clampedAmount++;
		}
		int finalAmount = (int) clampedAmount;
		player.setMaxHealth(currentMaxHealth + finalAmount);
	}
	
	public void playerAddSpeed(Player player, double amount) {
		double currentSpeed = player.getWalkSpeed();
		//Makes sure the amount (reassigned to clampedAmount) is clamped between maxHealth and minHealth.
		double clampedAmount = Math.min(mainClass.getConfig().getDouble("maxSpeed"), Math.max(mainClass.getConfig().getDouble("minSpeed"), amount));
		//If the number is odd, adds another one to make a full heart, as long as the config permits it.
		player.setMaxHealth(currentSpeed + clampedAmount);
	}
	

}
