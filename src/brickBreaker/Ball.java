package brickBreaker;

/**
 * @author Marci
 *
 */

import java.awt.Point;
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
	
	private Point pos;
	private Point2D.Double vel;
	
	Ball(){
		//new Ellipse2D.Double(10,10,2,2);
		this.height = 2;
		this.width = 2;
		this.x = 500;
		this.y = 500;
		this.vel = new Point2D.Double(0,-1);
	}
	
/*	public Ball() {
		this.pos = new Point(0,0);
		this.vel = new Point(0,0);
	}
	
	public Ball(Point pos, Point vel) {
		this.pos = pos;
		this.vel = vel;
	}
	
	public Ball(
			Point pos, 
			Point vel,
			int MOVESTEP,
			int BOUNDARY_L,
			int BOUNDARY_R,
			int BOUNDARY_T,
			int BOUNDARY_B
			) {
		this.pos = pos;
		this.vel = vel;
		Ball.setMOVESTEP(MOVESTEP);
		Ball.setBOUNDARY_L(BOUNDARY_L);
		Ball.setBOUNDARY_R(BOUNDARY_R);
		Ball.setBOUNDARY_T(BOUNDARY_T);
		Ball.setBOUNDARY_B(BOUNDARY_B);
	}
*/	
	/**
	 * refresh the ball midpoint position to its next value based on
	 * velocity vector and current position
	 * Midpoint will never go left of BOUNDARY_L, nor right of BOUNDARY_R,
	 * similarly over BOUNDARY_T nor below BOUNDARY_B 
	 * @return true if successfully moved, false if unable to be moved
	 */
/*	public boolean refresh() {
		if (
			(pos.x + vel.x > BOUNDARY_R) || (pos.x + vel.x < BOUNDARY_L) ||
			(pos.y + vel.y > BOUNDARY_T) || (pos.y + vel.y < BOUNDARY_B) )
			return false;
		else {
			pos.x += vel.x;
			pos.y += vel.y;
			return true;
		}
	}
*/
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
		//return pos;
		return new Point2D.Double(this.x, this.y);
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**
	 * @return the vel
	 */
/*	public Point getVel() {
		return vel;
	}
*/
	/**
	 * @param vel the vel to set
	 */
/*	public void setVel(Point vel) {
		this.vel = vel;
	}
*/
	
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