package Connection;
import java.sql.*;
import java.util.Scanner;
//Rahul Srinivas Desaboina
public class Movies_sqlite {
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:movies.db");
		
		if(con!=null) {
			
			System.out.println("Select option 1 for Creating a Table");
			System.out.println("Select option 2 for Inserting movies into a Table");
			System.out.println("Select option 3 for Updating a movie details of the Table");
			System.out.println("Select option 4 for Deleting a movie details of the Table");
			System.out.println("Select option 5 for Retrieving all movie details of the Table");
			System.out.println("Select option 6 for Retrieving specific movie based on the actor details of the Table");
			Scanner sc=new Scanner(System.in);
			int op=sc.nextInt();			
			if(op==1) {
			//Creating a Table
			Statement stmt=con.createStatement();
			stmt.executeUpdate("create table movies(movie text primary key,actor text,actress text,releaseyear int,director text)");
			System.out.println("Table Created");
			}
			
			else if(op==2) {
				//Inserting into a Table				
				PreparedStatement pstmt=con.prepareStatement("insert into movies values(?,?,?,?,?)");
				System.out.println("Enter Release Date of Movie");
				int releaseyear=sc.nextInt();
				System.out.println("Enter Movie Name");
				String movie=sc.next();
				System.out.println("Enter Actor Name");
				String actor=sc.next();
				System.out.println("Enter Actress Name");
				String actress=sc.next();
				System.out.println("Enter Director Name");
				String director=sc.next();
			
				pstmt.setString(1,movie);
				pstmt.setString(2,actor);
				pstmt.setString(3,actress);
				pstmt.setInt(4,releaseyear);
				pstmt.setString(5,director);
				pstmt.executeUpdate();	
				System.out.println("Successfully Inserted");
				
				
			}
			//Updating the movie details
			else if(op==3) {
				Statement stmt=con.createStatement();
				String sql="update movies set actress=30 where movie='venom'";
				int n=stmt.executeUpdate(sql);
				System.out.println(n+"records updated");
			}
			
			//Deleting the movie details
			else if(op==4) {
				Statement stmt=con.createStatement();
				String sql1="delete from movies where movie='venom'";
				int n=stmt.executeUpdate(sql1);
				System.out.println(n+"records deleted");
				
			}
			//Retrieving all movie details of the Table
			else if(op==5) {
				PreparedStatement pstmt=con.prepareStatement("select * from products");
				ResultSet rs=pstmt.executeQuery();
				System.out.println("movie name\t actor name\t actress name\t releasedate\t director name");
				System.out.println("------------------------------------------------------");
				while(rs.next())
				{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5));
				}
			}
			
			//Retrieving specific movie based on the actor details of the Table
			else if(op==6) {
				PreparedStatement pstmt=con.prepareStatement("select * from products where actor='TomHardy'");
				ResultSet rs=pstmt.executeQuery();
				System.out.println("movie name\t actor name\t actress name\t releasedate\t director name");
				System.out.println("------------------------------------------------------");
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5));
			}
			
			
			//If user chooses wrong option
			else {
				System.out.println("Please select option from given only");
			}
		}

		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
