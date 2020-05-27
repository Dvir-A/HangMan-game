package mmn11;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int _toDraw;
	public static final int DRAW_LIM = 9; 
	public MyPanel() {
		super();
		this._toDraw=0;
		this.setSize(WIDTH, HEIGHT);
	}
	
	public void setDraw(int toDraw) {
		this._toDraw=toDraw;
	}
	
	public int getDraw() {
		return this._toDraw;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		int x,y;
		final int headSize=50;
		final int body = 2*headSize;
		x=y=10;
		for(int i=0;i<=this._toDraw ;i++) {
			switch (i) {
			case 1:
				g.drawLine(x, y, x, this.getHeight());
				break;

			case 2:
				x=50;
				g.drawLine(y, y, (x=(this.getWidth()/2)), y);
				break;
				
			case 3:
				g.drawLine(x, y, x, y=(2*y));
				break;
				
			case 4: //head
				g.drawOval(x-(headSize/2), y, headSize, headSize);
				break;
				
			case 5://eyes
				g.drawOval(x+(headSize/8), y+(headSize/4), headSize/4, headSize/4);
				g.drawOval(x-(headSize/8)-(headSize/4), y+(headSize/4), headSize/4, headSize/4);
				break;
				
			case 6://Nose and mouth
				g.drawLine(x,y+(headSize/2), x, y+10+(headSize/2));
				g.drawArc(x-(headSize/4), (y+((headSize*3)/4)), ((headSize)/2),(headSize/4), 0, 180);
				break;
					
			case 7://body
				g.drawLine(x, y+headSize, x, y+headSize+body);
				break;
			
			case 8://Hands
				g.drawLine(x, headSize+y+(body/4), x+(2*headSize), (2*headSize)+y+(body/2));
				g.drawLine(x, headSize+y+(body/4), x-(2*headSize), (2*headSize)+y+(body/2));
				break;
				
			case 9://legs
				g.drawLine(x, headSize+y+(body), x+((3*headSize)/2), (2*headSize)+y+((3*body)/2));
				g.drawLine(x, headSize+y+(body),x-((3*headSize)/2), (2*headSize)+y+((3*body)/2));
				break;
			}
			
		}
	}


}
