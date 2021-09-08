import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

  //create constructor that takes in port number
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

  }

  public static void main(String[] args)throws IOException{
    new Server(12000);
  }
}
