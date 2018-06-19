// Launcher.java
// 11/27/17
// Jake Maro
// 
// This file launches the game.
//
//
//

package mainPackage;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("Game", 1280, 640);
		game.start();
	}
}
