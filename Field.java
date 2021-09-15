import java.awt.*;
import java.lang.Math;
import javax.swing.*;


public class Field extends JFrame
{
	final static int wolves = 10;
	final static int rabbits = 10;
	final static int sizeX = 400;
	final static int sizeY = 400;

	public Wolf[] w = new Wolf[wolves];
	public Rabbit[] r = new Rabbit [rabbits];

	private static JPanel surface;
	public static void main(String [] a)
	{
		Field jf = new Field();
		jf.setSize(sizeX, sizeY);
		jf.setVisible(true);
			
		for (int i = 0; i < wolves; i++)
		{
			jf.w[i] = new Wolf(surface, jf, Color.BLACK);
		}
		for (int i = 0; i < rabbits; i++)
		{
			jf.r[i] = new Rabbit(surface, jf, Color.WHITE);
		}
		
		for (Wolf wolf : jf.w)
		{
			wolf.start();
		}
		for (Rabbit rabbit : jf.r)
		{
			rabbit.start();
		}
	}

	
	Field()
	{
		setTitle("Wolves n' Rabbits");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setBackground(Color.BLACK);
		surface = new JPanel();
		surface.setBackground(Color.GREEN);
		cp.add(surface, "Center");
	}
}