import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread {
    private static BufferedReader readFromServer;
    private static BufferedWriter writeToServer;
    private static BufferedReader inputFromClient;
    private static String sayingFromServer;
    private static String sayingFromClient;
    private static Socket clientSocket;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 2301);

            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            inputFromClient = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                sayingFromServer = readFromServer.readLine();
                System.out.println(sayingFromServer);
                System.out.println("Say something...");
                sayingFromClient = inputFromClient.readLine();
                writeToServer.write("CLIENT SAYS: " + sayingFromClient);
                writeToServer.newLine();
                writeToServer.flush();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
