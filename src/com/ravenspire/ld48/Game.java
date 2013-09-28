package com.ravenspire.ld48;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.ravenspire.ld48.entity.Flying;
import com.ravenspire.ld48.entity.Item;
import com.ravenspire.ld48.entity.PeopleParents;
import com.ravenspire.ld48.entity.PeopleWife;
import com.ravenspire.ld48.entity.Platform;
import com.ravenspire.ld48.entity.Player;
import com.ravenspire.ld48.entity.Sad;
import com.ravenspire.ld48.entity.TheSun;

/**
 * My Ludum Dare Submission for the theme 10 seconds
 * 
 * All rights reserved Ravenspire Inc.
 * 
 * @author Ravenspire Inc.
 *
 */
public class Game extends Canvas implements Runnable
{
	/* Finals here */
	private static final long serialVersionUID = 602801997L; //#6D7920 //you guys will never get this
	public static final String TITLE = "When The Sun Sets";
	public static final String VERSION = "0.4.5";
	public static final int WIDTH = 640;
	public static final int HEIGHT = 705;
	

	/* Objects Here */
	private JFrame frame;
	private Player player;
	private PeopleParents par;
	private PeopleWife wife;
	private Sad sad;
	private TheSun sun;
	public Sound sound;
	private Flying flyer;
	private InputHandler input;
	private Random rand;
	public Color bkColor = new Color(244, 242, 243);
	public Color sideColor = new Color(153, 151, 152);
	public Font bigFont = new Font(Font.SANS_SERIF, 30, 42);
	public Font regularFont = new Font(Font.SANS_SERIF, 10, 10);
	public static Rectangle stop1;
	public static Rectangle stop2;
	public static Rectangle stop3;
	public static Rectangle stop4;
	public static Rectangle stop5;
	public static Rectangle stop6;

	
	public Platform platform;
	public static ArrayList<Platform> platforms;
	
	public static ArrayList<Flying> flyers;
	
	public static ArrayList<Item> items;


	/* Images  Here */	
	private static String imgName2 = "/resources/menuBack.png";
    private ImageIcon imgBack = new ImageIcon(this.getClass().getResource(imgName2));
	private Image sprtBack = imgBack.getImage();
	
	private static String imgName3 = "/resources/timerSide.png";
    private ImageIcon imgTimer = new ImageIcon(this.getClass().getResource(imgName3));
	private Image sprtTimer = imgTimer.getImage();
	
	private static String imgName4 = "/resources/lose.png";
    private ImageIcon imgLose = new ImageIcon(this.getClass().getResource(imgName4));
	private Image sprtLose = imgLose.getImage();
	
	/* Booleans Here */
	public static boolean debugMode = true;
	public static boolean running = false;
	public boolean isTouchingFloor;
	public static boolean inMenu = true;
	public static boolean inGame = false;
	public boolean canMenu = true;
	public static boolean isLose = false;
	public static boolean timerStarted = false;
	
	/* Ints, shorts, longs, ect Here */
	public static int DELAY = 10;
	public int tickCount = 0;
	public static int tickCount2 = 0;
	public static int tickCount3 = 0;
    public int timeLimit;
    private long lastTimer;
	public static int sunX, sunY, sunHeight, sunWidth;
	private int tickChunk;
	public int passedTime;
	public static int timeLeft = 10;
	private boolean canRes = true;

	
	public Game()
	{
		/*
		 * Size ends up being x800  y880
		 */
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame = new JFrame(TITLE + " v" + VERSION);
		//frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		//frame.requestFocus();
		//frame.setFocusable(true);
	}
	
	/**
	 * Starting method for applets
	 */
	public synchronized void start()
	{
		running = true;
		init();
		new Thread(this).start();
	}
	
	public synchronized void stop()
	{
		running = false;
	}
	
	/**
	 * Initiates stuff
	 */
	public void init()
	{
		timeLimit = 10;
		input = new InputHandler(this);
        player = new Player(input);
        wife = new PeopleWife();
        par = new PeopleParents();
        sad = new Sad();
        sun = new TheSun();
        initPlatforms();
        initFlyers();
        initItems();
        //sound.music.loop();
	}
	
	/**
	 * the method that runs the show
	 */
	public void run()
	{
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        lastTimer = System.currentTimeMillis();
        double i1 = 0;

        while (running == true) 
        {
            long now = System.nanoTime();
            i1 += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (i1 >= 1) {
                tick();
                i1 -= 1;
                shouldRender = true;
            }
            
            try { Thread.sleep(2); } 
            catch (InterruptedException e) {
            }

            if (shouldRender == true) {
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;                
            }
        
        }
    }
	
	public void tick()
	{
		try {
			player.tick();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sun.tick();
		wife.idle();
		par.tick();
		sad.tick();
		
		/*The Bottom Bounding boxes */
		//stop1 = new Rectangle(122, 725, 256, 40);
		stop2 = new Rectangle(350, 440, 256, 10);
		stop3 = new Rectangle(122, 718, 256, 10);
		stop4 = new Rectangle(122, 718, 256, 10);
		stop5 = new Rectangle(122, 718, 256, 10);
		stop6 = new Rectangle(122, 718, 256, 10);
		
        for (int i = 0; i < flyers.size(); i++) {
            Flying fly = (Flying)flyers.get(i);
            fly.tick();
        }		
        for (int i = 0; i < items.size(); i++) {
            Item it = (Item)items.get(i);
            it.tick();
        }		
		if(tickCount2 == DELAY){
			tickCount2 = 0;
		}		
		
		if(timeLeft <= 0){
			timeLeft = 0;
		}
		
		if(tickCount3 >= 100 && timerStarted){
			tickCount3 = 0;
			timeLeft--;
		}		
		
		if(tickCount == 1000){
			tickCount = 0;
			tickChunk += 1; 
			System.out.println(tickChunk);
		}		
		
		
		tickCount3++;
		tickCount2++;
		tickCount++;
	}
	
	/**
	 * Initiates the Platform Array and set there position
	 */
	public void initPlatforms()
	{
		platforms = new ArrayList<Platform>();
		
		platforms.add(new Platform(490, 790));
		
		platforms.add(new Platform(250, 710));
		platforms.add(new Platform(122, 710));
		
		platforms.add(new Platform(429, 623));
		
		platforms.add(new Platform(200, 520));
		platforms.add(new Platform(72, 520));
		
		platforms.add(new Platform(350, 430));
		platforms.add(new Platform(478, 430));
		
		platforms.add(new Platform(160, 330));
		platforms.add(new Platform(42, 330));
		
		
		platforms.add(new Platform(350, 230));
		platforms.add(new Platform(478, 230));
		
	
		/* These are the Floors */
		platforms.add(new Platform(0, 870));
		platforms.add(new Platform(128, 870));
		platforms.add(new Platform(256, 870));
		platforms.add(new Platform(384, 870));
		platforms.add(new Platform(512, 870));
		

	}
	
	/**
	 * Initiates enemys
	 */
	public void initFlyers()
	{
		flyers = new ArrayList<Flying>();
		flyers.add(new Flying(200, 370));

	}
	
	public void initItems()
	{
		items = new ArrayList<Item>();	
		
		//items.add(new Item(152, 645, 0));
	}

	

	/**
	 * Renders the graphics 
	 */
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3); //triple buffered, to ensure no tearing
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		if(inMenu && canMenu)
		{
			//g.scale(0.8, 0.8);
			g.setColor(bkColor);
			g.fillRect(0, 0, 800, 880);//draws the canvas
			g.drawImage(sprtBack, 0, 1, this);
		}
		
		if(inGame || !canMenu && !isLose)
		{
		g.scale(0.8, 0.8);
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 880);//draws the canvas
		//g.setColor(sideColor);
		//g.fillRect(640, 0, 160, 880);
		//g.drawImage(sprtTimer, 610, 0, this);
		
		g.setColor(Color.BLACK);
		//g.drawImage(this.bkgrnd, 0, 0, this);
		
		/*
		 * entity drawing area
		 */
		g.drawImage(player.getSprt(), player.getX(), player.getY(), this);
		g.drawImage(wife.getSprt(), wife.getX(), wife.getY(), this);
		g.drawImage(par.getSprt(), par.getX(), par.getY(), this);
		g.drawImage(sad.getSprt(), sad.getX(), sad.getY(), this);

		g.drawImage(sun.getSprt(), sun.getX(), sun.getY(), this);
		
        for (int i = 0; i < platforms.size(); i++) {
            Platform plt = (Platform)platforms.get(i);
            g.drawImage(plt.getImage(), plt.getX(), plt.getY(), this);
        }
        
        for (int i = 0; i < flyers.size(); i++) {
            Flying fly = (Flying)flyers.get(i);
            g.drawImage(fly.getSprt(), fly.getX(), fly.getY(), this);
        }
        for (int i = 0; i < items.size(); i++) {
            Item i1 = (Item)items.get(i);
            g.drawImage(i1.getSprt(), i1.getX(), i1.getY(), this);
        }
			
		if (this.debugMode){
	        g.setFont(regularFont);

			Rectangle charRect = player.getBounds();
			Rectangle wifeRect = wife.getBounds();
			Rectangle charRectLow = player.getBottomBounds();
			Rectangle parRect = par.getBounds();
			Rectangle sadRect = sad.getBounds();


			Rectangle sunRect = sun.getBounds();
	        for (int i = 0; i < flyers.size(); i++) {
	            Flying plt = (Flying)flyers.get(i);
	            g.draw(plt.getBounds());
	        }
	        for (int i = 0; i < platforms.size(); i++) {
	            Platform plt = (Platform)platforms.get(i);
	            g.draw(plt.getBounds());
	        }
	        for (int i = 0; i < items.size(); i++) {
	            Item i1 = (Item)items.get(i);
	            g.draw(i1.getBounds());
	        }
			g.draw(charRect);
			g.draw(wifeRect);
			g.draw(parRect);
			g.draw(sadRect);
			g.setColor(Color.red);
			g.draw(charRectLow);
			g.draw(stop1);
			g.draw(stop2);
	        for (int i = 0; i < flyers.size(); i++) {
	            Flying plt = (Flying)flyers.get(i);
	            g.draw(plt.getTopBounds());
	        }
			g.setColor(Color.black);
			g.draw(sunRect);
			
			g.drawString("Ticks: " + tickCount, 3, 10);
			g.drawString("X: " + player.getX() + " Y: " + player.getY() , 3, 25);	
			}
		}
		
		if(isLose){
			g.setColor(new Color(244, 241, 241));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(sprtLose, 0, 150, this);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//isLose = false;
		}
		
		//g.setColor(Color.black);
        //g.setFont(bigFont);
		//g.drawString("" + timeLeft, 390, 42);
	
		//System.out.println("I did that thing you wanted me to do :|");
		
		g.dispose();
		bs.show();
	}
		

	public static void main(String[] args)
	{
		new Game().start();
	}
}
