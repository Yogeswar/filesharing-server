import Scomm.*;
import utils.Convert;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class WorkerRunnable implements Runnable {
	Socket clientSocket = null;
    String serverText   = null;
    private ServerListener listener;
	InputStream is;
	PrintWriter toclient;

	public WorkerRunnable(Socket clientSocket, ServerListener sl) {
		// TODO Auto-generated constructor stub
		this.clientSocket = clientSocket;
        this.listener = sl;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
        try {
        	while(true)
    		{
        	String Ip1=clientSocket.getInetAddress().toString();
			String Ip = Ip1.substring(1);
			//System.out.println(Ip);
			is = new BufferedInputStream(clientSocket.getInputStream());
			
			byte[] bytes = IOUtils.toByteArray(is);
					

			listener.serverNotify(bytes, Ip);
			break;
    		}
            			
			clientSocket.close();
            
        } catch (Exception ex) {
        	System.out.println("Error in the code server : " + ex.toString());
        }
    }
		
	}


