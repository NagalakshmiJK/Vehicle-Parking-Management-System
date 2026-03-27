import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class CheckOut {
	public void checkoutdetails() throws SQLException {
		Scanner sc = new Scanner(System.in);
		String url="jdbc:mysql://Localhost:3306/parkingsystem";
		String username = "root";
		String password = "Kannan22";
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Enter your slot Id ");
		int checkoutslot = sc.nextInt();
		String createsql = "create table checkout(slotId int,Checkout time,amount int)";
//		String createsql = "alter table checkout modify Stablehours varchar(5)";
//		PreparedStatement st = con.prepareStatement(createsql);
//		int val = st.executeUpdate();
//		System.out.println(val +"table successfully you have to created");
		String checkoutcar = "select CheckInTime,slotid,vehicleno from checkin where slotid= ?";
		PreparedStatement pst = con.prepareStatement(checkoutcar);
		pst.setInt(1, checkoutslot);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			LocalTime currenttime = LocalTime.now().withSecond(0).withNano(0);
			int slotno = rs.getInt("slotId");
			String vehicleno = rs.getString("vehicleno");
			LocalTime checkintime = rs.getTime("CheckInTime").toLocalTime();
			long minutes =Math.abs(Duration.between(currenttime, checkintime).toMinutes());
			long time = minutes/60;
			String timeformat = time+"."+String.format("%02d",minutes%60);
			String checkoutdetail = "insert into checkout values(?,?,?,?)";
			PreparedStatement pstco = con.prepareStatement(checkoutdetail);
			pstco.setInt(1, slotno);
			pstco.setTime(2, Time.valueOf(currenttime));
			pstco.setLong(3, minutes*2);
			pstco.setString(4, timeformat);
			int ans = pstco.executeUpdate();
			
			// status changed 
			String changestatus = "update avaiableslot set status='available' where soltId = ?";
			PreparedStatement changest = con.prepareStatement(changestatus);
			changest.setInt(1, slotno);
			int changeupdate = changest.executeUpdate();

			//bookinglist add data 
			 String bookingdata  = "insert into bookinglist values(?,?,?,?,?,?)";
			 PreparedStatement details = con.prepareStatement(bookingdata);
			 details.setString(1, vehicleno);
			 details.setInt(2, slotno);
			 details.setTime(3, Time.valueOf(checkintime));
			 details.setTime(4, Time.valueOf(currenttime));
			 details.setLong(5, minutes*2);
			 details.setString(6, timeformat);
			 int addrecord = details.executeUpdate();
			 System.out.println(slotno +" successfully checkout in your car");
		}
		else
			System.out.println("Your slot Id is wrong ...\n check your slot id again");
		con.close();
	}
}
