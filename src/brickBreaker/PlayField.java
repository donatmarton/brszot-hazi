package brickBreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayField extends JPanel /*implements KeyListener*/{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage buffer;
	 
	private Ball ball;
	private Paddle Paddle;
	private Block Block;
	boolean gameOver = false;
	private boolean Paddle_left;
	private boolean Paddle_right;
	
	public PlayField(){
		setIgnoreRepaint(true);
		addKeyListener((KeyListener) this);
		setFocusable(true);
	}
	
	

	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
			Paddle_left = true;
		if(key == KeyEvent.VK_RIGHT)
			Paddle_right = true;
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
			Paddle_left = false;
		if (key == KeyEvent.VK_RIGHT)
			Paddle_right = false;
	}

	public boolean getLeft(){
		return Paddle_left;
	}
	public boolean getRight(){
		return Paddle_right;
	}
	
	}
