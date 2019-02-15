package IK1203.task3;

import tcpclient.TCPClient;
import java.io.IOException;

public class HTTPAsk {
   public static void main( String[] args) {
       String host, serverIn = null; int port;

       try {
           host = args[0];
           port = Integer.parseInt(args[1]);
           if (args.length >= 3) {
               StringBuilder sb = new StringBuilder();
               boolean first = true;
               for (int i = 2; i < args.length; i++) {
                   if (first)
                       first = false;
                   else
                       sb.append(" ");
                   sb.append(args[i]);
               }
               serverIn = sb.toString();
           }
       }
       catch (Exception ex) {
           System.err.println("Usage: TCPAsk host port <data to server>");
           return;
       }
       try {
           String serverOut = TCPClient.askServer(host, port, serverIn);
           System.out.printf("%s:%d says:\n%s", host, port, serverOut);
       } catch(IOException ex) {
           System.err.println(ex);
       }
   }
}

