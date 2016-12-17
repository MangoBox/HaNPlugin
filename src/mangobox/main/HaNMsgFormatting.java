package mangobox.main;

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

}
