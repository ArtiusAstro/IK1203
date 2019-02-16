package myRunnable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientRunnable implements Runnable {

    Socket socket;

    public ClientRunnable(Socket c){
        socket = c;
    }

    public void run(){
        String host = null, port = null, string = null, response;
        try{
            socket.setSoTimeout(5000);
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream clientOut = new DataOutputStream(socket.getOutputStream());

            String x = clientIn.readLine();
            String[] xs = x.split("[ =&?/]");

            if(xs[2].equals("ask")){
                for(int i = 0; i<xs.length; i++){
                    switch (xs[i]) {
                        case "hostname":
                            host = xs[i+++1];
                            break;
                        case "port":
                            port = xs[i+++1];
                            break;
                        case "string":
                            string = xs[i+++1];
                            break;
                    }
                }
                try{
                    assert port != null;
                    response = tcpclient.TCPClient.askServer(host,Integer.parseInt(port),string);
                    String header = "HTTP/1.1 200 OK\r\n\r\n";
                    clientOut.writeBytes(header + response +"\r\n");
                } catch(Exception e){
                    System.err.println(e);
                    clientOut.writeBytes("HTTP/1.1 404 Not found\r\n\r\n" + "404" + "\r\n");
                }
            }
            else{
                clientOut.writeBytes("HTTP/1.1 400 Bad request\r\n\r\n" + "400" + "\r\n");
            }

            socket.close(); clientIn.close(); clientOut.close();
        } catch(IOException e){
            System.err.println(e);
        }
    }
}