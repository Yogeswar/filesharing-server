package Scomm;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class DsmClient {

	Socket soc;
	//PrintWriter toserver;
	OutputStream toserver;
	//InputStream in;
	public DsmClient(byte[] buf, String ip)
		{
		
		try
			{
			
			// server is listening on this port			
			soc = new Socket(ip,5000);			
			toserver=new BufferedOutputStream(soc.getOutputStream());
			
		//	in = soc.getInputStream();

			// int len=0;
			 //byte[] buf=new byte[1000];
			  //       while ((len = in.read(buf))>=0) {
			               //serverinput.write(buf, 0, len);

			toserver.write(buf, 0, buf.length);
			    //     }
			toserver.close();
	//		in.close();
			}
		catch(Exception ex)
			{
			System.out.println("Error in the code : " + ex.toString());
			}
		}

	public static void main(String str[])
		{	
		//client clientobj = new client();
		}

}
