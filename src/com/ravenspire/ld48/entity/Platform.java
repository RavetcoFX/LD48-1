package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Platform 
{
	public int x, y, width, height;
	private static String imgName = "/resources/platform.png";
    private ImageIcon img = new ImageIcon(this.getClass().getResource(imgName));
	private Image sprt = img.getImage();

	public Platform(int x, int y)
	{
		this.x = x;
		this.y = y;
		height = sprt.getHeight(null);
		width = sprt.getWidth(null);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return sprt;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, width, height - 25);
	}
}
