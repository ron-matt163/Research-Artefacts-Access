package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDatabase {
	
	private Connection con;

	public void connect() throws Exception {
		
		if(con != null) {
			//System.out.println("db occupied");
			return;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
				throw new Exception("Driver not found");
		}
		
		String url = "jdbc:mysql://localhost:3306/testing";
		con = DriverManager.getConnection(url, "root", "Rjmcheppad01");
		
		System.out.println("Connected "+ con);
		
	}
	
	public boolean check(String username, String password) throws SQLException {
		if(con != null) {
			String checkSql = "select * from user where username=? and password=?";
			PreparedStatement checkStatement = con.prepareStatement(checkSql);
			int col=1;
			checkStatement.setString(col++,username);
			checkStatement.setString(col++,password);
			
			ResultSet checkResult =  checkStatement.executeQuery();
				
			if(checkResult.next()) {
				String usr = checkResult.getString(1);
				String passwd = checkResult.getString(2);
				System.out.println(usr + " " + passwd);
				if(usr.equals(username) && passwd.equals(password)) {
					return true;
				}
				else {
					return false;
				}
				
			}
			
		}
		return false;
		
	}
	
	public String checkPosition(String username,String password) throws SQLException {
		
		if(con != null) {
			String checkSql = "select * from user where username=? and password=?";
			PreparedStatement checkStatement = con.prepareStatement(checkSql);
			int col=1;
			checkStatement.setString(col++,username);
			checkStatement.setString(col++,password);
			
			ResultSet checkResult =  checkStatement.executeQuery();
				
			if(checkResult.next()) {
				String position = checkResult.getString(3);
				//String passwd = checkResult.getString(2);
				System.out.println(position);
				if(position.equals("Researcher")) {
					return "Researcher";
				}
				else if(position.equals("Superior")){
					return "Superior";
				}
				
			}
			
		}
		return null;
		
	}
	

	
	public List<String> viewResearcherUsernames(String username) throws SQLException {
		List<String> dependant = new ArrayList<String>();
		if(con != null) {
			String checkSql = "select * from superior_researcher where superior=?";
			PreparedStatement checkStatement = con.prepareStatement(checkSql);
			checkStatement.setString(1,username);
			
			ResultSet checkResult =  checkStatement.executeQuery();
			
			while(checkResult.next()) {
				String researcher = checkResult.getString("researcher");
				//System.out.println(researcher);
				dependant.add(researcher);
			}
			checkResult.close();
			return dependant;
		}
		
		else {
			try {
				if(con == null) {
					connect();
				}
			} catch (Exception e) {
				System.out.println("couldnt connect in viewResearchers");
			}
			//System.out.println("Connection: "+con);
			String checkSql = "select * from superior_researcher where superior=?";
			PreparedStatement checkStatement = con.prepareStatement(checkSql);
			checkStatement.setString(1,username);
			ResultSet checkResult =  checkStatement.executeQuery();
			
			while(checkResult.next()) {
				String researcher = checkResult.getString("researcher");
				System.out.println(researcher);
				dependant.add(researcher);

			}
			checkResult.close();
			return dependant;
		}
		//return null;
	}
	
	
	public List<String> viewWorks(String username) throws SQLException {
		List<String> works = new ArrayList<String>();
		
		
		if(con == null) {
			try {
				connect();
				//System.out.println(con);
			} catch (Exception e) {
				System.out.println("Couldn't connect inside viewWorks");
			}
		}

		String checkSql = "select * from researcher_work where researcher=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		checkStatement.setString(1,username);
			
		ResultSet checkResult =  checkStatement.executeQuery();
		while(checkResult.next()) {
				String work = checkResult.getString("title");
				works.add(work);

			}
		checkResult.close();
		return works;
	}
	
	public String getSuperiorName(String username) throws SQLException {
		
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in getSuperiorName");
		}
		String nameSql = "select * from superior where username=?";
		PreparedStatement nameStatement = con.prepareStatement(nameSql);
		nameStatement.setString(1,username);
		ResultSet nameResult =  nameStatement.executeQuery();
		if(nameResult.next()) {
			String firstname = nameResult.getString("firstname");
			String lastname = nameResult.getString("lastname");
			String fullname = firstname + " " + lastname;
			nameResult.close();
			return fullname;
		}
		
		return null;
	}
	
	public String getResearcherName(String username) throws SQLException {
		
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in getResearcherName");
		}
		String nameSql = "select * from researchers where username=?";
		PreparedStatement nameStatement = con.prepareStatement(nameSql);
		nameStatement.setString(1,username);
		ResultSet nameResult =  nameStatement.executeQuery();
		if(nameResult.next()) {
			String firstname = nameResult.getString("firstname");
			String lastname = nameResult.getString("lastname");
			String fullname = firstname + " " + lastname;
			nameResult.close();
			return fullname;
		}
		
		return null;
	}
	
	public String getPaper(String work,String researcher) throws SQLException {
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in getPaper");
		}
		//System.out.println(work);
		String paperSql = "select * from researcher_work where title=? and researcher=?";
		PreparedStatement paperStatement = con.prepareStatement(paperSql);
		paperStatement.setString(1,work);
		paperStatement.setString(2,researcher);
		ResultSet paperResult =  paperStatement.executeQuery();
		//System.out.println("got here");
		if(paperResult.next()) {
			String paper = paperResult.getString("filename");
			paperResult.close();
			return paper;
		}
		
		return null;
	}
	
	public String getPaper(String work) throws SQLException {
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in getPaper");
		}
		//System.out.println(work);
		String paperSql = "select * from researcher_work where title=?";
		PreparedStatement paperStatement = con.prepareStatement(paperSql);
		paperStatement.setString(1,work);
		ResultSet paperResult =  paperStatement.executeQuery();
		//System.out.println("got here");
		if(paperResult.next()) {
			String paper = paperResult.getString("filename");
			paperResult.close();
			return paper;
		}
		
		return null;
	}
	
	public void addPaper(String researcher, String title, String filename,String year,String topic) throws SQLException {
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in addPaper");
		}

		
		String insertSql = "insert into researcher_work(researcher,title,filename,year,topic) values(?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		insertStatement.setString(1,researcher);
		insertStatement.setString(2,title);
		insertStatement.setString(3,filename);
		insertStatement.setString(4,year);
		insertStatement.setString(5,topic);
		
		insertStatement.executeUpdate();
		insertStatement.close();
		
		
	}
	
	public boolean advisorApproval(String username) throws SQLException {
		
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in getSuperiorName");
		}
		String checkSql = "select * from researchers where username=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		checkStatement.setString(1,username);
		ResultSet checkResult =  checkStatement.executeQuery();
		if(checkResult.next()) {
			boolean advisorApproval = checkResult.getBoolean("AdvisorApproval");
			checkResult.close();
			return advisorApproval;
		}
		
		return true;
		
	}
	
	public List<String> getYears() throws SQLException {
		List<String> years = new ArrayList<String>();
		
		
		if(con == null) {
			try {
				connect();
				//System.out.println(con);
			} catch (Exception e) {
				System.out.println("Couldn't connect inside getYears");
			}
		}

		String checkSql = "select distinct year from researcher_work order by year";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		ResultSet checkResult =  checkStatement.executeQuery();
		while(checkResult.next()) {
				String year = checkResult.getString("year");
				years.add(year);

			}
		checkResult.close();
		return years;
	}
	
	public List<String> getTopics() throws SQLException {
		List<String> topics = new ArrayList<String>();
			
		if(con == null) {
			try {
				connect();
				//System.out.println(con);
			} catch (Exception e) {
				System.out.println("Couldn't connect inside getTopics");
			}
		}

		String checkSql = "select distinct topic from researcher_work order by topic";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		ResultSet checkResult =  checkStatement.executeQuery();
		while(checkResult.next()) {
				String topic = checkResult.getString("topic");
				topics.add(topic);

			}
		checkResult.close();
		return topics;
	}
	
	public List<String> papersOfYear(String year,String topic) throws SQLException {
		List<String> works = new ArrayList<String>();
		if(con == null) {
			try {
				connect();
				//System.out.println(con);
			} catch (Exception e) {
				System.out.println("Couldn't connect inside viewWorks");
			}
		}
		PreparedStatement checkStatement;
		if(year.equals("All")) {
			String checkSql = "select * from researcher_work where topic=?";
			checkStatement = con.prepareStatement(checkSql);
			checkStatement.setString(1, topic);
		}
		else {
			String checkSql = "select * from researcher_work where year=? and topic=?";
			checkStatement = con.prepareStatement(checkSql);
			checkStatement.setString(1,year);
			checkStatement.setString(2,topic);
		}

			
		ResultSet checkResult =  checkStatement.executeQuery();
		while(checkResult.next()) {
				String work = checkResult.getString("title");
				works.add(work);

			}
		checkResult.close();
		return works;
	}
	
	public void registerUser(String username,String password,String category,String firstname,String lastname) throws SQLException {
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in registerUser");
		}

		
		String insertSql = "insert into user(username,password,position) values(?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		insertStatement.setString(1,username);
		insertStatement.setString(2,password);
		insertStatement.setString(3,category);
		insertStatement.executeUpdate();
		insertStatement.close();
		
		String insSql;
		if(category.equals("Researcher")) {
			insSql = "insert into researchers(firstname,lastname,username) values(?,?,?)";
			PreparedStatement insStatement = con.prepareStatement(insSql);
			insStatement.setString(1,firstname);
			insStatement.setString(2,lastname);
			insStatement.setString(3,username);
			insStatement.executeUpdate();
			insStatement.close();
		}
		
		else if(category.equals("Superior")) {
			insSql = "insert into superior(firstname,lastname,username) values(?,?,?)";
			PreparedStatement insStatement = con.prepareStatement(insSql);
			insStatement.setString(1,firstname);
			insStatement.setString(2,lastname);
			insStatement.setString(3,username);
			insStatement.executeUpdate();
			insStatement.close();
		}
	}
	
	public List<String> getSpareResearchers() throws SQLException {
		List<String> usr1 = new ArrayList<String>();
		List<String> usr2 = new ArrayList<String>();
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in registerUser");
		}
		
		String checkSql = "select username from researchers";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		ResultSet checkResult =  checkStatement.executeQuery();
		while(checkResult.next()) {
				String usr = checkResult.getString("username");
				usr1.add(usr);
			}
		checkResult.close();
		
		String chkSql = "select researcher from superior_researcher";
		PreparedStatement chkStatement = con.prepareStatement(chkSql);
		ResultSet chkResult =  chkStatement.executeQuery();
		while(chkResult.next()) {
				String usr = chkResult.getString("researcher");
				usr2.add(usr);
			}
		chkResult.close();
		usr1.removeAll(usr2);
		
		for(String user:usr1) {
			System.out.println(user);
		}
		
		
		return usr1;
	
	} 
	
	public void addResearcher(String superior, String researcher) throws SQLException {
		try {
			if(con == null) {
				connect();
			}
		} catch (Exception e) {
			System.out.println("couldnt connect in addResearcher");
		}
		
		String insertSql = "insert into superior_researcher(superior,researcher) values(?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		insertStatement.setString(1,superior);
		insertStatement.setString(2,researcher);
		insertStatement.executeUpdate();
		insertStatement.close();
	
	}
}
