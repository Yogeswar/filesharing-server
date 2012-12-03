
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.io.IOUtils;
import Scomm.*;



public class DsmServer implements Runnable{
private ServerListener listener;
		
		public void registerListener(ServerListener sl)
		{
			this.listener = sl;
		}
	int serverPort=5001;
	Thread runningThread= null;
	ServerSocket serverSocket = null;
		
	
	public DsmServer(int port){
	        this.serverPort = port;
	    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.runningThread = Thread.currentThread();
		openServerSocket();
		while(true){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (Exception ex) {
            	System.out.println("Error in the code server : " + ex.toString());
                }
                
            
            new Thread(new WorkerRunnable(clientSocket, listener)).start();
		
        }
		
	}

	private void openServerSocket() {
		// TODO Auto-generated method stub
		 try {
	            this.serverSocket = new ServerSocket(this.serverPort);
	        } catch (Exception ex) {
	        	System.out.println("Error in the code server : " + ex.toString());
	        }
	}
	public static void main(String arg[]) throws UnknownHostException, IOException
	{
		DsmServer server = new DsmServer(5001);
		new Thread(server).start();
	}

}
