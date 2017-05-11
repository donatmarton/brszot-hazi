package brickBreaker;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


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
	private int xMax = 655;
	private int xMin = 40;
	private int yMax = 680;
	private int yMin = 85;
		
	
	
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
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 5; y++){
				int blockLife = ThreadLocalRandom.current().nextInt(0, 4);
				Point position = new Point(x*60+55,y*50+105);
				blockList.add(i, new Block(position,blockLife,false));
				System.out.println("tegla:" + position);
				i++;
			}
		}
		for (int k = 0; k < blockList.size(); k++){
			if (blockList.get(k).decreaseBlockLife() <= 0){
				blockList.remove(k);
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
			//ball.setVelx(ball.getVel().x + 1);
			System.out.println("uto");
		}
		
		return false;
	}
	
	
	public void playGame(){
		init(1, false);	
		timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1);
        
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
	        System.out.println(ball.x);
	        System.out.println(ball.y);
	        //paddle.refresh(dir);
	        collisionDetection();
	        generateAllData();
	        if (multiPlayer){
	        	sendAllData(allData);
	        	allDataReceived(allData);
	        }
	        gui.drawScreen(allData);
	        System.out.println(ball.getPos());
			System.out.println(ball.getVel());
	    }
	}
	 
	void startServer(String ip) {
		if (net != null)
			net.disconnect();
		net = new SerialServer(this);
		net.connect(ip);
	}
	
	void startClient(String ip) {
		if (net != null)
			net.disconnect();
		net = new SerialClient(this);
		net.connect(ip);
	}
	
	void sendAllData(ArrayList<Integer> allData2) {
		if (net == null)
			return;
		net.send(allData2);
	}

	void allDataReceived(ArrayList<Integer> allData2) {
		if (gui == null)
			return;
		//gui.addPoint(p); // NMD: todo call processing function here
	}
/* MARKED FOR DELETION - NMD
	public void clickReceived(Point received) {
		// TODO Auto-generated method stub
		
	}

	public void sendClick(Point point) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Integer> getData(){
		return allData;
	}
*/
}