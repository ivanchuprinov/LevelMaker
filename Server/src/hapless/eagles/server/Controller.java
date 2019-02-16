import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	ArrayList<WorldSector> sectors = new ArrayList<WorldSector>();
	private int playerID = 0;

  public static void main(String[] args) {
	  
	}

	public static void killPlayer(int player) {
		players.remove(player);
	}

	public static void newPlayer(WorldSector sector) {
		++playerID;
		players.put(playerID, new Player());
		sector.addPlayer(playerID);
	}

	private static void startGame() {
		sectors.add(new WorldSector);
	}

	private static void endGame() {
		
	}
}
