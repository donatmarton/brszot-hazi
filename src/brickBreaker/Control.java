package brickBreaker;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


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
	private ArrayList<Integer> allData2 = null;
	private Timer timer;
	private Block block;
	private boolean play = false;
	private int type = 0;
	private boolean end = false;
	private boolean win = false;

	
	// Field - konstans?
	private int xMax = 655;
	private int xMin = 40;
	private int yMax = 635;
	private int yMin = 85;
	private boolean isPaused = false;
	
	private int maxScore = 30;
		
	
	
	Control() {
	}

	void setGUI(GUI g) {
		gui = g;
	}
	
	public void setDirection(Direction direction){
		this.dir = direction;
		
	}

	public void init(int setLevel){
		level = setLevel;
		allData2 = null;
		this.play = true;
		this.win = false;
		
		ball = new Ball(level);
		paddle = new Paddle();
		paddle.setGUI(this.gui);
		blockList = new ArrayList<Block>();
		
		// ha kétjátékos módban kliensként lép be, a másikkal egyezo pálya legyen
		if (multiPlayer && type == 2){
			while(allData2 == null){
				System.out.println("kilens vagyok");
			}
			int i=6;
			while(i < allData2.size()){
				System.out.println(allData2.get(i+1));
				block = new Block(new Point(allData2.get(i+1) , allData2.get(i+2)) , allData2.get(i), false);
				blockList.add(block);
				i += 3;
			}
		}
		// egyébként generál pályát
		else{
			int i = 0;
			for (int x = 0; x < 10; x++){
				for (int y = 0; y < 5; y++){
					int blockLife = ThreadLocalRandom.current().nextInt(0, 4);
					Point position = new Point(x*60+55,y*50+105);
					blockList.add(i, new Block(position,blockLife,false));
					System.out.println("tegla:" + position + blockLife);
					i++;
				}
			}
			for (int k = 0; k < blockList.size(); k++){
				if (blockList.get(k).getBlockLife() <= 0){
					blockList.remove(k);
					--k;
				}
			}
			// a generált pályát két játékos estén elküldi
			if(multiPlayer){
				generateAllData();
				sendAllData(this.allData);
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
		if (ball.getCenterY() >= yMax){
			System.out.println("leesett");
			if (decreaseLife()){
				this.play = false;
				this.end = true;
				return true;
			}
			else{
				ball = new Ball(level);
				paddle.x = 300;
				paddle.y = 635;
			}
		}
		
		//fuggolegesfal
		if (ball.getMinX() <= xMin || ball.getMaxX() >= xMax){
			ball.setVelx(-ball.getVel().x);
			for (int steps=0; steps< 10 && (ball.getMinX() <= xMin || ball.getMaxX() >= xMax); ++steps) {
				ball.x += ball.getVel().x;
				ball.y += ball.getVel().y;
				
			}
			System.out.println("jobb/bal");
		}
		
		
		//plafon
		if (ball.getMinY() <= yMin){
			ball.setVely(-ball.getVel().y);
			System.out.println("fent");
			for (int steps=0; steps< 10 && (ball.getMinY() <= yMin); ++steps) {
				ball.x += ball.getVel().x;
				ball.y += ball.getVel().y;
				
			}
		}
		
		//tegla
		for (int k = 0; k < blockList.size(); k++){
			if (ball.intersects(blockList.get(k))){
				
				block = blockList.get(k);
				
				
				if (ball.intersects(block.getMinX(), block.getMinY(), 1, block.getHeight()) ||
					ball.intersects(block.getMaxX(),block.getMinY(), 1, block.getHeight())){
					ball.setVelx(-ball.getVel().x);
				}
				
				if (ball.intersects(block.getMinX(), block.getMinY(), block.getWidth(), 1) ||
					ball.intersects(block.getMinX(),block.getMaxY(), block.getWidth(), 1)){
					ball.setVely(-ball.getVel().y);
				}
				
				
				
				
				for (int steps=0; steps< 10 && ball.intersects(blockList.get(k)); ++steps) {
					ball.x += ball.getVel().x;
					ball.y += ball.getVel().y;
					
				} 
				
				if (block.decreaseBlockLife() <= 0){
					blockList.remove(k);
					--k;
					System.out.println("torol tegla" + k);
				}
				score++;
				
				if (blockList.size() == 0){
					this.play = false;
					this.win = true;
					System.out.println("nyert");
				}
				return true;
			}
		}
		
		
		//uto
		
		if (ball.intersects(paddle)){
			
			if (ball.intersects(paddle.getMinX(), paddle.getMinY(), 1, paddle.getHeight()) ||
					ball.intersects(paddle.getMaxX(),paddle.getMinY(), 1, paddle.getHeight())){
					ball.setVelx(-ball.getVel().x);
				}
				
				if (ball.intersects(paddle.getMinX(), paddle.getMinY(), paddle.getWidth(), 1) ||
					ball.intersects(paddle.getMinX(),paddle.getMaxY(), paddle.getWidth(), 1)){
					ball.setVely(-ball.getVel().y);
				}
				
				
				
				
				for (int steps=0; steps< 10 && ball.intersects(paddle); ++steps) {
					ball.x += ball.getVel().x;
					ball.y += ball.getVel().y;
					
				}
				
			
			double xDistance = (ball.x - paddle.x)/(paddle.width/2.0);
			
			
			
				
			double vx = ball.getVel().x;
			double vy = ball.getVel().y;
			double v = Math.sqrt(vx*vx+vy*vy);
			
			vx /= v;
			vy /= v;
			
			vx += 0.5 * xDistance;
			if (gui.getLeft() || gui.getRight())
				vx += Math.random() * 0.2 - 0.1;
			double vv = Math.sqrt(vx*vx+vy*vy);
			
			ball.setVelx(vx/vv*v);
			ball.setVely(vy/vv*v);
			
				
			
			System.out.println("uto");
		}
		
		return false;
	}
	
	//függvények a kiíratáshoz
	public String drawLife(){
		String lifeString;
		lifeString = Integer.toString(life);
		return lifeString;
	}
	public String drawScore(){
		String scoreString;
		scoreString = Integer.toString(score);
		return scoreString;
	}
	
	int getLife(){
		return life;
	}
	
	int getScore(){
		return score;
	}
	
	public void newGame(){
		init(gui.getLevel());	
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 100, 10);
	}
	
	public void playGame(){
		life = 3;
		score = 0;
		newGame();
	}
	
	
	public ArrayList<Integer> generateAllData(){
		 allData = new ArrayList<Integer>();
		 allData.add(this.life);
		 allData.add(this.score);
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
	    	
	    	if(play){
		        ball.refresh();
		        paddle.refresh();
		        collisionDetection();
		        generateAllData();
	        	sendAllData(allData);
		        if (multiPlayer){
		        	if (allData2 != null){
			        	if (multiPlayerEndGame())
			        		return;
		        	}
	        	generateAllData();
	        	sendAllData(allData);	
		        }
		        gui.drawScreen();
		        //System.out.println("jatek megy");
		        
	    	}
	    	else{
//	    		generateAllData();
//	    		sendAllData(allData);
	    		timer.cancel();
                timer.purge();
                System.out.println("jatek vege");
                if (win || multiPlayer){
                	try {
                	    TimeUnit.MILLISECONDS.sleep(10);
                	} catch (InterruptedException e) {
                	    //Handle exception
                	}
                	newGame();
                }
	    	}
	    }  
	    
	}
	
	public boolean multiPlayerEndGame(){
		if (score >= maxScore || allData2.get(0) <= 0 || life <= 0 || allData2.get(1) >= maxScore){
			score = 0;
			life = 3;
			this.play = false;
			allData2 = null;
			blockList = null;
			return true;
		}
		return false;
	}
	
	public void pause() {
        this.timer.cancel();
    }
	
	public void resume() {
	    this.timer = new Timer();
	    timer.scheduleAtFixedRate(new ScheduleTask(), 100, 10);
	}
	 
	ArrayList<Integer> getData(){
		return allData;
	}
	
	ArrayList<Integer> getData2(){
		return allData2;
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
			return;;
		net.send(allData2);
	}

	void allDataReceived(ArrayList<Integer> allData2) {
		if (gui == null)
			return;
		if (play)
			this.allData2 = allData2;
		else
			this.allData2 = null;
		//gui.addPoint(p); // NMD: todo call processing function here
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void negatePaused() {
		this.isPaused = !this.isPaused;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setEnd(boolean end){
		this.end = end;
	}
	
	public void setMultiPlayer(boolean multiPlayer){
		this.multiPlayer = multiPlayer;
	}
	
	public boolean getEnd(){
		return end;
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