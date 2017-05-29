package brickBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

class MyPanel extends JPanel { 

}
public class GUI extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Control ctrl;
	private int mode;
	boolean gameOver = false;
	private DrawPanel drawPanel;
	private int player;
	
	private boolean Paddle_left;
	private boolean Paddle_right;
	private boolean Pause;
	private boolean Toplist;

	
	String[][] list= new String[6][3];
	String[] columnNames={"First name","Last name","Score"};
	JTable table = new JTable(list,columnNames);
	BufferedImage buffer;
	
	GUI(Control c){
		super("brickBreaker");
		ctrl = c;
		setSize(1500, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
			
		addKeyListener((KeyListener) this);
	
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Start");
		JPanel inputPanel = new JPanel();

		JMenuItem menuItem = new JMenuItem("Single player mode");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   player=1;
			   ctrl.setMultiPlayer(false);
			   try {
				DataIn();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   draw_levels();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Double player mode");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player=2;
				mode = 2;
				ctrl.setMultiPlayer(true);
				draw_connect();
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
			    frame2.setSize(1500,800);
			    
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
				try {
					DataIn();
					loadScores();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
//		inputPanel.setBounds(30, 30, 620, 600);
//		inputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		if(player == 2){
//			inputPanel.setBounds(650, 30, 1240, 600);
//			inputPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		}
		inputPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("X:" + e.getX() + " Y:" + e.getY());
				//ctrl.sendClick(new Point(e.getX(), e.getY()));
			}
		});
	
		add(inputPanel);
		setVisible(true);
		}

	void draw_levels() {
		setSize(1500, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JMenuBar menuBar1 = new JMenuBar();
		
		JMenuItem menuItem1 = new JMenuItem("Easy");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			   mode=1;
			   ctrl.playGame();
				ArrayList<Integer> uh = null;
				uh=ctrl.generateAllData();
				System.out.println("tea:" + uh.get(4));
			}
		});
		menuBar1.add(menuItem1);

		menuItem1 = new JMenuItem("Medium");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode=2;
				ctrl.playGame();
				ArrayList<Integer> uh = null;
				uh=ctrl.generateAllData();
				System.out.println("tea:" + uh.get(4));
			}
		});
		menuBar1.add(menuItem1);

		menuItem1 = new JMenuItem("Hard");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode=3;
				ctrl.playGame();
				ArrayList<Integer> uh = null;
				uh=ctrl.generateAllData();
				System.out.println("tea:" + uh.get(4));
			}
			});
		menuBar1.add(menuItem1);
		
		setJMenuBar(menuBar1);
		setVisible(true);
		}
	
	void draw_connect() {
		setSize(1500, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JMenuBar menuBar1 = new JMenuBar();
		
		JMenuItem menuItem1 = new JMenuItem("Server");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startServer("localhost");
			}
		});
		menuBar1.add(menuItem1);

		menuItem1 = new JMenuItem("Client");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startClient(JOptionPane.showInputDialog("IP:"));
			}
		});
		menuBar1.add(menuItem1);
		
		setJMenuBar(menuBar1);
		setVisible(true);
		}

	
	public void loadScores() throws IOException{
		JFrame frame2 = new JFrame("Top List");
	    frame2.setVisible(true);
	    frame2.setSize(400,400);
	    
		JLabel indexEntry = new JLabel();

		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(100, 300, 300, 400);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    table.setFillsViewportHeight(true);

		JPanel panel = new JPanel();
		frame2.add(panel);
		panel.add(table);
		panel.add(indexEntry);
	    setVisible(true);
	    }

	public void DataIn() throws IOException{
		BufferedReader in;
		
		in = new BufferedReader(new FileReader("score.txt"));
		list[0][0]="Name";
		list[0][1]="Score";
		for(int j=1;j<6;j++){
			list[j][0]= in.readLine();
			list[j][1]=in.readLine();
		}
	}
	
	public void DataOut() throws IOException{
		BufferedWriter out;
		
		out = new BufferedWriter(new FileWriter("score.txt"));
		for(int i=1;i<6;i++){
			for(int j=0;j<2;j++){
			out.write(list[i][j]+"");
			out.newLine();
			out.flush();
			}
		}
		//out.close();
	}
	public void SetToplist(int i) throws IOException{
		JFrame frame2 = new JFrame("Top List Scores");
		String myName = (String) JOptionPane.showInputDialog(frame2,"Name:\n","Enter your name", JOptionPane.PLAIN_MESSAGE,null,null,"Name");
		list[i][0]=myName;
		DataOut();
		loadScores();		
	}
	
	public String[][] getScore(){
		return list;
	}

	private class DrawPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		protected void paintComponent(Graphics g) {
			Toplist =false;
			ArrayList<Integer> Data = ctrl.getData();
			super.paintComponent(g);
			if(ctrl.getEnd()){
				ctrl.setEnd(false);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
				g.setColor(Color.BLACK);
				g.drawString("GameOver", 200, 300);
				g.dispose();
				
				int index=0;
				String helper1 = null;
				String helper2 = null;
				
				for(int i=1;i<list.length;i++){
					int k = Integer.parseInt(list[i][1]);
					if(ctrl.getScore() >= k){
						String strI = Integer.toString(ctrl.getScore());
						index=i;
						helper1=list[i][0];
						helper2=list[i][1];
						list[i][1]=strI;
						Toplist=true;
						break;
					}
				}
				if(Toplist == true){
					for(int m=5; m>index+1;m--){
						list[m][0]= list[m-1][0];
						list[m][1]= list[m-1][1];
					}
					if (index < 5){
						list[index+1][0]=helper1;
						list[index+1][1]=helper2;
					}
					try {
						SetToplist(index);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ctrl.setEnd(false);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
				g.setColor(Color.BLACK);
				g.drawString("GameOver", 200, 300);
				g.dispose();
			}
		
		//repaint();
		//inputPanel.setBounds(30, 30, 620, 600);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.drawString("Life:", 100, 80);
		g.drawString(ctrl.drawLife(), 160, 80);
		g.drawString("Scores:", 250, 80);
		g.drawString(ctrl.drawScore(), 350, 80);
		g.drawRect(40, 85, 615, 580);
		
		//Ball
		int Ball_pos_x = (int) Data.get(2);
		int Ball_pos_y = (int) Data.get(3);
		g.setColor(Color.BLACK);
		g.fillOval(Ball_pos_x, Ball_pos_y, 50, 50);
		 
		//Paddle
		int Paddle_pos_x = (int) Data.get(4);
		int Paddle_pos_y = (int) Data.get(5);
		g.setColor(Color.MAGENTA);
		g.fillRect(Paddle_pos_x, Paddle_pos_y, 100,10); 
		 	 
		//Blocks
		int i=6;
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
		
		if(player == 2){
			ArrayList<Integer> Data2= ctrl.getData2();
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.setColor(Color.BLACK);
			g.drawString("Life:", 750, 80);
			g.drawString(Integer.toString(Data2.get(0)), 810, 80);
			g.drawString("Scores:", 900, 80);
			g.drawString(Integer.toString(Data2.get(1)), 1000, 80);
			g.drawRect(690, 85, 615, 580);
			
			//Ball
			int Ball_pos_x2 = (int)  Data2.get(2);
			int Ball_pos_y2 = (int)  Data2.get(3);
			g.setColor(Color.BLACK);
			g.fillOval(Ball_pos_x2+650, Ball_pos_y2, 50, 50);
			 
			//Paddle
			int Paddle_pos_x2 = (int) Data2.get(4);
			int Paddle_pos_y2 = (int) Data2.get(5);
			g.setColor(Color.MAGENTA);
			g.fillRect(Paddle_pos_x2+650, Paddle_pos_y2, 100,10); 
			 	 
			//Blocks
			int n=6;
			while( n<Data2.size()){
				if((int)Data2.get(n)!=0){
					if((int)Data2.get(n)==1){
						g.setColor(Color.BLUE);
						}
					if((int)Data2.get(n)==2){
						g.setColor(Color.GREEN);
						}
					if((int)Data2.get(n)==3){
						g.setColor(Color.RED);
						}
					g.fillRect((int)Data2.get(n+1)+650, (int)Data2.get(n+2),50, 30);
				}
				n=n+3;
			}
			}
		//Toolkit.getDefaultToolkit().sync();
		//g.dispose();
	//	repaint();
		
			}
		}

	public void drawScreen(){
		drawPanel = new DrawPanel();
		drawPanel.setBounds(0,0, 1680, 1680);
		//drawPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		add(drawPanel);
		setVisible(true);	
		drawPanel.repaint();
	}
	
	public void keyPressed(KeyEvent e){
		
		int key= e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){

			Paddle_left = true;
			System.out.println("balra");
		}
		if(key == KeyEvent.VK_RIGHT){
			Paddle_right = true;
			System.out.println("jobbra");
		}
		if(key == KeyEvent.VK_SPACE){
			Pause = true;
			if (player == 1){
				System.out.println("szünet");
				if(ctrl.isPaused()){
					ctrl.resume();				
				}
				else{
					ctrl.pause();
				}
				ctrl.negatePaused();
			}
		}		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
			Paddle_left = false;
		if (key == KeyEvent.VK_RIGHT)
			Paddle_right = false;
		if(key == KeyEvent.VK_SPACE)
			Pause = false;
	}

	public boolean getLeft(){
		return Paddle_left;
	}
	
	public boolean getRight(){
		return Paddle_right;
	}
	
	public boolean Pause(){
		return Pause;
	}
	
	public int getLevel(){
		return mode;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}