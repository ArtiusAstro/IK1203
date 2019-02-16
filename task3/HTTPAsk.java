import java.net.*;
import java.io.*;
import java.util.Arrays;

public class HTTPAsk {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = (args.length == 0)? new ServerSocket(8888) :  new ServerSocket(Integer.parseInt(args[0]));
        String host = null, port = null, string = null, response; int v = 0;
        while(true){
            try{
                System.out.println("looping "+v++);
                Socket socket = serverSocket.accept(); socket.setSoTimeout(5000);
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream clientOut = new DataOutputStream(socket.getOutputStream());

                String x = clientIn.readLine();
                String[] xs = x.split("[ =&?/]");
                System.out.println(x+'\n'+Arrays.toString(xs));


                if(xs[2].equals("ask")){
                    for(int i = 0; i<xs.length; i++){
                        System.out.println("index: "+i);
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
                        response = tcpclient.TCPClient.askServer(host,Integer.parseInt(port),string);
                        String header = "HTTP/1.1 200 OK\r\n\r\n";
                        clientOut.writeBytes(header + response +"\r\n");
                    } catch(Exception e){
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
}
