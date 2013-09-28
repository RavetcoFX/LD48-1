package com.ravenspire.ld48;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ravenspire.ld48.entity.Player;

public class InputHandler implements KeyListener
{
	private Game game;
	private Player player;
	public InputHandler(Game game) 
	{
        game.addKeyListener(this);
    }

    public class Key 
    {
    	private int timesPrsd = 0;
        private boolean pressed = false;
        private boolean released = true;

        public int getNumTimesPressed() 
        {
            return timesPrsd;
        }

        public boolean isPressed() 
        {
            return pressed;
        }
        
        public boolean isReleased() 
        {
            return released;
        }

        public void toggle(boolean isPressed) 
        {
            pressed = isPressed;
            released = !isPressed;
            if (isPressed) timesPrsd++;
        }
    }

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean debug;
    public boolean space;
    public boolean esc;
    public boolean enter;
    public boolean start;
    

    public void keyPressed(KeyEvent e) 
    { 
		int keyCode = e.getKeyCode(); 
        
    	//System.out.println("Toggled");
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up = true;
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left = true;;
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (keyCode == KeyEvent.VK_F3) {
        	game.debugMode = !game.debugMode;
        }
        if (keyCode == KeyEvent.VK_F5) {
        }
        if (keyCode == KeyEvent.VK_SPACE) {
        	//player.canJump = true;
        	up = true;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
        	if(!game.inMenu) game.inMenu = true;
			if(game.inGame) game.inGame = false;
        }
        if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE){
        	if(game.inMenu) game.inMenu = false; game.timerStarted = true;
			if(!game.inGame) game.inGame = true; game.running = true;
        }
    }
    


    public void keyReleased(KeyEvent e) 
    {
		int keyCode = e.getKeyCode(); 
        
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up = false;
            player.isStill = true;
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down = false;
            player.isStill = true;
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left = false;
            player.isStill = true;
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right = false;
            player.isStill = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
        	up = false;
        }
        //System.out.println("Let Go!");
        player.isMoving = false;
        
        if(!player.isMoving){
        	player.isStill = true;
        }
    }

    public void keyTyped(KeyEvent e) 
    {
    }
}
