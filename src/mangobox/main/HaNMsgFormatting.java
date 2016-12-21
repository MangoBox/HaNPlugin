package mangobox.main;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

public class HaNMsgFormatting {

	public HaNMsgFormatting() {
		// TODO Auto-generated constructor stub
	}
	
	public String returnPercentageBar(float barAmount, int sectors, boolean showEndBrackets, String endBracketColour, String emptyBar, String fullBar) {
		String bar = "";
		//Adds the initial bracket.
		if(showEndBrackets) {
			bar += endBracketColour + "[";
		}
		
		//Floors the amount of bars to fill.
		int barsToFill = (int) Math.ceil(barAmount) / sectors;
		
		//Creates the actual bar.
		for(int i = 0; i < sectors; i++) {
			if(barsToFill <= i) {
				bar += fullBar;
			} else {
				bar += emptyBar;
			}
		}
		
		if(showEndBrackets) {
			bar += endBracketColour + "]";
		}
		
		return bar;
	}
	
	public void sendPlayerNutritionMessage(Player player, HaNMain mainClass) {
		HaNValueManagement valueManagement = new HaNValueManagement(mainClass);
		DecimalFormat df = new DecimalFormat("#.#");
		player.sendMessage("§2-----§aHealth Stats§2-----");
		//Total
		player.sendMessage(returnPercentageBar((float)valueManagement.getTotalFoodLevel(player), 10, true, "§b", "§3▓", "§b░") + " §b" + df.format(valueManagement.getTotalFoodLevel(player)) + "% " + "§bTotal health");
		player.sendMessage(returnPercentageBar((float)valueManagement.getPlayerFruitLevel(player), 10, true, "§c", "§4▓", "§c░") + " §c" + df.format(valueManagement.getPlayerFruitLevel(player)) + "% " + "§cFruit");
		player.sendMessage(returnPercentageBar((float)valueManagement.getPlayerMeatLevel(player), 10, true, "§f", "§f▓", "§e░") + " §f" + df.format(valueManagement.getPlayerMeatLevel(player)) + "% " +  "§fMeat");
		player.sendMessage(returnPercentageBar((float)valueManagement.getPlayerGrainLevel(player), 10, true, "§6", "§6▓", "§e░") + " §6" + df.format(valueManagement.getPlayerGrainLevel(player)) + "% " +"§bGrain");
		player.sendMessage(returnPercentageBar((float)valueManagement.getPlayerFishLevel(player), 10, true, "§7", "§8▓", "§7░") + " §7" + df.format(valueManagement.getPlayerFishLevel(player)) + "% " +  "§7Fish");
		player.sendMessage(returnPercentageBar((float)valueManagement.getPlayerVegetableLevel(player), 10, true, "§a", "§2▓", "§a░") + " §a" + df.format(valueManagement.getPlayerVegetableLevel(player)) + "% " + "§aVegetable");
		player.sendMessage("§2-----§aHealth Stats§2-----");
	}
	
}
