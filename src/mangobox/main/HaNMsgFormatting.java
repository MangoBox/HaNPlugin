package mangobox.main;

import org.bukkit.entity.Player;

public class HaNMsgFormatting {

	public HaNMsgFormatting() {
		// TODO Auto-generated constructor stub
	}
	
	public String returnPercentageBar(float barAmount, int sectors, boolean showEndBrackets, String endBracketColour, String emptyBarColour, String fullBarColour) {
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
				bar += fullBarColour + "-";
			} else {
				bar += emptyBarColour + "-";
			}
		}
		
		if(showEndBrackets) {
			bar += endBracketColour + "]";
		}
		
		return bar;
	}
	
	public void sendPlayerNutritionMessage(Player player) {
		HaNValueManagement valueManagement = new HaNValueManagement();
		player.sendMessage("�2-----�aHealth Stats�2-----");
		//Total
		player.sendMessage("�b" + valueManagement.getTotalFoodLevel(player) + "% " + returnPercentageBar((float)valueManagement.getTotalFoodLevel(player), 10, true, "�b", "�3", "�b") + "�b - Total health");
		player.sendMessage("�c" + valueManagement.getPlayerFruitLevel(player) + "% " + returnPercentageBar((float)valueManagement.getPlayerFruitLevel(player), 10, true, "�c", "�4", "�c") + "�c - Fruit");
		player.sendMessage("�f" + valueManagement.getPlayerMeatLevel(player) + "% " + returnPercentageBar((float)valueManagement.getPlayerMeatLevel(player), 10, true, "�f", "�f", "�e") + "�f - Meat");
		player.sendMessage("�6" + valueManagement.getPlayerGrainLevel(player) + "% " + returnPercentageBar((float)valueManagement.getPlayerGrainLevel(player), 10, true, "�6", "�6", "�e") + "�b - Grain");
		player.sendMessage("�7" + valueManagement.getPlayerFishLevel(player) + "% " + returnPercentageBar((float)valueManagement.getPlayerFishLevel(player), 10, true, "�7", "�8", "�7") + "�7 - Fish");
		player.sendMessage("�a" + valueManagement.getPlayerVegetableLevel(player) + "% " + returnPercentageBar((float)valueManagement.getPlayerVegetableLevel(player), 10, true, "�a", "�2", "�a") + "�a - Vegetable");
		
	}

}
