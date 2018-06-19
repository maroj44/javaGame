// Game.java
// 11/27/17
// Jake Maro
// 
// This file contains all game info.
// a thread is a miniprogram
//
//
// game loop updates variables, positions, then renders (draws everything), then repeats
package mainPackage;

import java.awt.*;
import java.awt.image.*;

public class Game implements Runnable { // allows to be ran in a thread

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs; // way for computer to draw to screen with hidden screen (buffer)
	private Graphics g;
	
	//states
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//input
	private KeyManager keyManager;
	
	//camera
	private GameCamera gameCamera;
	
	//handler
	private Handler handler;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
		
		
	}
	

	
	private void tick() { // updates things
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
		
	}
	
	

	
	private void render() { //draw things to the screen
		bs = display.getCanvas().getBufferStrategy(); // sets bs to current bs of game
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3); // if it doesnt already have a buffer
			return;
		}
		g = bs.getDrawGraphics();
		// clear screen
		g.clearRect(0, 0, width, height);
		// draw here
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		// end drawing
		bs.show(); // okay im done now
		g.dispose(); // toss the canvas
	}
	
	private void init() { //initializes graphics of game, called once
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		
		//initialize all states
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		
		State.setState(gameState);
	}
	
	public void run() { // needed for implementing Runnable, will contain most of code
		
		init();
		
		int fps = 60; //frames (ticks) per second
		double timePerTick = 1000000000 / fps; //1 billion nanoseconds in one second
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); //returns amount of current time in nanoseconds
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick; // tells computer when to call tick&render
			timer += now - lastTime; // adds amt of time since block was last called
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() { //synchronized used when working with threads directly
		if(running) // just incase
			return;
		running = true;
		thread = new Thread(this);
		thread.start(); //calls run method
		
	}
	
	public synchronized void stop() {
		if(!running) //just incase
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
