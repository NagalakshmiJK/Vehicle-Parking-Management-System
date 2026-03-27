import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Scanner;
public class Checkin {
	static int recordid = 1;
	public void checkindetails() throws SQLException {
		Scanner sc = new Scanner(System.in);
		String url ="jdbc:mysql://Localhost:3306/parkingsystem";
		String username = "root";
		String password = "Kannan22";
		Connection con = DriverManager.getConnection(url,username, password);
		System.out.println("Enter your Vehicle no ");
		String vehiclenumber = sc.nextLine();
//		System.out.println("Enter your Check in time : ");
//		double checkintime = sc.nextDouble();
//		System.out.println("Enter your Check Out Time : ");
//		double checkouttime = sc.nextDouble();
//		String sql = "create table CheckIn(recordId int,vehicleno varchar(10),slotid int)";
//		String addcolsql = "alter table checkin Modify CheckInTime time";
//		PreparedStatement pst = con.prepareStatement(addcolsql);
//		int addcol = pst.executeUpdate();
//		System.out.println(addcol+"successfully add a new col");
		String sql ="select status,soltId from avaiableslot where status='available'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			String status = rs.getString("status");
			int slotno = rs.getInt("soltId");
			if(status.equalsIgnoreCase("available")) {
				LocalTime currenttime = LocalTime.now().withSecond(0).withNano(0);
				String addsql = "insert into CheckIn(vehicleno,slotId,CheckInTime) values(?,?,?)";
				PreparedStatement stadd = con.prepareStatement(addsql);
				stadd.setString(1, vehiclenumber );
				stadd.setInt(2,slotno);
				stadd.setTime(3, Time.valueOf(currenttime));
				int val = stadd.executeUpdate();
				
				String updatesql = "update avaiableslot set status='Occupied' where soltId=?";
				PreparedStatement pst = con.prepareStatement(updatesql);
				pst.setInt(1, slotno);
				int ans = pst.executeUpdate();
				System.out.println("successfully parked your car and your unique slotno "+slotno);
			}
		}
		else
			System.out.println("Not Available any slots You can come will be next time");
		con.close();
	}

}
