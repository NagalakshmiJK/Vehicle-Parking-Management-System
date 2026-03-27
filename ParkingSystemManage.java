import java.sql.SQLException;
import java.util.Scanner;
public class ParkingSystemManage {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Checkin parkcheckin = new Checkin();
		CheckOut parkcheckout = new CheckOut();
		Bookinglist cardetails = new Bookinglist();
		System.out.println("Welcome to Parking Management System");
		while(true) {
			System.out.println("1.CheckIn \n2.CheckOut \n3.Booking Details");
			System.out.print("Enter your choice:");
			int ch = sc.nextInt();
			switch(ch) {
			case 1 : {
				parkcheckin.checkindetails();
				break;
				}
			case 2 : {
				parkcheckout.checkoutdetails();
				break;
				}
			case 3 : {
				cardetails.Bookingdata();
				break;
				}
			case 4 : {
				System.out.println("Thank you parking your car");
				return ;
			}
			}
		}
	}
	

}
