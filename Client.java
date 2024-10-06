import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private static BufferedWriter writeToServer = null;
    private static BufferedReader readFromServer = null;
    private static Socket clientSocket = null;
    private static String sayingFromServer;
    private static BufferedReader inputFromClient = null;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 3009);

            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            inputFromClient = new BufferedReader(new InputStreamReader(System.in));

            while (true) {  
                sayingFromServer = readFromServer.readLine();
                System.out.println(sayingFromServer);            
                System.out.println("Message...");
                String sentence = inputFromClient.readLine();  
                writeToServer.write(">>" + sentence);
                writeToServer.newLine();
                writeToServer.flush();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Something is fcked");
        }
    }
}
