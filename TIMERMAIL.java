import java.awt.event.*;
import java.util.Scanner;
import javax.swing.Timer;
import org.apache.commons.mail.*;
public class TIMERMAIL{
	public static int counter;
	public static void main(String[] args) {
		//run with java 1.8 and less
		//Author: Ali Jadelaoun
		//-------------------------------------------------------------------------------------------
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the smtpServer:(outlook:smtp.office365.com|gmail:smtp.gmail.com|LAU:pod51013.outlook.com)");
		String smtpServer = scan.nextLine();
		int smtpPort = 587; // Use TLS port 587
		System.out.println("Enter your username(email):");
		String smtpUsername = scan.nextLine(); // Replace with your Gmail email address
		System.out.println("Enter your password:");
		String smtpPassword = scan.nextLine(); // Replace with your Gmail password
		String fromEmail = smtpUsername; // Replace with your Gmail email address
		System.out.println("Enter the email you want to send this message to:");
		String toEmail = scan.nextLine(); // Recipient's email address
		System.out.println("Enter a subject( optional):");
		String Subject = scan.nextLine();
		System.out.println("Enter message you want to send:");
		String Message = scan.nextLine();
		//now all credentials are entered by user
		//-------------------------------------------------------------------------------------------

		Timer timer = new Timer(1000,(e)->{
			if(counter>0) {
				counter--;
			}else {
				try {
					MultiPartEmail email = new MultiPartEmail();
					email.setStartTLSEnabled(true);
					email.setSubject(Subject);
					email.setHostName(smtpServer);
					email.setSmtpPort(smtpPort);
					email.setAuthentication(smtpUsername,smtpPassword);
					email.setFrom(fromEmail);
					email.setMsg(Message);
					email.addTo(toEmail);
					email.send();
				} catch (Exception ee) {
					ee.printStackTrace();
					System.out.println("Email not sent!");
				}
				counter--;
			}
		}
				);//timer instantiation ended
		System.out.println("Enter hours,minutes,seconds to send email after:");
		int hrs = scan.nextInt();
		int min = scan.nextInt();
		int sec = scan.nextInt();
		int delay = runTimerAfter(hrs, min, sec);
		counter= 0;
		timer.setInitialDelay(delay);
		timer.start();
		while(timer.isRunning()) {
			if (counter==-1) {
				timer.stop();
			}
		}
		scan.close();
	}	
	public static int runTimerAfter(int hours,int minutes,int seconds) {
		return  (hours*3600+minutes*60+seconds)*1000;
	}
}



