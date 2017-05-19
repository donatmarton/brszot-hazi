package brickBreaker;

/**
 * @author Marci
 *
 */

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

class Paddle extends Rectangle2D.Double{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static double MOVESTEP = 3;
	private static int BOUNDARY_L = 0;
	private static int BOUNDARY_R = 600;
	
	private Point pos;
	private PlayField field; 
	private GUI gui;
	
	public Paddle() {
		this.pos = new Point(0,0);
		this.setRect(300,635,100,10);
	}
	public Paddle(int level) {
		this.pos = new Point(0,0);
		switch(level){
		case 1:{
			this.setRect(300,635,100,10);
			break;
		}
		case 2:{
			this.setRect(300,635,80,10);
			break;
		}
		case 3:{
			this.setRect(300,635,60,10);
			break;
		}
		}
		
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
	
	public void setGUI(GUI g){
		gui = g;
	}

	/**
	 * refresh the paddle midpoint position to its next value based on
	 * dir and internal MOVESTEP member
	 * Midpoint will never go left of BOUNDARY_L, nor right of BOUNDARY_R 
	 * @return true if successfully moved, false if unable to be moved
	 */
	public boolean refresh() {
		if (gui.getRight()) {
			if (this.x + MOVESTEP + 50 > BOUNDARY_R)
				return false;
			else {
				this.x += MOVESTEP;
				return true;
			}
		}
		else if(gui.getLeft())  {
			if (this.x - MOVESTEP - 50 < BOUNDARY_L)
				return false;
			else {
				this.x -= MOVESTEP;
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
	public static double getMOVESTEP() {
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