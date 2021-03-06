package brickBreaker;

/**
 * @author Marci
 *
 */

import java.awt.Point;
import java.awt.Rectangle;

class Block extends Rectangle { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static Point DEFAULTSIZE = new Point(5,5);
	
	private final boolean special;
	private int blockLife;
	private Point pos;
	
	public Block(boolean special) {
		this.special = special;
		this.pos = new Point(0,0);
		this.blockLife = 1;
	}
	
	public Block(
			boolean special, 
			int blockLife,
			Point size,
			Point pos
			) {
		this.special 	= special;
		this.blockLife 	= blockLife;
		this.pos = pos;
	}
	
	
	Block(Point pos, int life, boolean spec){
		this.x = pos.x;
		this.y = pos.y;
		setBlockLife(life);
		this.width = 50;
		this.height = 30;
		special = spec;
	}
	

	public int decreaseBlockLife(){
		this.blockLife--;
		return blockLife;
	}
	
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
	 * @return the blockLife
	 */
	public int getBlockLife() {
		return blockLife;
	}

	/**
	 * @param blockLife the blockLife to set
	 */
	public void setBlockLife(int blockLife) {
		this.blockLife = blockLife;
	}


	/**
	 * @return the special
	 */
	public boolean isSpecial() {
		return special;
	}

	/**
	 * @return the dEFAULTSIZE
	 */
	public static Point getDEFAULTSIZE() {
		return DEFAULTSIZE;
	}

	/**
	 * @param dEFAULTSIZE the dEFAULTSIZE to set
	 */
	public static void setDEFAULTSIZE(Point dEFAULTSIZE) {
		DEFAULTSIZE = dEFAULTSIZE;
	}
	

}