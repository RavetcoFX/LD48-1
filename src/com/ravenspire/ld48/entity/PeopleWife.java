package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ravenspire.ld48.Game;

public class PeopleWife
{
	public static int x, y, width, height;
	Game game;
	public ImageIcon img1 = new ImageIcon(this.getClass().getResource("/resources/People/wife_1.png"));		
	public ImageIcon img2 = new ImageIcon(this.getClass().getResource("/resources/People/wife_2.png"));
	public Image sprt = img1.getImage();
	private int aniCount = 1;
	
	public PeopleWife()
	{
		x = 480;
		y = 104;
		height = sprt.getHeight(null);
		width = sprt.getWidth(null);
	}
	
	public void idle()
	{
		switch(aniCount)
		{
			case 1:
				sprt = img1.getImage();
				if(Game.tickCount2 == game.DELAY){
					aniCount = 2;
				}
				break;
				
			case 2:
				sprt = img2.getImage();
				if(Game.tickCount2 == game.DELAY){
					aniCount = 1;
				}
				break;
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

	public static Rectangle getBounds()
	{
		return new Rectangle(x + 30, y, width -  65, height);
	}
	
	public Image getSprt()
	{
		return sprt;
	}
}
