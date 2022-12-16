/* This will handle the "Hot Key" system. */

package Main;

import Data.BoundingBox;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(70);
	private static stopWatchX timer = new stopWatchX(70);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')	{
		
			//Main.currentSprite.setTag("soketFront");

			return;	
		}
		
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		
		case 'w':
			if (Main.isAnyDescriptionShown()) break;  // do not allow movement if an item is being inspected
			
			Main.direction = "up";
			if (timer.isTimeUp()) {
				++Main.currentSpriteIndex;
				if (Main.currentSpriteIndex > 7) {	// when last element of the ArrayList is reached
					Main.currentSpriteIndex = 0;	// reset to the first element, which is the first frame
				}
				
				Main.currentSprite = Main.upSprites.get(Main.currentSpriteIndex);	// get next frame
				Main.currentCoords.setY(Main.currentCoords.getY() - 8);                 // move up 8 pixels
				Main.currentSprite.setCoords(Main.currentCoords.getX(),Main. currentCoords.getY());	// update y to be 8 pixels to the up, x stays the same\
				
				// update bounding boxes
				Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
				Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
				Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
				Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
				
				// check for collision
				for (int index = 1; index <= 5; index++) {
					if (BoundingBox.isCollision(Main.boundingBoxes.get(0), Main.boundingBoxes.get(index))) {
						Main.currentCoords.setY(Main.currentCoords.getY() + 8);                             // collision detected, undo the coordinate change
						Main.currentSprite.setCoords(Main.currentCoords.getX(),Main.currentCoords.getY());  // reset coords to before collision
						
						// undo the sprite index change
						if (Main.currentSpriteIndex != 0) {  
							--Main.currentSpriteIndex;
						}
						else {  // if the index is 0, -- would make it -1, so set it to the last index instead
							Main.currentSpriteIndex = 7;
						}
						
						Main.currentSprite = Main.upSprites.get(Main.currentSpriteIndex); // reset sprite to before collision
						
						// reset bounding boxes to before collision
						Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
						Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
						Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
						Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
					}
				}
				
				timer.resetWatch();
			}
				
			break;
			  
		case 'a':
			if (Main.isAnyDescriptionShown()) break;  // do not allow movement if an item is being inspected
			
			Main.direction = "left";
			if (timer.isTimeUp()) {
				++Main.currentSpriteIndex;
				if (Main.currentSpriteIndex > 7) {	// when last element of the ArrayList is reached
					Main.currentSpriteIndex = 0;	// reset to the first element, which is the first frame
				}
				
				Main.currentSprite = Main.leftSprites.get(Main.currentSpriteIndex);	// get next frame
				Main.currentCoords.setX(Main.currentCoords.getX() - 8);                 
				Main.currentSprite.setCoords(Main.currentCoords.getX(),Main. currentCoords.getY());	// update x to be 8 pixels to the left, y stays the same
				
				// update bounding boxes
				Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
				Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
				Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
				Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
				
				// check for collision
				for (int index = 1; index <= 5; index++) {
					if (BoundingBox.isCollision(Main.boundingBoxes.get(0), Main.boundingBoxes.get(index))) {
						Main.currentCoords.setX(Main.currentCoords.getX() + 8);                             // collision detected, undo the coordinate change
						Main.currentSprite.setCoords(Main.currentCoords.getX(),Main.currentCoords.getY());  // reset coords to before collision
						
						// undo the sprite index change
						if (Main.currentSpriteIndex != 0) {  
							--Main.currentSpriteIndex;
						}
						else {  // if the index is 0, -- would make it -1, so set it to the last index instead
							Main.currentSpriteIndex = 7;
						}
						
						Main.currentSprite = Main.leftSprites.get(Main.currentSpriteIndex); // reset sprite to before collision
						
						// reset bounding boxes to before collision
						Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
						Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
						Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
						Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
					}
				}
				
				timer.resetWatch();
			}
			
			break;
			
		case 's':
			if (Main.isAnyDescriptionShown()) break;  // do not allow movement if an item is being inspected 
			
			Main.direction = "down";
			if (timer.isTimeUp()) {
				++Main.currentSpriteIndex;
				if (Main.currentSpriteIndex > 7) {	// when last element of the ArrayList is reached
					Main.currentSpriteIndex = 0;	// reset to the first element, which is the first frame
				}
				
				Main.currentSprite = Main.downSprites.get(Main.currentSpriteIndex);	// get next frame
				Main.currentCoords.setY(Main.currentCoords.getY() + 8);                  
				Main.currentSprite.setCoords(Main.currentCoords.getX(),Main. currentCoords.getY());	// update y to be 8 pixels down, x stays the same
				
				// update bounding boxes
				Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
				Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
				Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
				Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
				
				// check for collision
				for (int index = 1; index <= 5; index++) {
					if (BoundingBox.isCollision(Main.boundingBoxes.get(0), Main.boundingBoxes.get(index))) {
						Main.currentCoords.setY(Main.currentCoords.getY() - 8);                             // collision detected, undo the coordinate change
						Main.currentSprite.setCoords(Main.currentCoords.getX(),Main.currentCoords.getY());  // reset coords to before collision
						
						// undo the sprite index change
						if (Main.currentSpriteIndex != 0) {  
							--Main.currentSpriteIndex;
						}
						else {  // if the index is 0, -- would make it -1, so set it to the last index instead
							Main.currentSpriteIndex = 7;
						}
						
						Main.currentSprite = Main.downSprites.get(Main.currentSpriteIndex); // reset sprite to before collision
						
						// reset bounding boxes to before collision
						Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
						Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
						Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
						Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
					}
				}
		
				timer.resetWatch();
			}
			
			break;
			  
		case 'd':
			if (Main.isAnyDescriptionShown()) break;  // do not allow movement if an item is being inspected
			
			Main.direction = "right";
			if (timer.isTimeUp()) {
				++Main.currentSpriteIndex;
				if (Main.currentSpriteIndex > 7) {	// when goes past last element of the ArrayList is reached
					Main.currentSpriteIndex = 0;	// reset to the first element, which is the first frame
				}
				
				Main.currentSprite = Main.rightSprites.get(Main.currentSpriteIndex);	// get next frame
				Main.currentCoords.setX(Main.currentCoords.getX() + 8);
				Main.currentSprite.setCoords(Main.currentCoords.getX(),Main. currentCoords.getY());	// update x to be 8 pixels to the right, y stays the same
				
				// update bounding boxes
				Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
				Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
				Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
				Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
				
				// check for collision
				for (int index = 1; index <= 5; index++) {
					if (BoundingBox.isCollision(Main.boundingBoxes.get(0), Main.boundingBoxes.get(index))) {
						Main.currentCoords.setX(Main.currentCoords.getX() - 8);                             // collision detected, undo the coordinate change
						Main.currentSprite.setCoords(Main.currentCoords.getX(),Main.currentCoords.getY());  // reset coords to before collision
						
						// undo the sprite index change
						if (Main.currentSpriteIndex != 0) {  
							--Main.currentSpriteIndex;
						}
						else {  // if the index is 0, -- would make it -1, so set it to the last index instead
							Main.currentSpriteIndex = 7;
						}
						
						Main.currentSprite = Main.rightSprites.get(Main.currentSpriteIndex); // reset sprite to before collision
						
						// reset bounding boxes to before collision
						Main.boundingBoxes.get(0).setX1(Main.currentCoords.getX());        // LEFT - top left corner is the left bound, take its X
						Main.boundingBoxes.get(0).setY1(Main.currentCoords.getY());        // TOP - top left corner is the top bound, take its Y
						Main.boundingBoxes.get(0).setX2(Main.currentCoords.getX() + 128);  // RIGHT - sprite is 128px wide, so left bound + 128
						Main.boundingBoxes.get(0).setY2(Main.currentCoords.getY() + 128);  // BOTTOM - sprite is 128px tall, so top bound + 128
					}
				}
				
				timer.resetWatch();
			}
				
			break;
			
		case '$': // spacebar - inspect item
			for (int index = 1; index <= 1; index ++) {  // check for fridge and toaster
				if (BoundingBox.isInspectable(Main.boundingBoxes.get(0), Main.boundingBoxes.get(index), Main.direction)) {
					Main.isShowDescriptions.set(index, true);
				}
			}
			break;
			
		case 'b': 
			for ( int index = 1; index <= 2; index++) {
				if (Main.isShowDescriptions.get(index)) {
					Main.isShowDescriptions.set(index, false);
				}
			}
					
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}