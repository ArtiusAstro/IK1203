import java.net.ServerSocket;
import java.net.Socket;
import t4.SomRunnable;

public class MultiHTTPAsk{

    public static void main(String[] args) {

        try{
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));

            while(true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new SomRunnable(clientSocket)).start();
            }
        }
        catch(Exception e){
            System.out.println("server Error");
        }

    }
}