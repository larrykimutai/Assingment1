/**Client side of socket programming
 *
 * @author Larry Langat
 * @since 09/04/2021
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  /**
   * Constructor class that takes in port number and ip address
   *
   * @param port port number that server will use to connect to client
   * @param IpAddress IP Address of server*/
  public Client(String IpAddress, int port)throws IOException {

    //create a socket object that will make a connection with the server and accepts ip address and port number
    Socket socket = new Socket(IpAddress, port);

    //create scanner object to accept input from user that will be sent to server
    Scanner toServer = new Scanner(System.in);
    System.out.println("Connected to server");
    System.out.print("Enter Radius: ");
    double radius = toServer.nextDouble();
    System.out.print("Enter Height: ");
    double height = toServer.nextDouble();

    //use printWrite to be able to send user input to server via socket
    PrintWriter p = new PrintWriter(socket.getOutputStream(), true);
    //sends data to server socket
    p.println(radius);
    p.println(height);

    //create another scanner that will be used to accept data from server
    Scanner fromServer = new Scanner(socket.getInputStream());
    double radius_fromServer = fromServer.nextDouble();
    double height_fromServer = fromServer.nextDouble();
    double Volume = fromServer.nextDouble();

    //print to console
    System.out.print("Radius: " + radius_fromServer + "\nHeight: " + height_fromServer);
    System.out.format("\nVolume: %.2f", Volume);

    //close the connection
    socket.close();
    toServer.close();
    fromServer.close();
    p.close();

    System.out.println("\nConnection closed");
  }

  /**
   * Main class that calls client method
   *
   * @param args unused
   * @throws IOException in case ip address or port number is unknown
   * */
  public static void main(String[] args)throws IOException{
    new Client("localhost", 12000);
  }
}
