package IK1203.task3;

import java.net.*;
import java.io.*;

public class HTTPAsk {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = (args.length == 0)? new ServerSocket(8888) :  new ServerSocket(Integer.parseInt(args[0]));
        String host = null, port = null, string = null, response;
        while(true){
            try{
                Socket socket = serverSocket.accept(); socket.setSoTimeout(5000);
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream clientOut = new DataOutputStream(socket.getOutputStream());

                String[] xs = clientIn.readLine().split("[ =&?/]");

                if(xs[2].equals("ask")){
                    for(int i = 0; i<xs.length; i++){
                        switch (xs[i]) {
                            case "hostname":
                                host = xs[i++ + 1];
                                break;
                            case "port":
                                port = xs[i++ + 1];
                                break;
                            case "string":
                                string = xs[i++ + 1];
                                break;
                        }
                    }
                    try{
                        response = tcpclient.TCPClient.askServer(host,Integer.parseInt(port),string);
                        String header = "HTTP/1.1 200 OK\r\n\r\n";
                        clientOut.writeBytes(header + response +"\r\n");
                    } catch(Exception e){
                        clientOut.writeBytes("HTTP/1.1 404 Not found\r\n");
                    }
                }
                else{
                    clientOut.writeBytes("HTTP/1.1 400 Bad request\r\n");
                }

                socket.close(); clientIn.close(); clientOut.close();
            } catch(IOException e){
                System.err.println(e);
            }
        }
    }
}
