import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class CheckIncheckout{
	public static void main(String[] arg) throws SQLException {
		String url = "jdbc:mysql://Localhost:3306/parkingsystem";
		String username ="root";
		String password="Kannan22";
		Connection connection = DriverManager.getConnection(url, username, password);
//		String sql ="create table parking(slotid int primary key auto_increment,vahicleno varchar(10),checkin time,checkout time,status varchar(15) default 'available')";
//		PreparedStatement statement = connection.prepareStatement(sql);
//		statement.executeUpdate();	
//		System.out.println("Successfully created your table");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the vehicle no : ");
		String vahicleno = sc.nextLine();
		System.out.println("Enter your checkin time : ");
		String intime = sc.nextLine();
		LocalTime checkin = LocalTime.parse(intime);
		System.out.println("Enter your checkout time : ");
		String outtime = sc.nextLine();
		LocalTime checkout = LocalTime.parse(outtime);
		long hours = Math.abs(Duration.between(checkout, checkin).toMinutes());
		int payamount = (int) hours *2;

		String insertquery = "insert into parking(vahicleno,checkin,checkout,status,payamount) values(?,?,?,?,?)";
		PreparedStatement pstinsert = connection.prepareStatement(insertquery);
		pstinsert.setString(1, vahicleno);
		pstinsert.setTime(2, Time.valueOf(checkin));
		pstinsert.setTime(3, Time.valueOf(checkout));
		pstinsert.setString(4,"Occupied");
		pstinsert.setInt(5,payamount);
				
		String updatequery = "select checkout,slotid from parking";
		PreparedStatement updatest=connection.prepareStatement(updatequery);
		ResultSet resultset = updatest.executeQuery();
		boolean updated=false;	
		while(resultset.next()) {
			Time checkouttime = resultset.getTime("checkout");
			LocalTime dbcheckout = checkouttime.toLocalTime();
			int slotno = resultset.getInt("slotid");
			if(dbcheckout.isBefore(checkin)) {
				String carcheckout = "update parkingdatastorage set status=? where checkout = ?";
				PreparedStatement caroutst = connection.prepareStatement(carcheckout);
				caroutst.setString(1, "Check Out Car");
				caroutst.setTime(2,Time.valueOf(dbcheckout));
				caroutst.executeUpdate();
				String update1="update parking set vahicleno=?,checkin=?,checkout=?,payamount=? where checkout=?";
				PreparedStatement pst = connection.prepareStatement(update1);
				pst.setString(1,vahicleno);
				pst.setTime(2, Time.valueOf(checkin));
				pst.setTime(3, Time.valueOf(checkout));
				pst.setInt(4, payamount);
				pst.setTime(5, checkouttime);
				pst.executeUpdate();
				updated=true;
				
				String listquery2="insert into parkingdatastorage(vahicleno, checkin, checkout,status,payamount) values(?,?,?,?,?)";
				PreparedStatement listst2=connection.prepareStatement(listquery2);
				listst2.setString(1, vahicleno);
				listst2.setTime(2, Time.valueOf(checkin));
				listst2.setTime(3,Time.valueOf(checkout));
				listst2.setString(4, "CheckIn Car");
				listst2.setInt(5, payamount);
				listst2.executeUpdate();
				System.out.println("successfully updated my query");
				break;
			}
		}			
		if(!updated){
		pstinsert.executeUpdate();
		String listquery="insert into parkingdatastorage(vahicleno, checkin, checkout,status,payamount) values(?,?,?,?,?)";
		PreparedStatement listst=connection.prepareStatement(listquery);
		listst.setString(1, vahicleno);
		listst.setTime(2, Time.valueOf(checkin));
		listst.setTime(3,Time.valueOf(checkout));
		listst.setString(4, "CheckIn Car");
		listst.setInt(5, payamount);
		listst.executeUpdate();
		System.out.println("successfully added in a record");
		}
		connection.close();
	}
}