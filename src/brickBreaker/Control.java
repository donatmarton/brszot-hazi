package brickBreaker;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Control {
	private GUI gui;
	private Network net = null;
	private boolean multiPlayer;
	private Ball ball;
	private Paddle paddle;
	private ArrayList<Block> blockList;
	private int level;
	private int score;
	private int life;
	private Direction dir;
	private ArrayList<Integer> allData;
	private Timer timer;

	
	// Field - konstans?
	private int xMax = 20;
	private int xMin = 0;
	private int yMax = 20;
	private int yMin = 0;
		
	
	
	Control() {
	}

	void setGUI(GUI g) {
		gui = g;
	}
	
	public void setDirection(Direction direction){
		this.dir = direction;
		
	}

	public void init(int setLevel, boolean mode){
		multiPlayer = mode;
		level = setLevel;
		life = 3;
		score = 0;
		
		ball = new Ball();
		paddle = new Paddle();
		blockList = new ArrayList<Block>();
		
		
		int i = 0;
		for (int x = 0; x < 5; x++){
			for (int y = 0; y < 3; y++){
				Point position = new Point(x*125+85,y*125+85);
				blockList.add(i, new Block(position,1,false));
				System.out.println("tegla:" + position);
				i++;
			}
		}
	}
	
	public boolean decreaseLife(){
		life--;
		
		//end game
		if (life <= 0){
			System.out.println("end");
			ball.setVelx(0);
			ball.setVely(0);
			return true;
		}
		return false;
			
	}
	
	public boolean collisionDetection(){
		
		//leesett
		if (ball.getMinY() <= yMin){
			System.out.println("leesett");
			ball.setVely(-ball.getVel().y);
//			if (decreaseLife()){
//				return true;
//			}
		}
		
		//fuggolegesfal
		
		if (ball.getMinX() <= xMin || ball.getMaxX() >= xMax){
			ball.setVelx(-ball.getVel().x);
			System.out.println("jobb/bal");
		}
		
		
		//plafon
		
		if (ball.getMaxY() >= yMax){
			ball.setVely(-ball.getVel().y);
			System.out.println("fent");
		}
		
		//tegla
		
		for (int k = 0; k < blockList.size(); k++){
			if (ball.intersects(blockList.get(k))){

				if (ball.getMaxX() > blockList.get(k).getMinX() && ball.getMinX() <  blockList.get(k).getMaxX()){
					ball.setVely(-ball.getVel().y);
					System.out.println(ball.getVel());
					
				}
				else{
					ball.setVelx(-ball.getVel().x);
					System.out.println(ball.getVel());
					
				}
				if (blockList.get(k).decreaseBlockLife() <= 0){
					blockList.remove(k);
					System.out.println("torol tegla" + k);
				}
				score++;
				return true;
			}
		}
		
		
		//uto
		
		if (ball.intersects(paddle)){
			ball.setVely(-ball.getVel().y); // csusztatas!!
			ball.setVelx(ball.getVel().x + 1);
			System.out.println("uto");
		}
		
		return false;
	}
	
	
	public void playGame(){
		init(1, false);	
		timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
        
	}
	
	
	public ArrayList<Integer> generateAllData(){
		 allData = new ArrayList<Integer>();
		 allData.add((int)ball.x);
		 allData.add((int)ball.y);
		 allData.add((int)paddle.x);
		 allData.add((int)paddle.y);
		 for(int k = 0; k < blockList.size(); k++){
			 allData.add(blockList.get(k).getBlockLife());
			 allData.add(blockList.get(k).x);
			 allData.add(blockList.get(k).y);	 			 
		 }
		 return allData;
		
	}

	private class ScheduleTask extends TimerTask {

	    @Override
	    public void run() {

	        ball.refresh();
	        paddle.refresh(dir);
	        collisionDetection();
	        generateAllData();
	        if (multiPlayer){
	        	sendAllData(allData);
	        	allDataReceived(allData);
	        }
	        System.out.print(ball.getPos());
			System.out.println(ball.getVel());
	    }
	}
	 
	void startServer() {
		if (net != null)
			net.disconnect();
		net = new SerialServer(this);
		net.connect("localhost");
	}
	
	void startClient() {
		if (net != null)
			net.disconnect();
		net = new SerialClient(this);
		net.connect("localhost");
	}
	
	void sendAllData(ArrayList<Integer> allData2) {
		// gui.addPoint(p); //for drawing locally
		if (net == null)
			return;
		//net.send(p);
	}

	void allDataReceived(ArrayList<Integer> allData2) {
		if (gui == null)
			return;
		//gui.addPoint(p);
	}

	public void clickReceived(Point received) {
		// TODO Auto-generated method stub
		
	}

	public void sendClick(Point point) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Integer> getData(){
		return allData;
	}

}