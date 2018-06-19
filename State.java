package mainPackage;

import java.awt.Graphics;

public abstract class State { //must have subclasses, cannot be directly instantiated
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//class
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick(); //abstract methods can only be used and defined by subclasses
	
	public abstract void render(Graphics g);
	
	
	
}
