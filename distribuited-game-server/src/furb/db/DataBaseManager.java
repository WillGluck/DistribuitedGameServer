package furb.db;


import java.sql.Connection;
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
	private static final String LAST_SAVED = "last_saved";
	private static final String LIFE_POINTS = "life";
	private static final String POSITION_X = "position_x";
	private static final String POSITION_Y = "position_y";
	
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
	
	public Player getPlayer(String userName) {
		Player player = null;
		try {
			Statement statement = this.db.createStatement();			
			ResultSet result = statement.executeQuery("select * from player where " + userName + " = " + userName + ";");
			while (result.next()) {
				player = new Player();
				player.name = result.getString(USERNAME);
				player.last_saved = result.getLong(LAST_SAVED);
				player.life = result.getInt(LIFE_POINTS);
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
			String sql = "update player set " + LAST_SAVED 	+ " = " + new Date().getTime() + "," + LIFE_POINTS + " = " + player.life + ","
					+ POSITION_X 	+ " = " + player.position.get(0) + "," + POSITION_Y 	+ " = " + player.position.get(1) + "where " + USERNAME + "=" + player.name + ";"; 
											  
			statement.execute(sql);			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}		
	}
	
	public void insertPlayer(Player player) {
		try {
			Statement statement = this.db.createStatement();
			String sql = "insert into player values ('" + player.name + "'," + player.life + "," + player.position.get(0) +	"," + player.position.get(1) + "," + new Date().getTime();											  
			statement.execute(sql);			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}		
	}
	
	
	private void initDataBase() {
		try {
			Class.forName("org.postgresql.Driver");
			this.db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgre", "postgres", "pfafveiou");
			this.createTables();
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
			String createTable = "create table player (user_name varchar primary key,life numeric,position_x numeric,position_Y numeric,last_saved timestamp)";	
			statement.execute(createTable);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
	}

}
