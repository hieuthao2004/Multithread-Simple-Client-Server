import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServiceThread implements Runnable {
    private static BufferedReader readFromClient;
    private static BufferedWriter writeToClient;
    private static String clientSaying;
    
    private Socket serverSocket;
    private int clientNth;

    public ServiceThread(Socket serverSocket, int clientNth) {
        this.serverSocket = serverSocket;
        this.clientNth = clientNth;
        log("Client number " + this.clientNth + " has connected to the Server at " +  this.serverSocket);
    }

    private static void log(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        try {
            readFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            writeToClient = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));

            while (true) {
                clientSaying = readFromClient.readLine();
                System.out.println("CLIENT #" + this.clientNth + " SAYS: " + clientSaying);
                writeToClient.write("SERVER SAYS: HELLO AGAIN SOCKET!");
                writeToClient.newLine();
                writeToClient.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
