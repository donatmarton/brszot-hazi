package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.datatype.DatatypeConstants.Field;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
class MyPanel extends JPanel { 

}
public class GUI extends JFrame{

	private Control ctrl;
	private Leaderboards board;
	private int button;
	private PlayField field;
	private String s;
	private String [] intArray = new String[10000];
	private int numOfInts = 0;
	boolean gameOver = false;
	private Paddle Paddle;
	private Block Block;
	private DrawPanel drawPanel;
	  
	BufferedImage buffer;
	
	GUI(Control c){
		super("brickBreaker");
		ctrl = c;
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
	
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Start");
		JPanel inputPanel = new JPanel();

		JMenuItem menuItem = new JMenuItem("Single player mode");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    //draw_levels();
				//ctrl.init(1, false);
				c.playGame();
				ArrayList<Integer> uh = null;
				uh=ctrl.generateAllData();
				System.out.println("tea:" + uh.get(4));
				//drawScreen(uh);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Double player mode");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ide jön majd !!
			}
		});
		menu.add(menuItem);	
		menuBar.add(menu);

		menuItem = new JMenuItem("Help");
		menuItem.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame2 = new JFrame("Help");
			    frame2.setVisible(true);
			    frame2.setSize(400,400);
			    
				JLabel indexEntry = new JLabel();
				String content;
				try {
					content = new Scanner(new File("help.txt")).useDelimiter("\\Z").next();
					indexEntry.setText(content);
					setVisible(true);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 JPanel panel = new JPanel();
				 frame2.add(panel);
				 panel.add(indexEntry);
			    setVisible(true);
			    }
			});
			
		menuBar.add(menuItem);
		menuItem = new JMenuItem("TopScore");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//board.getInstance();
				loadScores();
			}
		});
		
		menuBar.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(menuItem);

		setJMenuBar(menuBar);
		
		inputPanel.setBounds(30, 30, 620, 600);
		inputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		inputPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("X:" + e.getX() + " Y:" + e.getY());
				ctrl.sendClick(new Point(e.getX(), e.getY()));
			}
		});
		add(inputPanel);
		setVisible(true);
		
		drawPanel = new DrawPanel();
		drawPanel.setBounds(230,30,200,200);
		drawPanel.setBorder(BorderFactory.createTitledBorder("Draw"));
		add(drawPanel);
		setVisible(true);
	}

void draw_levels() {
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JMenuBar menuBar1 = new JMenuBar();
		
		JMenuItem menuItem1 = new JMenuItem("Easy");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   s="Easy";
			}
		});
		menuBar1.add(menuItem1);

		menuItem1 = new JMenuItem("Medium");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s="Medium";
			}
		});

		menuBar1.add(menuItem1);

		menuItem1 = new JMenuItem("Hard");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s="Hard";
			}
			});
		menuBar1.add(menuItem1);
		setJMenuBar(menuBar1);
		setVisible(true);
	}

	public void loadScores(){
		JFrame frame2 = new JFrame("Top List");
	    frame2.setVisible(true);
	    frame2.setSize(400,400);
	    
		JLabel indexEntry = new JLabel();
		String content;
		try {
			content = new Scanner(new File("score.txt")).useDelimiter("\\Z").next();
			indexEntry.setText(content);
			setVisible(true);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 JPanel panel = new JPanel();
		 frame2.add(panel);
		 panel.add(indexEntry);
	    setVisible(true);
	    }
	
	void addPoint(Point p) {
		drawPanel.points.add(p);
		drawPanel.repaint();
	}

	private class DrawPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private ArrayList<Point> points = new ArrayList<Point>();

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (Point p : points) {
				g.drawOval(p.x, p.y, 10, 10);
			}
		}
	}

	public void drawScreen(ArrayList<Integer> Data){
		System.out.println("tsdasadscadd:" + Data.get(0) + Data.get(1));
		Graphics2D g = (Graphics2D)this.getGraphics();

		if(gameOver){
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", (buffer.getWidth()/2), buffer.getHeight()/2);
			g.dispose();
		}
		
		repaint();
		
		//Ball
		 int Ball_pos_x = (int) Data.get(0);
		 int Ball_pos_y = (int) Data.get(1);
		 g.setColor(Color.BLACK);
		 g.fillOval(Ball_pos_x, Ball_pos_y, 50, 50);
		 
		//Paddle
		 int Paddle_pos_x = (int) Data.get(2);
		 int Paddle_pos_y = (int) Data.get(3);
		 g.setColor(Color.WHITE);
		 g.fillRect(Paddle_pos_x, Paddle_pos_y, 100,3);
		
		 
		 	 
		//Blocks
		int i=4;
		while( i<Data.size()){
		if((int)Data.get(i)!=0){
			if((int)Data.get(i)==1){
			g.setColor(Color.BLUE);
			}
			if((int)Data.get(i)==2){
			g.setColor(Color.GREEN);
			}
			if((int)Data.get(i)==3){
			g.setColor(Color.RED);
			}
			g.fillRect((int)Data.get(i+1), (int)Data.get(i+2),50, 30);
		}
		i=i+3;
		}
		Toolkit.getDefaultToolkit().sync();
		
		//g.dispose();
		//repaint();
		
	}
	}