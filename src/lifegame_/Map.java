package lifegame_;
import javax.swing.JFrame;

public class Map extends JFrame { 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map lg = new Map();
		lg.setSize(300,300);
		lg.setVisible(true);
		Map mp = new Map();
		lg.add(mp);
		mp.flash();

		//	Map m = new Map(20,20);
	}

}