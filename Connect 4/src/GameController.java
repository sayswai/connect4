import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;


public class GameController {

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(0,0, 2, 2));
	}
	
	public static void main(String[] args){
		new GameController().paint(null);
	}
}