/**Server side of socket programming
 *
 * @author Larry Langat
 * @since 09/04/2021
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

  /**
   * Constructor class that takes in port number
   *
   * @param port port number that server will use to connect to client
   */
  public Server(int port)throws IOException {

    //create server socket that will wait and listen for clients in given port
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Waiting for client...");

    //create socket that will be used to communicate with client
    Socket socket = serverSocket.accept();
    System.out.println("Connected");

    //create scanner that will be used to accept data from client
    Scanner scanner = new Scanner(socket.getInputStream());

    //store values to server
    double radius = scanner.nextDouble();
    double height = scanner.nextDouble();

    double vol = (Math.PI * (Math.pow(radius,2)) * height);

    //use print writer to send back info via socket
    PrintWriter p = new PrintWriter(socket.getOutputStream(), true);
    p.println(radius);
    p.println(height);
    p.println(vol);

    //close connection
    serverSocket.close();
    scanner.close();
    p.close();

    System.out.println("Connection closed");

  }

  /**
   * Main class that calls server method
   *
   * @param args unused
   * @throws IOException in case ip address or port number is unknown
   * */
  public static void main(String[] args)throws IOException{
    new Server(12000);
  }
}
