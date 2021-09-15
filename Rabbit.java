import java.awt.*;
import javax.swing.*;
import java.lang.Math;


public class Rabbit extends Thread
{
	private Field f;
	private JPanel box;
	private Color color;
	private static int RabbitX = 10;
	private static int RabbitY = 10;
	double x;
	double y;
	static final int speed = 5;
	double[] distances = new double[Field.wolves];
	double velX;
	double velY;

	Rabbit(JPanel c, Field field, Color clr)
	{
		box = c;
		color = clr;
		f = field;
		x = (int) (Math.random() * (Field.sizeX - 10)) + 2;
		y = (int) (Math.random() * (Field.sizeY - 10)) + 2;		
	}
	public void run()
	{
		drawRabbit();
		while (true)
		{
			try { Thread.sleep(20); }
			catch (InterruptedException e)
			{
				return; 
			}
			move();
		}
	}
	
	public void drawRabbit()
	{
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.fillOval((int)x, (int)y, RabbitX, RabbitY);		
	}	
	
	public int minInd()
	{
		double minVal = distances[0];
		int minInd = 0;

		for (int i = 0; i < Field.wolves; i++)
		{
			if (distances[i] < minVal)
			{
				minVal = distances[i];
				minInd = i;
			}
		}
		return minInd;
	}
	
	public void moveRabbit(double velX, double velY)
	{
		int minIndex = minInd();
		
		for (int i = 0; i < f.w.length; i++)
		{
			distances[i] = Math.sqrt(Math.pow(f.w[i].x - x, 2)  + Math.pow(f.w[i].y - y , 2));
		}
		
		double dx = f.w[minIndex].x - x;
		double dy = f.w[minIndex].y - y;
		
		velX = (dx * speed) / (distances[minIndex]);
		velY = (dy * speed) / (distances[minIndex]);
	
		
		if (Field.sizeX - RabbitX - x <= speed)
		{
			if (Math.sqrt(Math.pow(f.w[minIndex].x, 2)  + Math.pow(f.w[minIndex].y, 2)) < distances[minIndex])
			{	
				x = x + 0;
			}
			else
			{
				while (x + velX > Field.sizeX - RabbitX)
				{
					x = 0;
				}
				while (x + velX < RabbitX)
				{
					x = Field.sizeX - RabbitX;
				}
				
				x = x - velX;
				
			}
		}
		else
		{
			while (x + velX > Field.sizeX - RabbitX)
			{
				x = 0;
			}
			while (x + velX < RabbitX)
			{
				x = Field.sizeX - RabbitX;
			}
		
			x = x - velX;
		}
		
		if (Field.sizeY - RabbitY - y <= speed)
		{
			if (Math.sqrt(Math.pow(f.w[minIndex].x, 2)  + Math.pow(f.w[minIndex].y, 2)) < distances[minIndex])
			{	
				y = y + 0;
			}
			else
			{
				while (y + velY > Field.sizeY - RabbitY)
				{
					y = RabbitY;
				}
				while (y + velY < RabbitY)
				{
					y = Field.sizeY- RabbitY;
				}
				y = y - velY;
			}
		}
		else
		{
			while (y + velY > Field.sizeY - RabbitY)
			{
				y = RabbitY;
			}
			while (y + velY < RabbitY)
			{
				y = Field.sizeY- RabbitY;
			}
			y = y - velY;
		}
		
	}
		
	public void move()
	{
		Graphics g = box.getGraphics();
		g.setColor(box.getBackground());
		g.fillOval((int)x, (int)y, RabbitX, RabbitY);
		
		moveRabbit(velX, velY);	
		
		while (x + velX > Field.sizeX - RabbitX)
		{
			x = RabbitX;
		}
		while (x + velX < RabbitX)
		{
			x = Field.sizeX - RabbitX;
		}
		while (y + velY > Field.sizeY - RabbitY)
		{
			y = RabbitY;
		}
		while (y + velY < RabbitY)
		{
		y = Field.sizeY - RabbitY;
		}
			
		g.setColor(color);
		g.fillOval((int)x, (int)y, RabbitY, RabbitY);
	}

}