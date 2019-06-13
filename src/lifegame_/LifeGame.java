package lifegame_;
import 	java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.math.*;

public class LifeGame extends JPanel{
	private int rows;
	private int cols;
	final private int width = 10;
	final private int length = 10;
	private int map[][];
	private static int status_now[][];
	private static int status_next[][];
	Timer timer;
	
	
	public LifeGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		status_now = new int[rows][cols];
		status_next = new int[rows][cols];
	}
	
	public void reset() {
		int i,j;
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++) {
				status_next[i][j] = status_now[i][j] = (Math.random()>0.5?0:1);
				//System.out.println(status_next[i][j]);
			}
	}
	
	public int get_neibor_count(int row, int col) {
		int i ,j , count = 0;
		for(i = row-1; i<=row+1; i++) {
			for(j = col-1; j<=col+1; j++) {
				if(i==row&&j==col) 
					continue;
				if(i<0||i>=rows||j<0||j>=cols) 
					continue;
				if(status_now[i][j] == 1)
					count++;
			}
		}
		return count;
	}
	
	public void set() {
		int i,j;
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++) {
				int count = get_neibor_count(i,j);
				if(count == 3)
					status_next[i][j] = 1;
				else if(count == 2)
					status_next[i][j] = status_now[i][j];
				else
					status_next[i][j] = 0;
			}

		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				status_now[i][j]  = status_next[i][j];
	}
	
	public void paint(Graphics g) {
		int i,j;
		super.paint(g);
		for(i=0; i<rows;i++)
			for(j=0;j<cols;j++) {
				if(status_next[i][j] == 1)
					g.fillRect(i*width, j*length, width, length);
				else
					g.drawRect(i*width, j*length, width, length);
			}
	}
	
	public void flash() {
		this.reset();
		timer = new Timer(200, new ActionListener() {  
			
			public void actionPerformed(ActionEvent e) {  
				//System.out.println("1234");
				set();
				repaint();  
			}  
		});
		timer.start();	
	}
}


