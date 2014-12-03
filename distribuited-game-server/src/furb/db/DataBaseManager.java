package furb.db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thrift.stubs.Player;

public class DataBaseManager {
		
	private static final String USERNAME = "user_name";
	private static final String AREA = "area";
	private static final String LAST_SAVED = "last_saved";
	private static final String LIFE_POINTS = "life";
	private static final String POSITION_X = "position_x";
	private static final String POSITION_Y = "position_y";
	
	private Connection db;
	private static DataBaseManager instance;
	
	private DataBaseManager() {
		this.initDataBase();
	}
	
	public static void instantiate() {
		instance = new DataBaseManager();
	}
	
	public static DataBaseManager getInstance() {
		if (instance != null)
			return instance;
		throw new RuntimeException();
	}
	
	public Player getPlayer(String userName) {
		Player player = null;
		try {
			Statement statement = this.db.createStatement();	
			String sql = "select * from player where " + USERNAME + " = '" + userName + "';";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				player = new Player();
				player.name = result.getString(USERNAME);
				player.last_saved = result.getLong(LAST_SAVED);
				player.life = result.getInt(LIFE_POINTS);
				player.area = result.getInt(AREA);
				int positionX = result.getInt(POSITION_X);
				int positionY = result.getInt(POSITION_Y);
				List<Integer> positions = new ArrayList<Integer>();
				positions.add(positionX);
				positions.add(positionY);
				player.position = positions;				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return player;
	}
	
	public void updatePlayer(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "update player set " + LIFE_POINTS + " = " + player.life + ", "
					+ POSITION_X 	+ " = " + player.position.get(0) + ", " + POSITION_Y 	+ " = " + player.position.get(1) + ", " + AREA + " = " + player.area  
					+ LAST_SAVED + " = " + new Date().getTime() + " where " + USERNAME + " = '"	+ player.name + "';"; 
											  
			statement.execute(sql);			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}		
	}
	
	public void insertPlayer(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "insert into player values ('" + player.name + "'," + player.life + "," + player.area + "," + player.position.get(0) +	"," 
							+ player.position.get(1) + "," + new Date().getTime() + ");";											  
			statement.execute(sql);			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}		
	}
	
	public void deletePlayer(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "delete from player where " +  USERNAME + " = '" + player.name + "';"; 
			statement.execute(sql);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	private void initDataBase() {
		try {
			Class.forName(SQLITE_FOR_CLASS);
			this.db = DriverManager.getConnection(SQLITE_URL);
			DatabaseMetaData dbm = this.db.getMetaData();			
			ResultSet tables = dbm.getTables(null, null, "player", null);
			if (!tables.next()) {
				this.createTables();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			Statement statement = this.db.createStatement();
			String createTable = "create table player (user_name varchar primary key,life numeric, area numeric,"
					+ "position_x numeric,position_Y numeric, last_saved integer)";	
			statement.execute(createTable);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
	}
	
	private static final String POSTGRESQL_FOR_CLASS = "org.postgresql.Driver";
	private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String POSTGRESQL_USER = "postgres";
	private static final String POSTGRESQL_PASSWORD = "pfafveiou";
	
	private static final String SQLITE_FOR_CLASS = "org.sqlite.JDBC";
	private static final String SQLITE_URL = "jdbc:sqlite:mine.db";
	
/*		public static void main(String[] args) {
			
			DataBaseManager.instantiate();
			
			Player player = new Player();
			player.name = "fabricio";
			player.last_saved = new Date().getTime();
			player.life = 30;
			List<Integer> positions = new ArrayList<Integer>();
			positions.add(30);
			positions.add(20);
			player.position = positions;
			
			DataBaseManager.getInstance().insertPlayer(player);
			Player playerOne = DataBaseManager.getInstance().getPlayer("william");
			Player playerTwo = DataBaseManager.getInstance().getPlayer("caique");
			
			playerTwo.life = 150;
			
			DataBaseManager.getInstance().updatePlayer(playerTwo);
			playerTwo = DataBaseManager.getInstance().getPlayer("caique");
			
	}*/

}
