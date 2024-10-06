import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverListening = null;
    private static Socket serverSocket = null;
    private static BufferedReader readFromClient = null;
    private static BufferedWriter writeToClient = null;
    public static void main(String[] args) {
        String sayingFromClient;
        try {
            
            serverListening = new ServerSocket(3009);
            System.out.println("Finding Nemo");
            serverSocket = serverListening.accept();
            System.out.println("Accept a client");
            writeToClient = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            readFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            writeToClient.write("Write something!");
            writeToClient.newLine();
            writeToClient.flush();
    
            while (true) {
                sayingFromClient = readFromClient.readLine();
                System.out.println(sayingFromClient);
                writeToClient.write("hello back there");
                writeToClient.newLine();
                writeToClient.flush();
            }

        } catch (Exception e) {
            System.out.println("Something is fcked");
        }
    }
}
