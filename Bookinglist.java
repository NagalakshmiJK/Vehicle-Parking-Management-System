import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Bookinglist {

	public void Bookingdata() throws SQLException {
		String url = "jdbc:mysql://Localhost:3306/parkingsystem";
		String username = "root";
		String password = "Kannan22";
		Connection con = DriverManager.getConnection(url, username, password);
//		String sql = "create table Bookinglist(recordId int,vehicleno varchar(20),slotId int,CheckInTime time,CheckOutTime time,amount int , stabletime int)";
//		String recorddelete = "alter table bookinglist modify stabletime varchar(5)";
//		PreparedStatement st = con.prepareStatement(recorddelete);
//		int val = st.executeUpdate();
		
		String sql = "select * from bookinglist";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		boolean bookingrecord = false;
		while(rs.next()) {
			String vehicleno = rs.getString("vehicleno");
			int slotId = rs.getInt("slotId");
			Time checkintime = rs.getTime("CheckInTime");
			Time checkout = rs.getTime("CheckOutTime");
			int pay = rs.getInt("amount");
			String parktotaltime = rs.getString("stabletime");
			System.out.println(vehicleno+" | "+slotId+" | "+checkintime+" | "+checkout+" | "+pay+" | "+parktotaltime);
		}
		if(!bookingrecord)
			System.out.println("No Booking Any Cars");
		con.close();
	}

}
