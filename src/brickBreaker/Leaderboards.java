package brickBreaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Leaderboards{

	private static Leaderboards Board;
	private String filePath;
	private String highScores;
	 
	private ArrayList<String> Name;
	private ArrayList<Integer> Scores;
	
	private Leaderboards(){
		filePath = new File("").getAbsolutePath();
		highScores= "Scores";

		Name=new ArrayList<String>();
		Scores=new ArrayList<Integer>();
	}
	
	public static Leaderboards getInstance(){
		if(Board == null){
			Board= new Leaderboards();
	}
	return Board;
	}
	
	public void addScore(int Score, String name){
		for(int i=0; i< Scores.size();i++){
			if(Score >= Scores.get(i)){
				Scores.add(i,Score);
				Name.add(i,name);
				Scores.remove(Scores.size()-1);
				Name.remove(Name.size()-1);
				return;
			}
		}
	}
	
/*	public void loadScores(){
		try{
			File f=new File(filePath, highScores);
			if(!f.isFile()){
				createSaveData();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		
			Scores.clear();
			Name.clear();
			
			String[] scores = reader.readLine().split(".");
			String[] names =reader.readLine().split(".");
			
			for(int i=0; i< scores.length;i++)
			{
				Scores.add(Integer.parseInt(scores[i]));
			}
			for(int i=0; i<names.length;i++){
				Name.add(String.valueOf(names[i]));
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}*/
	
	
	public void saveScores(){
		FileWriter output = null;
		try{
			File f = new File(filePath,highScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write(Scores.get(0) + "-" + Scores.get(1) + "-"+Scores.get(2) + "-"+Scores.get(3));
			writer.newLine();
			writer.write(Name.get(0) + "-" + Name.get(1) + "-"+Name.get(2) + "-"+Name.get(3));
			writer.newLine();
			}catch(Exception e){
				e.printStackTrace();
			}
	
	}
	private void createSaveData(){
		FileWriter output = null;
		try{
			File f = new File(filePath,highScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write("0-0-0-0");
			writer.newLine();
			writer.write("0-0-0-0");
			writer.newLine();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	public int getHighScore(){
		return Scores.get(0);
	}
	public String getName(){
		return Name.get(0);
	}
	
	
}