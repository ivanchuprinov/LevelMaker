package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldSector;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Once this is all done, we can clean it up.
public class Controller {
	private static HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	private ArrayList<WorldSector> sectors = new ArrayList<WorldSector>();
	private int playerID = 0;
	private World world;

	public Controller(World world)
	{
		this.world = world;

		WorldSector[][] sectors = world.getSectors();
		for(int i = 0; i<sectors.length; i++)
		{
			for(int j = 0; j<sectors[i].length; i++)
			{
				this.sectors.add(sectors[i][j]);
			}
		}
	}

	public void killPlayer(int pID) {

		Player p = players.remove(pID);

		sectors.get(p.getSectorID()).removePlayer(players.get(pID));

	}

	@SneakyThrows
	public void newPlayer(int sectorID) {
		++playerID;
		WorldSector sector = sectors.get(sectorID);
		Player p = new Player(playerID, sector);
		p.setSectorID(sectorID);
		players.put(playerID, p);
		sector.addPlayer(p);
	}

	/* The game is supposed to be started each time there are enough players */
	public void startGame(WorldSector s)
	{

		while(s.gameReady) {
			if (s.getPlayerCount() > 1)
			{
				for (int pIndex = 0; pIndex < s.getPlayers().size(); pIndex++)
				{
					Player p = s.getPlayers().get(pIndex);
					p.move();

					if(!p.isAlive())
					{
						killPlayer(p.getPlayerID());
						int newSize = s.getPlayerCount();
						if (0 < newSize && newSize < 2)
						{
							Player winner = s.getPlayers().get(0);
							paintPixel(winner.getPlayerID(), winner.getSectorID());
						}
					}
				}
			}
		}
		endGame(s);
	}

	public void paintPixel(int pID, int sID)
	{
		System.out.println("Player " + pID + " gets to paint the pixel of their choosing"
				+ " in the sector #" + sID);
	}


	private void endGame(WorldSector s) {
		s.removeAllPlayers();
		s.loadQueuedPlayers();
		if(s.gameReady)
			startGame(s);
		else
			waitForPlayers(s);
	}

	private void waitForPlayers(WorldSector s)
	{
		try
		{
			while (!s.gameReady)
				Thread.sleep(1);
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
	}

	public Player getPlayer(int pID) {
  		return players.get(pID);
	}

	public void runGameLoop() {
		System.out.println("Game Loop has been called.");
		while(true) {
			for(int i = 0; i < sectors.size(); ++i) { // repeat for each sector
				if(sectors.get(i).getPlayerCount() > 1) { // while a game is active in that sector
					for(Player p : players.values()) {

					}
				}
			}
		}
	}
}
