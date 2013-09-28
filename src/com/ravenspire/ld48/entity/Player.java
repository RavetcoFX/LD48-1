package com.ravenspire.ld48.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.ravenspire.ld48.Game;
import com.ravenspire.ld48.InputHandler;

public class Player
{
	public ImageIcon imgPlyrR1 = new ImageIcon(this.getClass().getResource("/resources/Char/R/char_R_1.png"));		
	public ImageIcon imgPlyrR2 = new ImageIcon(this.getClass().getResource("/resources/Char/R/char_R_2.png"));
	public ImageIcon imgPlyrR3 = new ImageIcon(this.getClass().getResource("/resources/Char/R/char_r_3.png"));		
	public ImageIcon imgPlyrL1 = new ImageIcon(this.getClass().getResource("/resources/Char/L/char_L_1.png"));
	public ImageIcon imgPlyrL2 = new ImageIcon(this.getClass().getResource("/resources/Char/L/char_L_2.png"));		
	public ImageIcon imgPlyrL3 = new ImageIcon(this.getClass().getResource("/resources/Char/L/char_L_3.png"));
	public ImageIcon imgPlyrStill1 = new ImageIcon(this.getClass().getResource("/resources/Char/Still/char_1.png"));		
	public ImageIcon imgPlyrStill2 = new ImageIcon(this.getClass().getResource("/resources/Char/Still/char_2.png"));
	public ImageIcon imgJmp1 = new ImageIcon(this.getClass().getResource("/resources/Char/Jumping/char_jump_1.png"));
	public ImageIcon imgJmp2 = new ImageIcon(this.getClass().getResource("/resources/Char/Jumping/char_jump_2.png"));	
    private ImageIcon imgD1 = new ImageIcon(this.getClass().getResource("/resources/Death/death_1.png"));
    private ImageIcon imgD2 = new ImageIcon(this.getClass().getResource("/resources/Death/death_2.png"));
    private ImageIcon imgD3 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_bad.png"));
    private ImageIcon imgD4 = new ImageIcon(this.getClass().getResource("/resources/Death/death_3_good.png"));
    private ImageIcon imgD5 = new ImageIcon(this.getClass().getResource("/resources/Death/death_4.png"));

	public Image sprtPlyr;
	
	public static final int JUMP_HEIGHT = 130;
	public static final int GROUND_LEVEL = 687;
	private Game game;
	private Flying flying;
	private Random rand = null;
	private Platform platform;
	private int tickCount;
	public String[] name = {"Bodil Munson", "Mr. Man", "Clef Yinsal", "Borkid Eripsnevar", "Simon Gnikeci", "Sheldon", "Sanfit", "Sir. Loin", "Anne Chovie", "Sue Yu", "Krik Niantpac", "Notloc Tisitra"};
	private InputHandler input;
	public static int x, y, dx, dy, width, height;
	public static boolean alive;
	private boolean isDying = true;
	public static short still = 0;
	public int dir = 0;
	public static boolean isMoving = false;
	public static boolean isStill = true;
	public int aniCount = 1;
	public int aniCount2 = 0;
	private int oldX, oldY;

	private boolean isFalling = false;
	public static long jumpingTime = 250;
	public static boolean jumping = false;
	public static boolean canJump = false;
	public final int START_X = 0;
	public final int START_Y = GROUND_LEVEL;
	public boolean wasDeathGood;
	
	public Player(InputHandler input)
	{
		new Thread().start();
		sprtPlyr = imgPlyrStill1.getImage();
		alive = true;
		x = START_X;
		y = START_Y;
		width = sprtPlyr.getWidth(null);
		height = sprtPlyr.getHeight(null);
		this.input = input;
	}
	
	public void tick() throws InterruptedException
	{
		if(alive){
			if(touchingFloor()) oldY = y; oldX = x;
			gravity();
			move();
			checkIfStill();
			collision();
			width = sprtPlyr.getWidth(null);
			height = sprtPlyr.getHeight(null);

			tickCount++;
		}
	}
	
	/**
	 * Moves the Player
	 */
	public void move()
	{
		if(canJump) jump();
		
		if(input != null){
			if(input.left){
				isStill = false;
				if(x >= 2) x -= 3;
				dir = 1;
				left();
			}
			
			if(input.right){
				isStill = false;
				if(x <= 535) x += 3;
				dir = 2;
				right();
			}
		}
	}
	
	/**
	 * Makes player jump
	 */
	public void jump()
	{
		while(y > (oldY - JUMP_HEIGHT)){
			jumping = true;
			y -= 7;
		}
		canJump = false;
	}
	
	/**
	 * Adds gravity to the player
	 */
	public void gravity()
	{
		if(!touchingFloor()){
			isFalling = true;
			y += 6;
		}
	} 
	
	public void left()
	{
		switch(aniCount)
		{
			case 1:
				sprtPlyr = imgPlyrL1.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 2;
				}
				break;
			case 2:
				sprtPlyr = imgPlyrL2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 3;
				}
				break;
			case 3:
				sprtPlyr = imgPlyrL3.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 4;
				}
				break;
			case 4:
				sprtPlyr = imgPlyrL2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 1;
				}
				break;
		}
	}
	
	public void right()
	{	
		//System.out.println("right");
		switch(aniCount)
		{
			case 1:
				sprtPlyr = imgPlyrR1.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 2;
				}
				break;
			case 2:
				sprtPlyr = imgPlyrR2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 3;
				}
				break;
			case 3:
				sprtPlyr = imgPlyrR3.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 4;
				}
				break;
			case 4:
				sprtPlyr = imgPlyrR2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 1;
				}
				break;
		}
	}
	
	public void jumpPos()
	{
		switch(dir)
		{
			case 1:
				sprtPlyr = imgJmp1.getImage();
				break;
			case 2:
				sprtPlyr = imgJmp2.getImage();
				break;
		}
	}
	
	public void deathAni()
	{
		int cntDwn = 5;

		switch (aniCount2){
			case 0:
				sprtPlyr = imgD1.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount2 = 1;
				}
				break;
			case 1:
				sprtPlyr = imgD2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount2 = 2;
				}
				break;
			case 2:
				if(wasDeathGood) sprtPlyr = imgD4.getImage();
				else sprtPlyr = imgD3.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount2 = 3;
				}
				break;
			case 3:
				sprtPlyr = imgD5.getImage();
				cntDwn--;
				if(Game.tickCount2 == Game.DELAY && cntDwn > 0){
					aniCount2 = 2;
				}
				break;
		}
	}
	
	/**
	 * Idling animation
	 */
	public void idle()
	{
		switch(aniCount)
		{
			case 1:
				sprtPlyr = imgPlyrStill1.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 2;
				}
				break;
				
			case 2:
				sprtPlyr = imgPlyrStill2.getImage();
				if(Game.tickCount2 == Game.DELAY){
					aniCount = 1;
				}
				break;
		}
	}
	
	/**
	 * Checks if standing still
	 */
	public void checkIfStill()
	{
		if(isStill){
			if(touchingFloor()){
				isFalling = false;
				//sprtPlyr = imgPlyrStill1.getImage();
				idle();
			}
		}
	}
	
	/**
	 * Checks if touching a solid ground
	 * @return
	 */
	public static boolean touchingFloor()
	{
		boolean b1 = false;
		
        for (int i = 0; i < Game.platforms.size(); i++) {
            Platform plt = (Platform)Game.platforms.get(i);
    		
            if((getBottomBounds().intersects(plt.getBounds()))){
    			b1 = true;
    		}
        }
		return b1;
	}
	
	public void collision()
	{
		Rectangle parre = PeopleParents.getBounds();
		Rectangle sadRec = Sad.getBounds();
		Rectangle wfRec = PeopleWife.getBounds();


        for (int i = 0; i < Game.flyers.size(); i++) {
            Flying plt = (Flying)Game.flyers.get(i);
    		
            if(getBounds().intersects(plt.getBounds())){
    		}
            if(getBottomBounds().intersects(plt.getTopBounds())){
            	plt.deathAni();
         		Game.flyers.remove(plt);
         		plt.dead = true;
         	}
        }
        
        if(getBounds().intersects(parre))
        {
        	PeopleParents.gone = true;
        }
        
        if(getBounds().intersects(sadRec))
        {
        	Sad.gone = true;
        }
        
        if(getBounds().intersects(wfRec))
        {
        	wasDeathGood = true;
        	deathAni();

        }

	}

	public Image getSprt()
	{
		return sprtPlyr;
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
		return new Rectangle(x, y, width, height);
	}
	
	public static Rectangle getBottomBounds()
	{
		return new Rectangle(x, y + 113, width, height - 112);
	}
	
	
	public class thread implements Runnable 
	{
		public void run()
		{
			try {
				Thread.sleep(jumpingTime);
			} catch (InterruptedException e) {}
		}
	}
}
