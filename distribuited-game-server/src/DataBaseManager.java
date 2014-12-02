

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
	
	private Connection db;
	private static DataBaseManager instance;
	
	private DataBaseManager() {
		this.initDataBase();
	}
	
	public static DataBaseManager getInstance() {
		if (DataBaseManager.instance == null) {
			DataBaseManager.instance = new DataBaseManager();
		}
		return DataBaseManager.instance;
	}
	
	private void initDataBase() {
		try {
			Class.forName("org.postgresql.Driver");
			this.db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgre", "postgres", "pfafveiou");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<Player>();
		try {
			
			Statement statement = this.db.createStatement();
			String sql = "select * from player";
			ResultSet result = statement.executeQuery(sql);			 
			while (result.next()) {
				Player player = new Player();
				player.setUserName(result.getString(Player.USERNAME));
				player.setRegionCode(result.getString(Player.REGIONCODE));
				player.setPoints(result.getInt(Player.POINTS));
				players.add(player);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return players;
	}
	
	public List<Region> getRegions() {
		List<Region> regions = new ArrayList<Region>();
		try {
			
			Statement statement = this.db.createStatement();
			String sql = "select * from regions";
			ResultSet result = statement.executeQuery(sql);			 
			while (result.next()) {
				Region region= new Region();
				region.setRegionNumber(result.getInt(Region.REGIONCODE));				
				regions.add(region);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return regions;
	}
	
	public void insertPlayer(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "insert into player values (" + player.getUserName() + "," + player.getRegionCode() + "," + player.getPoints() + ");";
			ServerManager.getInstance().addSql(player.getUserName(), sql);
			statement.execute(sql);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void updatePoints(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "update player set points=" + player.getPoints() + " where username=" + player.getUserName();
			ServerManager.getInstance().addSql(player.getUserName(), sql);
			statement.execute(sql);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void executeSql(String sql) {
		try {
			Statement statement = this.db.createStatement();			
			statement.execute(sql);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
