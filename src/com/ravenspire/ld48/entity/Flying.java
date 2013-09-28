package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ravenspire.ld48.Game;

public class Flying 
{
	private ImageIcon img1 = new ImageIcon(this.getClass().getResource("/resources/Flying/flying_1.png"));		
	private ImageIcon img2 = new ImageIcon(this.getClass().getResource("/resources/Flying/flying_2.png"));
	private ImageIcon img3 = new ImageIcon(this.getClass().getResource("/resources/Flying/flying_3.png"));		
	private ImageIcon img4 = new ImageIcon(this.getClass().getResource("/resources/Flying/flying_4.png"));		
    private ImageIcon imgD1 = new ImageIcon(this.getClass().getResource("/resources/Death/death_1.png"));
    private ImageIcon imgD2 = new ImageIcon(this.getClass().getResource("/resources/Death/death_2.png"));
    private ImageIcon imgD3 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_bad.png"));
    private ImageIcon imgD4 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_good.png"));
    private ImageIcon imgD5 = new ImageIcon(this.getClass().getResource("/resources/Death/death_4.png"));
	public Image sprt;
	public int x, y, dx, dy, width, height;
	public int dir = 0;
	public int aniNum = 0;
	public int tickCount = 0;
	private Game game;
	public int SPEED = 3;
	public boolean dead = false;
	
	public Flying(int x, int y)
	{
		sprt = img1.getImage();
		this.x = x;
		this.y = y;

	}
	
	public void tick()
	{
		width = sprt.getWidth(null);
		height = sprt.getHeight(null);
		if(dead) deathAni();
		chgDir();
		move();
		
		if(dir == 0 || dir == 1){
			x -= SPEED;
			if(x <= 10){
				x += x;
				dir = 2;
			}
		}
		
		if(dir == 2 || dir == 3){
			x += SPEED;
			if(x >= 250){
				dir = 0;
			}
		}
		
		tickCount++;
	}
	
	public void move()
	{
		x += dx;
	}
	
	public void chgDir()
	{
		switch(dir)
		{
			case 0:
				sprt = img1.getImage();
				if(Game.tickCount2 == game.DELAY){
					dir = 1;
				}
				break;
			case 1:
				sprt = img2.getImage();
				if(Game.tickCount2 == game.DELAY){
					dir = 0;
				}
				break;
			case 2:
				sprt = img3.getImage();
				if(Game.tickCount2 == game.DELAY){
					dir = 3;
				}
				break;
			case 3:
				sprt = img4.getImage();
				if(Game.tickCount2 == game.DELAY){
					dir = 2;
				}
				break;
		}
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
	
	public Image getSprt()
	{
		return sprt;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x + 10, y + 75, width - 30, height - 100);
	}
	
	public Rectangle getTopBounds()
	{
		return new Rectangle(x + 10, y + 46, width - 50, height - 100);
	}

}
