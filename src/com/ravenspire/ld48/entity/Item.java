package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Item 
{
	private ImageIcon img1 = new ImageIcon(this.getClass().getResource("/resources/Items/burrito.png"));
	private ImageIcon img2 = new ImageIcon(this.getClass().getResource("/resources/Items/carrot.png"));
	private ImageIcon img3 = new ImageIcon(this.getClass().getResource("/resources/Items/cupkake.png"));		
	private Image sprt;
	public boolean visible;

	public int x, y, width, height, type = 0;
	
	public Item(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		width = 64;
		height = 64;
		this.type = type;
	}
	
	public void tick()
	{
		getType();
	}
	
	public void getType()
	{
		if(visible){
			if(type == 0) sprt = img1.getImage();
			if(type == 1) sprt = img2.getImage();
			if(type == 2) sprt = img2.getImage();
		}

	}
	
	public Image getSprt()
	{
		return sprt;
	}
	
	public boolean isVisible() 
	{
		return visible;
	}

	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}

	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, width, height);
	}

}
