package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldSector;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Once this is all done, we can clean it up.
public class Controller {
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
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
	public void startGame()
	{

		while(gamesUnfinished())
		{
			for (int sID = 0; sID < sectors.size(); sID++)
			{
				WorldSector s = sectors.get(sID);
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
		}
	}

	public void paintPixel(int pID, int sID)
	{
		System.out.println("Player " + pID + " gets to paint the pixel of their choosing"
				+ " in the sector #" + sID);
	}

	private boolean gamesUnfinished()
	{
		for (int sID = 0; sID < sectors.size(); sID++)
		{
			WorldSector s = sectors.get(sID);
			if (s.getPlayerCount() > 1)
			{
				return true;
			}
		}
		return false;
	}



	private void endGame()
	{
		/* Thanos did nothing wrong */
	}

	public Player getPlayer(int pID) {
  		return players.get(pID);
	}
}
