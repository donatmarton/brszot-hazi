package brickBreaker;

/**
 * @author Marci
 *
 */

import java.awt.Point;
import java.awt.Rectangle;

class Paddle extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static int MOVESTEP = 1;
	private static int BOUNDARY_L = 0;
	private static int BOUNDARY_R = 100;
	
	private Point pos;
	private PlayField field; 
	
	public Paddle() {
		this.pos = new Point(0,0);
		this.setRect(300, 635, 100, 3);
	}
	
	public Paddle(Point pos) {
		this.pos = pos;
	}
	
	Paddle(double x, double y, double w, double h) {
		this.setRect(x, y, w, h);
	}
	
	public Paddle(
			Point pos,
			int MOVESTEP,
			int BOUNDARY_L,
			int BOUNDARY_R
			) {
		this.pos = pos;
		Paddle.setMOVESTEP(MOVESTEP);
		Paddle.setBOUNDARY_L(BOUNDARY_L);
		Paddle.setBOUNDARY_R(BOUNDARY_R);
	}

	/**
	 * refresh the paddle midpoint position to its next value based on
	 * dir and internal MOVESTEP member
	 * Midpoint will never go left of BOUNDARY_L, nor right of BOUNDARY_R 
	 * @return true if successfully moved, false if unable to be moved
	 */
	public boolean refresh(Direction dir) {
		if (field.getRight()) {
			if (this.x + MOVESTEP > BOUNDARY_R)
				return false;
			else {
				this.x += MOVESTEP;
				return true;
			}
		}
		else if(field.getLeft())  {
			if (pos.x - MOVESTEP < BOUNDARY_L)
				return false;
			else {
				pos.x -= MOVESTEP;
				return true;
			}
		}
		return false;
	}
	
/*	public boolean refresh(Direction dir) {
		if (dir == Direction.RIGHT) {
			if (pos.x + MOVESTEP > BOUNDARY_R)
				return false;
			else {
				pos.x += MOVESTEP;
				return true;
			}
		}
		else {
			if (pos.x - MOVESTEP < BOUNDARY_L)
				return false;
			else {
				pos.x -= MOVESTEP;
				return true;
			}
		}
	}*/
	
	/**
	 * @return the pos
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**
	 * @return the mOVESTEP
	 */
	public static int getMOVESTEP() {
		return MOVESTEP;
	}

	/**
	 * @param mOVESTEP the mOVESTEP to set
	 */
	public static void setMOVESTEP(int mOVESTEP) {
		MOVESTEP = mOVESTEP;
	}

	/**
	 * @return the bOUNDARY_L
	 */
	public static int getBOUNDARY_L() {
		return BOUNDARY_L;
	}

	/**
	 * @param bOUNDARY_L the bOUNDARY_L to set
	 */
	public static void setBOUNDARY_L(int bOUNDARY_L) {
		BOUNDARY_L = bOUNDARY_L;
	}

	/**
	 * @return the bOUNDARY_R
	 */
	public static int getBOUNDARY_R() {
		return BOUNDARY_R;
	}

	/**
	 * @param bOUNDARY_R the bOUNDARY_R to set
	 */
	public static void setBOUNDARY_R(int bOUNDARY_R) {
		BOUNDARY_R = bOUNDARY_R;
	}
	
}