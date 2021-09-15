import java.awt.*;
import javax.swing.*;
import java.lang.Math;

public class Wolf extends Thread
{
	private Field f;
	private JPanel box;
	private Color color;
	private static int WolfX = 10;
	private static int WolfY = 10;
	double x;
	double y;
	static final int speed = 5;
	double distances[] = new double [Field.rabbits];
	double velX;
	double velY;

	Wolf(JPanel c, Field field, Color clr)
	{
		box = c;
		color = clr;
		f = field;
		x = (int) (Math.random() * (Field.sizeX - WolfX)) + 2;
		y = (int) (Math.random() * (Field.sizeY - WolfY)) + 2;		
	}
	
	public void run()
	{
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.fillOval((int)x, (int)y, WolfX, WolfY);		

		while (true)
		{
			try { Thread.sleep(20); }
			catch (InterruptedException e)
			{ return; }
			move();
		}
	}
	
	public int minInd()
	{
		double minVal = distances[0];
		int minInd = 0;

		for (int i = 0; i < Field.rabbits; i++)
		{
			if (distances[i] < minVal)
			{
				minVal = distances[i];
				minInd = i;
			}
		}
		return minInd;
	}
	
	public void moveWolf(double velX, double velY)
	{
		for (int i = 0; i < f.r.length; i++)
		{
			distances[i] = Math.sqrt(Math.pow(f.r[i].x - x, 2)  + Math.pow(f.r[i].y - y , 2));
		}
		int minIndex = minInd();
		
		double dx = f.r[minIndex].x - x;
		double dy = f.r[minIndex].y - y;
		
		velX = (dx * speed) / (distances[minIndex]);
		velY = (dy * speed) / (distances[minIndex]);
		x = x + velX;
		y = y + velY;
	}

	public void move()
	{
		Graphics g = box.getGraphics();
		g.setColor(box.getBackground());
		g.fillOval((int)x, (int)y, WolfX, WolfY);
		
		moveWolf(velX, velY);	
		
		while (x + velX > Field.sizeX - WolfX)
		{
			x = WolfX;
		}
		while (x + velX < WolfX)
		{
			x = Field.sizeX - WolfX;
		}
		while (y + velY > Field.sizeY - WolfY)
		{
			y = WolfY;
		}
		while (y + velY < WolfY)
		{
		y = Field.sizeY - WolfY;
		}
			
		g.setColor(color);
		g.fillOval((int)x, (int)y, WolfX, WolfY);
	}

}