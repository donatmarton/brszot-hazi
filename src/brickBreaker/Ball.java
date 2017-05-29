package brickBreaker;

/**
 * @author Marci
 *
 */

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

class Ball extends Ellipse2D.Double{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static int MOVESTEP = 1;
	private static int BOUNDARY_L = 0;
	private static int BOUNDARY_R = 100;
	private static int BOUNDARY_T = 100;
	private static int BOUNDARY_B = 0;
	
	private Point2D.Double vel;
	
	Ball(){
		this.height = 50;
		this.width = 50;
		this.x = 305;
		this.y = 360;
		this.vel = new Point2D.Double(0,2);
	}
	
	Ball(int level){
		this.height = 50;
		this.width = 50;
		this.x = 300;
		this.y = 300;
		switch(level){
		case 1: {
			this.vel = new Point2D.Double(0,2);
			break;
		}
		case 2: {
			this.vel = new Point2D.Double(0,3);
			break;
		}
		case 3: {
			this.vel = new Point2D.Double(0,4);
			break;
		}
		}
	}
	

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

	/**
	 * @return the bOUNDARY_T
	 */
	public static int getBOUNDARY_T() {
		return BOUNDARY_T;
	}

	/**
	 * @param bOUNDARY_T the bOUNDARY_T to set
	 */
	public static void setBOUNDARY_T(int bOUNDARY_T) {
		BOUNDARY_T = bOUNDARY_T;
	}

	/**
	 * @return the bOUNDARY_B
	 */
	public static int getBOUNDARY_B() {
		return BOUNDARY_B;
	}

	/**
	 * @param bOUNDARY_B the bOUNDARY_B to set
	 */
	public static void setBOUNDARY_B(int bOUNDARY_B) {
		BOUNDARY_B = bOUNDARY_B;
	}

	/**
	 * @return the pos
	 */
	public Point2D.Double getPos() {
		return new Point2D.Double(this.x, this.y);
	}


	public void refresh() {
		this.x = this.x +  this.vel.x;
		this.y = this.y +  this.vel.y;
	}

	public void setPos(Point2D.Double pos) {
		this.x = pos.x;
		this.y = pos.y;
	}

	public Point2D.Double getVel() {
		return vel;
	}

	public void setVelx(double velx) {
		this.vel.x = velx;
	}
	
	public void setVely(double vely) {
		this.vel.y = vely;
	}
}