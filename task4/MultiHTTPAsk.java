import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiHTTPAsk{

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = (args.length == 0)? new ServerSocket(8888) :  new ServerSocket(Integer.parseInt(args[0]));
        try{
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new myRunnable.ClientRunnable(socket)).start();
            }
        }
        catch(Exception e){
            System.err.println(e);
        }

    }
}

/*
  time.nist.gov	13
  java.lab.ssvl.kth.se	13
  whois.iis.se	43
  whois.internic.net	43
  java.lab.ssvl.kth.se	7
  java.lab.ssvl.kth.se	9
  java.lab.ssvl.kth.se	19

  sample:
  localhost:8888/ask?hostname=whois.iis.se&port=43&string=kth.se
  localhost:8888/ask?hostname=time.nist.gov&port=13
  localhost:8888/ask?hostname=java.lab.ssvl.kth.se.se&port=19
 */