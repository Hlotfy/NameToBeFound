package cz.kuasta.db;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.kuasta.items.Equipment;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Item.Quality;
import cz.kuasta.items.inventory.ListInventory;


/**
 * An interface for communication between the library DB and the client.
 * @author Matrin Vostal
 * @version 1.0
 * @since 1.0
*/
public class DB{
			
	/** The access point to your database, for example with MySQL, you use the IP, user login and password.*/
	private String conectionName;
	/**Name of the database connector class.*/
	private String connectorName;
		
	/** Basic constructor that includes the JDBC Database.*/
	public DB(){
		this.conectionName = "jdbc:sqlite:data/ServerDB.db";
		this.connectorName = "org.sqlite.JDBC";
	}
	
	/** Advanced DB constructor.<br>
	 * If you want to change the DB type, you should also override connection methods with the respective commands and change respective methods.*/
	public DB(String connectionName, String connectorName){
		this.conectionName = connectionName;
		this.connectorName = connectorName;
	}
	
	//DB methods
	public void executeSql(String sqlStmt){
		Connection connection = null;
		try{
			Class.forName(connectorName);
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlStmt);
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e.getMessage());
		}finally{
		    try{
		    	if(connection != null)
		    		connection.close();
		    }catch(SQLException e){
		        System.err.println(e);
		    }
		}
	}	
	public void executeSql(ArrayList<String> sqlStmt){
		Connection connection = null;
		try{
			Class.forName(connectorName);
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			for(String s : sqlStmt){
				statement.executeUpdate(s);
			}
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e.getMessage());
		}finally{
		    try{
		    	if(connection != null)
		    		connection.close();
		    }catch(SQLException e){
		        System.err.println(e);
		    }
		}
	}
	public ArrayList<UserData> getUsers() throws SQLException{
		ArrayList<UserData> data = new ArrayList<UserData>();
		
		try(
				Connection connection = DriverManager.getConnection(conectionName);
				PreparedStatement statement = connection.prepareStatement("select * from Users");
				ResultSet rsSet = statement.executeQuery();
		){
			while(rsSet.next()){
				UserData userData = new UserData();
				userData.login = rsSet.getString("login");
				userData.password = rsSet.getString("password");
				userData.email = rsSet.getString("email");
				userData.accesLevel = rsSet.getInt("accesLevel");
				userData.characterId = rsSet.getInt("characterId");
				
				data.add(userData);
			}
		}
		
		return data;
	}
	/**
	 * @param sqlStmt - string representing the statement for execution
	 * @return resultSet - Instance of ResultSet which contains statement results.
	 * @exception - 
	*/
	/*public TableData executeQuerry(String sqlStmt){		
		try(
				Connection connection = DriverManager.getConnection(conectionName);
				Statement statement = connection.prepareStatement(sqlStmt);
				ResultSet rsSet = statement.executeQuery(sqlStmt);
		){
			if(sqlStmt.contains("from Users where")){
				UserData userData = new UserData();
				userData.login = rsSet.getString("login");
				userData.password = rsSet.getString("password");
				userData.email = rsSet.getString("email");
				userData.accesLevel = rsSet.getInt("accesLevel");
				userData.characterId = rsSet.getInt("characterId");
				
				return userData;
			}else if(sqlStmt.contains("from Users")){
				ArrayList<UserData>
			}
		}
		
		try {
			
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlStmt);
		}catch(SQLException){
		    System.err.println(e.getMessage());
		}finally{
			try{
		    	if(connection != null)
		    		connection.close();
		    }catch(SQLException e){
		        System.err.println(e);
		    }
		}
		
		return resultSet;
	}*/	
	
	//added methods
	/**
	 * @throws SQLException 
	 * 
	*/
	
	/*public Item getItem(int id, int amount) throws SQLException{
		Item result = null;
		String sqlStmt = null;
		ResultSet rsSet = null;
				
		
		if(1<id && id<200){
			
			sqlStmt = "select * from Items where id=" + id;
			rsSet = executeQuerry(sqlStmt);	
					 
			result = new Item();
					
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
				
		} else if(200<id && id<400){
				
			sqlStmt = "select * from Eqipment where id=" + id;
			rsSet = executeQuerry(sqlStmt);
			
			result = new Equipment();
					
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
			((Equipment) result).setDurability(rsSet.getInt("durability"));
			((Equipment) result).setMaxDurability(rsSet.getInt("maxDurability"));
			((Equipment) result).setSlot(valueToSlot(rsSet.getInt("slot")));
			((Equipment) result).setStats(getStats(rsSet.getInt("stats")));				
		} else if(200<id && id<400){	
			
			sqlStmt = "select * from Recipes where id=" + id;
			rsSet = executeQuerry(sqlStmt);
				
			result = new Recipe();
				
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
			((Recipe) result).setDescription(rsSet.getString("description"));
			((Recipe) result).setMachine((Class<? extends Machine>) rsSet.getObject("requiredMachine"));
				
			//adding requiredItems
			ArrayList<cz.kuasta.items.crafting.Slot> tmp = new ArrayList<cz.kuasta.items.crafting.Slot>();
			ResultSet rsSetTmp = executeQuerry("select * from SlotValues where id=" + rsSet.getInt("requiredItems"));
			int i = 2;
			
			while(rsSetTmp.getInt(i) != (Integer) null){
				cz.kuasta.items.crafting.Slot slot = new cz.kuasta.items.crafting.Slot(
						rsSetTmp.getInt(i),
						rsSetTmp.getInt(i+1)
					);
				tmp.add(slot);
				i += 2;
			}
				
			((Recipe) result).setRequiredItems(tmp);
				
			//setting tool IDs
			ArrayList<Class<? extends Tool>> tmp2 = new ArrayList<Class<? extends Tool>>();
			i = 2;
			rsSetTmp = executeQuerry("select * from ToolIDs where id=" + rsSet.getInt("requiredTools"));
			while(rsSetTmp.getObject(i) != null){
				tmp2.add((Class<? extends Tool>) rsSetTmp.getObject(i));
				i++;
			}
				
			//setting products
			tmp = new ArrayList<cz.kuasta.items.crafting.Slot>();
			i = 2;
			rsSetTmp = executeQuerry("select * from SlotValues where id=" + rsSet.getInt("products"));
			while(rsSetTmp.getInt(i) != (Integer) null){
				cz.kuasta.items.crafting.Slot slot = new cz.kuasta.items.crafting.Slot(
						rsSetTmp.getInt(i),
						rsSetTmp.getInt(i+1)
					);
				tmp.add(slot);
				i += 2;
			}	
		} else if(true){ // make other table slot if necessary
			
		}	
		
		return result;
	}*/
	
	private boolean loginTaken(String name){
		Connection connection = null;
		ResultSet rsSet = null;
		boolean result = false;
		
		try{
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			rsSet = statement.executeQuery("select * from Users where login='" + name + "'");
				
			if(rsSet.isBeforeFirst())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
				rsSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	private boolean emailTaken(String email){
		Connection connection = null;
		ResultSet rsSet = null;
		boolean result = false;
		
		try{
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			rsSet = statement.executeQuery("select * from Users where email='" + email + "'");
		
			if(rsSet.isBeforeFirst())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
				rsSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public UserData getUserData(String login){
		Connection connection = null;
		ResultSet rsSet = null;
		UserData result = null;
		
		try{
				connection = DriverManager.getConnection(conectionName);
				PreparedStatement statement = connection.prepareStatement("select * from Users where login=(?)");
				statement.setString(1, login);
				rsSet = statement.executeQuery();
				
				if(rsSet != null){
					result = new UserData();
					result.login = rsSet.getString("login");
					result.password = rsSet.getString("password");
					result.email = rsSet.getString("email");
					result.accesLevel = rsSet.getInt("accesLevel");
					result.characterId = rsSet.getInt("characterId");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
				rsSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@SuppressWarnings("unchecked")
	public CharacterData getCharacterData(int characterId){
		CharacterData result = null;
		Connection connection = null;
		ResultSet rsSet = null;
		
		try{
				connection = DriverManager.getConnection(conectionName);
				PreparedStatement statement = connection.prepareStatement("select * from Characters where characterId=(?)");
				statement.setInt(1, characterId);
				rsSet = statement.executeQuery();
				
				if(rsSet != null){
					result = new CharacterData();
					result.charId = characterId;
					result.title = rsSet.getString("title");
					result.name = rsSet.getString("name");
					result.level = rsSet.getInt("level");
					result.stats = rsSet.getObject("stats", cz.kuasta.items.Stats.class);
					result.inventory = rsSet.getObject("inventory", ListInventory.class);
					result.equip = (HashMap<Slot, Equipment>) rsSet.getObject("equip", HashMap.class);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
				rsSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	/**Need to be remade or used whatsoever
	 * @throws SQLException */
	public int addUser(String login, String password, String email, int accountLevel){
		//int result = (Integer) null;
		String stmt = "";
		boolean loginTaken = loginTaken(login);
				
		if(loginTaken == false){
			Pattern loginPatern = Pattern.compile("[a-zA-Z0-9]{4,20}");
			Matcher loginMatcher = loginPatern.matcher(login);
			
			Pattern passwordPatern = Pattern.compile("[a-zA-Z0-9]{6,30}");
			Matcher passwordMatcher = passwordPatern.matcher(password);
			
			Pattern emailPatern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher emailMatcher = emailPatern.matcher(email);
			
			
			if(emailTaken(email) == false){
				if (emailMatcher.find() == true && loginMatcher.find() == true  && passwordMatcher.find() == true && loginTaken == false) {
					stmt = "insert into Users values('" + login + "','" + password + "','" + email + "'," + accountLevel + ",0)";
					System.out.println(stmt);
					executeSql(stmt);
					return 1; //everything ok, email or name not taken
				}else
					return 2; //pattern doesnt match
			}else
				return 3; // email taken
		}else
			return 0; //name taken
		//return result;
	}
	public void addItem(){}
	

	public static Quality valueToQuality(int i){
		Quality result = null;
		
		switch(i){
			case 1:
				result = Quality.POOR;
				break;
			case 2:
				result = Quality.COMMON;
				break;
			case 3:
				result = Quality.UNCOMMON;
				break;
			case 4:
				result = Quality.RARE;
				break;
			case 5:
				result = Quality.EPIC;
				break;
			case 6:
				result = Quality.MYTHIC;
				break;
			case 7:
				result = Quality.LEGENDARY;
				break;
		}
		
		return result;
	}
	public static Slot valueToSlot(int i){
		Slot result = null;
		
		switch(i){
			case 1:
				result = Slot.HEAD;
				break;
			case 2:
				result = Slot.SHOULDERS;
				break;
			case 3:
				result = Slot.CHEST;
				break;
			case 4:
				result = Slot.WRIST;
				break;
			case 5:
				result = Slot.HANDS;
				break;
			case 6:
				result = Slot.BELT;
				break;
			case 7:
				result = Slot.LEGS;
				break;
			case 8:
				result = Slot.BOOTS;
				break;
			case 9:
				result = Slot.MAINHAND;
				break;
			case 10:
				result = Slot.OFFHAND;
				break;
			case 11:
				result = Slot.ONEHAND;
				break;
			/*case 12:
				result = Slot.TWOHAND;
				break;*/
			case 13:
				result = Slot.RING;
				break;
		}
		
		return result;
	}

	/***/
	public void dbEntriesToTxt(){
		PrintWriter pw;
		try {
			pw = new PrintWriter("data/savedEntries.txt");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/savedEntries.txt", true))){
			
			ArrayList<UserData> userData = getUsers(); 
			
			bw.write("Users");
			bw.newLine();
			bw.write("---------------------------------------------------------------------------------------------------------");
			bw.newLine();

			for(UserData ud : userData){
				String result = "| login = " + ud.login + 
						" password = " + ud.password + 
						" email = " + ud.email + 
						" acces Level = " + ud.accesLevel +
						" charId = " + ud.characterId;
				bw.write(result);
				bw.newLine();
			}
				
			bw.write("---------------------------------------------------------------------------------------------------------");
			bw.flush();
		}catch (Exception e){
			System.err.println("Error working with text file");
		}
	
		/*try {
			pw = new PrintWriter("data/serverLog.txt");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter bwr = new BufferedWriter(new FileWriter("data/serverLog.txt", true))){
			bwr.write(NameToBeFoundServer.serverLog);
			bwr.flush();
		}catch (Exception e){
			System.err.println("Error working with text file");
		}*/
	}
}