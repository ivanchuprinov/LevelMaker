package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldSector;

import java.util.ArrayList;
import java.util.HashMap;

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

	public void newPlayer(int sectorID) {
		++playerID;
		WorldSector sector = sectors.get(sectorID);
		if(sector.getPlayerCount() == 0) {
			Player p = new Player(playerID, sector);
			p.setSectorID(sectorID);
			players.put(playerID, p);
			sector.addPlayer(p);
		}
		else
		{
			System.out.println("You've been added to the queue");
			System.wait(Forever);
		}
	}

	private void startGame()
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
							killPlayer(p.getPlayerID());
					}
				}
			}
		}
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



	private void endGame(int sectorID)
	{
		
	}

	public Player getPlayer(int pID) {
  		return players.get(pID);
	}
}
