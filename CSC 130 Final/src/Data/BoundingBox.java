// bounding box for each sprite

package Data;

public class BoundingBox {
	// Fields
	private int x1 = -128;	// left
	private int y1 = -128;  // top
	private int x2 = -128;  // right
	private int y2 = -128;  // bottom

	// Constructor
	public BoundingBox(int left, int top, int right, int bottom) {
		setX1(left);
		setY1(top);
		setX2(right);
		setY2(bottom);
	}
	
	// get methods
	public int getX1() {
		return x1;
	}
	public int getY1() {
		return y1;
	}
	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}

	// set methods
	public void setX1(int left) {
		x1 = left;
	}
	public void setY1(int top) {
		y1 = top;
	}
	public void setX2(int right) {
		x2 = right;
	}
	public void setY2(int bottom) {
		y2 = bottom;
	}
	
	// to check if 2 bounding boxes collide
	public static boolean isCollision(BoundingBox box1, BoundingBox box2) {
		boolean isCollision = true;
		
		// if any are true, there is no collision
		if (box1.x1 > box2.x2) {
			isCollision = false;  
		} 	
		else if (box1.y1 > box2.y2) {
			isCollision = false;  
		} 			
		else if (box1.x2 < box2.x1) {
			isCollision = false; 
		}
		else if (box1.y2 < box2.y1) {
			isCollision = false;  
		}
		
		return isCollision;
	}
	
	// to check if soket can inspect an item
	public static boolean isInspectable(BoundingBox box1, BoundingBox box2, String direction) {
		boolean isInspectable = false;
		
		// if facing the direction of a "would be" collision, the item is inspectable
		switch (direction) {
		case "up":
			if (isCollision(new BoundingBox(box1.x1, (box1.y1 - 8), box1.x2, box1.y2), box2)) {
				if ((box1.y1 - 8) < box2.y2) {  
					isInspectable = true;
				}
			}
			break;
			
		case "left":
			if (isCollision(new BoundingBox((box1.x1 - 8), box1.y1, box1.x2, box1.y2), box2)) {
				if ((box1.x1 - 8) < box2.x2) {
					isInspectable = true;
				}
			}
			break;
			
		case "down":
			if (isCollision(new BoundingBox(box1.x1, box1.y1, box1.x2, (box1.y2 +8)), box2)) {
				if ((box1.y2 + 8) > box2.y1) {
					isInspectable = true;
				}
			}
			break;
				
		case "right":
			if (isCollision(new BoundingBox(box1.x1, box1.y1, (box1.x2 + 8), box1.y2), box2)) {
				if ((box1.x2 + 8) > box2.x1) {
					isInspectable = true;
				}	
			}	
			break;
		}

		return isInspectable;
	}
	
	
	
}