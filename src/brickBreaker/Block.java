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
	private Point size;
	private Point pos;
	
	public Block(boolean special) {
		this.special = special;
		this.size = Block.getDEFAULTSIZE();
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
		this.size = size;
		this.pos = pos;
	}
	
	
	Block(Point pos, int life, boolean spec){
		this.x = pos.x;
		this.y = pos.y;
		setBlockLife(life);
		this.width = 5;
		this.height = 2;
		special = spec;
	}
	
	/**
	 * decreases block life by one, if life above 0, else does nothing
	 * @return true if successfully decreased, otherwise false
	 */
/*	public boolean decreaseBlockLife() {
		if ( this.getBlockLife() > 0 ) {
			setBlockLife(getBlockLife() - 1);
			return true;
		}
		else
			return false;
	}
*/
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
	 * @return the size
	 */
/*	public Point getSize() {
		return size;
	}
*/
	/**
	 * @param size the size to set
	 */
	public void setSize(Point size) {
		this.size = size;
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
