import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class slotchecking {

	public static void main(String[] args) throws SQLException {
		String url ="jdbc:mysql://Localhost:3306/parkingsystem";
		String username = "root";
		String password = "Kannan22";
		Connection con = DriverManager.getConnection(url, username, password);
//		String sql ="create table avaiableslot(soltId int primary key,floor int,slot int,status varchar(15))";
		int slotid=2001;
		for(int i=1;i<=2;i++) {
			for(int j=1;j<=2;j++) {
				String sql ="insert into avaiableslot values(?,?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, slotid);
				st.setInt(2, i);
				st.setInt(3, j);
				st.setString(4,"available");
				slotid++;
				int val = st.executeUpdate();
				System.out.println(val+"rows affected");
			}
		}
		con.close();
	}

}
