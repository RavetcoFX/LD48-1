package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ravenspire.ld48.Game;

public class TheSun 
{
    private ImageIcon imgSun1 = new ImageIcon(this.getClass().getResource("/resources/Sun/sun_1.png"));
    private ImageIcon imgSun2 = new ImageIcon(this.getClass().getResource("/resources/Sun/sun_2.png"));

	private Image sprtSun = imgSun1.getImage();
	public int x, y, dx, dy, width, height;
	public int tickCount;
	public int pos;
	private Game game;
	
	public TheSun()
	{
		y = 0;
		x = 670;
		width = sprtSun.getWidth(null);
		height = sprtSun.getHeight(null);
		pos = 0;
	}
	
	public void tick()
	{
		if(Game.timerStarted) move();
		
		if(y >= 740)
		{
		}
		tickCount++;
	}
	
	public void move()
	{
		if(y <= 740){
			y += 1;
		}
		if(y >= 335)
		{
			sprtSun = imgSun2.getImage();
		}


	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Image getSprt()
	{
		return sprtSun;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, width, height);
	}

}
