package Main;

import java.awt.Color;
import logic.Control;
import Data.Vector2D;
import Data.spriteInfo;
import Data.BoundingBox;
import FileIO.EZFileRead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main{
	// Fields (Static) below...

	public static int index = -1; // for loops
	
	// for sprites in update()
	public static int currentSpriteIndex = 0;                      // used to properly loop thru animation frames
	public static spriteInfo currentSprite;                        // used to store data pulled from the sprite ArrayLists
	public static Vector2D currentCoords = new Vector2D(50, 500);  // soket's coordinates
	
	public static ArrayList<spriteInfo> rightSprites = new ArrayList<>();    // sprites walking right
	public static ArrayList<spriteInfo> downSprites = new ArrayList<>();     // sprites walking down
	public static ArrayList<BoundingBox> boundingBoxes = new ArrayList<>();  // all the bounding boxes (item index matches index in isShowDescriptions)
	
	public static ArrayList<Boolean> isShowDescriptions = new ArrayList<>(); // for inspecting items, 1 element per inspectable item.
	public static String direction = "";                                     // to tell which way soket is facing to inspect items   
	
	// for item descriptions
	public static HashMap<String, String> descriptionMap = new HashMap<>(); // store description lines here
	public static String lineFromFile = "";                                 // store read line
	public static String key = "";                                          // key for HashMap
	public static String value = "";                                        // value from the key/value pair
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)

		EZFileRead readFile = new EZFileRead("descriptions.txt");	// connect to the text file using readFile variable
		
		for (int index = 0; index <= 1; index++) {
			lineFromFile = readFile.getLine(index); // read next line from the .txt file
			StringTokenizer string = new StringTokenizer(lineFromFile, "*");
			
			key = string.nextToken();    // get key from line
			value = string.nextToken();  // get value from line
			descriptionMap.put(key, value); // add key/value pair to the HashMap
		}
		
		// load right walking frames into ArrayList
		int frameToAdd = 0;	// start at the first frame, "walk0"
		for (frameToAdd = 0; frameToAdd < 8; frameToAdd++) {
			rightSprites.add(new spriteInfo(new Vector2D(50, 500), "sokRight" + frameToAdd));
		}
		
		// load down walking frames into ArrayList
		//for (frameToAdd = 0; frameToAdd < 1; frameToAdd++) {
		downSprites.add(new spriteInfo(new Vector2D(50, 500), "soketFront"));
		//}
		
		currentSprite = rightSprites.get(0);  // starting sprite
		
		// set up all BoundingBox's
		boundingBoxes.add(new BoundingBox(-128, -128, -128, -128));  // [0] Soket's bounding box (will change)
		boundingBoxes.add(new BoundingBox(720, 240, 800, 320));      // [1] toaster's bounding box
		//fridge
		boundingBoxes.add(new BoundingBox(50, 50, 50, 670));         //  |  left bound
		boundingBoxes.add(new BoundingBox(50, 50, 1230, 50));        //  |  top bound
		boundingBoxes.add(new BoundingBox(1230, 50, 1230, 670));     //  V  right bound
		boundingBoxes.add(new BoundingBox(50, 670, 1230, 670));      // [6] bottom bound
		
		// set up all isShowDescriptions, start false so by default a description won't be shown
		isShowDescriptions.add(false);  // [0] will not be used
		isShowDescriptions.add(false);	// [1] toaster
		isShowDescriptions.add(false);  // [2] fridge
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		
		ctrl.addSpriteToFrontBuffer(50, 50, "gameBackground");  // the background
		ctrl.addSpriteToFrontBuffer(720, 240, "gameToaster");   // the toaster
		ctrl.drawString(20, 20, direction, Color.GREEN);
		
		// TEST
		if (isShowDescriptions.get(1)) {
			ctrl.drawString(20, 50, "true", Color.GREEN);
		} else ctrl.drawString(20, 50, "false", Color.GREEN);
		
		if (isAnyDescriptionShown()) {
			ctrl.drawString(20, 100, "shown", Color.GREEN);
		} else ctrl.drawString(20, 100, "not shown", Color.GREEN);
		
		// TEST
		String sokLine = descriptionMap.get("description" + 0); 
		ctrl.drawString(20, 170, sokLine, Color.WHITE);
		
		// TEST - show sprite bounding box
		ctrl.drawString(200, 300, "x1: " + boundingBoxes.get(0).getX1(), Color.WHITE);
		ctrl.drawString(200, 325, "x2: " + boundingBoxes.get(0).getX2(), Color.WHITE);
		ctrl.drawString(200, 350, "y1: " + boundingBoxes.get(0).getY1(), Color.WHITE);
		ctrl.drawString(200, 375, "y2: " + boundingBoxes.get(0).getY2(), Color.WHITE);
		// 
		
		// TEST - show toaster bounding box
		ctrl.drawString(400, 325, "x1: " + boundingBoxes.get(1).getX1(), Color.WHITE);
		ctrl.drawString(400, 300, "x2: " + boundingBoxes.get(1).getX2(), Color.WHITE);
		ctrl.drawString(400, 375, "y1: " + boundingBoxes.get(1).getY1(), Color.WHITE);
		ctrl.drawString(400, 350, "y2: " + boundingBoxes.get(1).getY2(), Color.WHITE);
		//
		
		// show description of item
		for (index = 1; index <= 2; index++) {
			if (isShowDescriptions.get(index)) {
				ctrl.drawString(boundingBoxes.get(index).getX1(), boundingBoxes.get(index).getY1(), descriptionMap.get("description" + index), Color.CYAN);
			}
		}
		
		ctrl.addSpriteToFrontBuffer(currentCoords.getX(), currentCoords.getY(), currentSprite.getTag());  // update sprite and coordinates based on key pressed		
	}
	
	// Additional Static methods below...(if needed)
	
	// used to "freeze" soket when a description is being shown on screen
	public static boolean isAnyDescriptionShown() {  
		boolean isAnyDescriptionShown = false;
		
		for (index = 1; index <= 2; index++) {
			if (isShowDescriptions.get(index)) {
				isAnyDescriptionShown = true;
			}
		}
		
		return isAnyDescriptionShown;
	}

}
