package IK1203.task2;

import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        File file = new File("C:\\Users\\astro\\IdeaProjects\\IK1203\\src\\IK1203\\task2\\out.txt");
        file.getParentFile().mkdirs();
        while(true){
            try{
                Socket socket = serverSocket.accept(); socket.setSoTimeout(5000);
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter clientOut = new PrintWriter(file);

                String x = "HTTP/1.1 200 OK\r\n"; StringBuilder sb = new StringBuilder(x).append("\r\n");
                while((x = clientIn.readLine()) != null && x.length() !=0){
                    sb.append(x).append("\r\n");
                }
                System.err.println(sb.toString());
                clientOut.print(sb.toString());
                clientOut.flush();
                socket.close(); clientIn.close(); clientOut.close();
            } catch(IOException e){
                System.err.println(e);
            }
        }
    }
}
