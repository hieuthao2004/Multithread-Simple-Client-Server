import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {
    private static ServerSocket listener;
    private static Socket serverSocket;
    private static BufferedWriter writeToClient;
    public static void main(String[] args) {
        try {
            listener = new ServerSocket(2301);
            System.out.println("Waiting for client connection...");
            int clientNth = 0;
            
            while (true) {
                serverSocket = listener.accept();
                System.out.println("Accept a client");
                
                writeToClient = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
    
                writeToClient.write("SERVER SAYS: YOU HAVE CONNECTED TO THE SERVER!");
                writeToClient.newLine();
                writeToClient.flush();

                ServiceThread st = new ServiceThread(serverSocket, ++clientNth);
                Thread newThread = new Thread(st);
                newThread.start();
            }
        } catch (Exception e) {
            System.out.println("Something is fked!");
        }
    }
}
