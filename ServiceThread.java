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

    Socket serverSocket;
    public ServiceThread(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            readFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            writeToClient = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));

            while (true) {
                clientSaying = readFromClient.readLine();
                System.out.println(clientSaying);
                writeToClient.write("Hello again socket");
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
