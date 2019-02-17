package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.WorldSector;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	private static HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	private static ArrayList<WorldSector> sectors = new ArrayList<WorldSector>();
	private static int playerID = 0;

  public static void main(String[] args) {
	  
	}

	public static void killPlayer(int player) {
		players.remove(player);
	}

	public static void newPlayer(WorldSector sector) {
		++playerID;
		players.put(playerID, new Player(playerID, sector));
		sector.addPlayer();
	}

	private static void startGame() {
		//sectors.add(new WorldSector());
	}

	private static void endGame() {
		
	}

	public Player getPlayer(int pID) {
  		return players.get(pID);
	}
}
