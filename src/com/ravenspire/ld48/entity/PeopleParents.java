package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ravenspire.ld48.Game;

public class PeopleParents
{
	public static int x, y, width, height;
	Game game;
	public ImageIcon img1 = new ImageIcon(this.getClass().getResource("/resources/People/parents.png"));		
    private ImageIcon imgD1 = new ImageIcon(this.getClass().getResource("/resources/Death/death_1.png"));
    private ImageIcon imgD2 = new ImageIcon(this.getClass().getResource("/resources/Death/death_2.png"));
    private ImageIcon imgD3 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_bad.png"));
    private ImageIcon imgD4 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_good.png"));
    private ImageIcon imgD5 = new ImageIcon(this.getClass().getResource("/resources/Death/death_4.png"));
	public Image sprt = img1.getImage();
	private int aniNum = 1;
	public static boolean gone = false;
	
	public PeopleParents()
	{
		x = 450;
		y = 300;
		height = sprt.getHeight(null);
		width = sprt.getWidth(null);
	}
	
	public void tick()
	{
		if(gone) deathAni();
	}
	
	public void deathAni()
	{
		switch (aniNum)
		{
			case 0:
				sprt = imgD1.getImage();
				if(Game.tickCount2 == game.DELAY){
					aniNum = 1;
				}
				break;
			case 1:
				sprt = imgD2.getImage();
				if(Game.tickCount2 == game.DELAY){
					aniNum = 2;
				}
				break;
			case 2:
				sprt = imgD5.getImage();
				if(Game.tickCount2 == game.DELAY){
					aniNum = 3;
				}
				break;
			case 3:
				sprt = null;
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
		return new Rectangle(x + 30, y, width -  65, height - 40);
	}
	
	public Image getSprt()
	{
		return sprt;
	}
}
