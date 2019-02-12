package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {

    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
        if(ToServer==null) return askServer(hostname, port);
        Socket clientSocket = new Socket(hostname, port);
        clientSocket.setSoTimeout(5000);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(ToServer+'\n');
        String x; StringBuilder answer = new StringBuilder();
        try {
            while ((x=inFromServer.readLine())!=null) {
                answer.append(x).append('\n');
            }
            clientSocket.close();
            return answer.toString();
        } catch(IOException e) {
            clientSocket.close();
            return answer.toString();
        }
    }

    public static String askServer(String hostname, int port) throws  IOException {
        Socket clientSocket = new Socket(hostname, port);
        clientSocket.setSoTimeout(5000);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String x; StringBuilder answer = new StringBuilder();
        final int MAX = 256; int c = 1;
        try {
            while ((x=inFromServer.readLine())!=null) {
                answer.append(x).append('\n');
                if(c++>MAX){
                    clientSocket.close();
                    return answer.toString();
                }
            }
            clientSocket.close();
            return answer.toString();
        } catch(IOException e) {
            clientSocket.close();
            return answer.toString();
        }
    }
}

