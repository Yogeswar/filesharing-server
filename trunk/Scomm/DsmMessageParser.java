
import DataManager.Node;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.Arrays;
import Scomm.MsgAction;
import Scomm.ServerListener;
import utils.Convert;
import DataManager.Node;


public class DsmMessageParser implements ServerListener{
	
	private byte[] data;
	private Node iptable; 
	private MsgAction action;
	private DsmServer server;
	private ServerSocket Server;
	private String recvIP;
	private Convert conv;
	
	// Constants
	  public static final byte CMD_REGISTER				= 9;
	  public static final byte CMD_UPDATE				= 1;
	  public static final byte CMD_DISCONNECT	        = 10;
	  	
	public DsmMessageParser()
	{
		startServer();
		conv = new Convert();
	}
	
	
	public DsmMessageParser(byte[] incoming)
	{
		startServer();
		this.data = incoming;
	}
	
	
	public void startServer()
	{
		
		try {
			server = new DsmServer(5001);
			server.registerListener(this);
			new Thread(server).start();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void addActionProvider(MsgAction ac)
	{
		this.action = ac;
	}
	
	public void serverNotify(byte[] data, String ip)
	{
		this.data = data;
		this.recvIP = ip;
		this.parsedata();
	}
	
	
	public void parsedata()
	{
		byte msgType = this.data[0];
		this.data = Arrays.copyOfRange(this.data, 1, this.data.length);
		switch(msgType)
		{
			case DsmMessageParser.CMD_REGISTER:
				this.parseRegister();
				
				break;
			case DsmMessageParser.CMD_UPDATE:
				this.parseUpdate();
				break;
			case DsmMessageParser.CMD_DISCONNECT:
				this.parseDisconnect();
				break;
			default:
				break;
				
		}
	}
	
	private void parseRegister(){
		
		Node obj = (Node) conv.toObject(this.data);
		this.action.Register(obj);
		
	}
		
	private void parseUpdate()
	{
		try 
		{
		    ByteArrayInputStream bis = new ByteArrayInputStream(this.data);
		    ObjectInputStream ois = new ObjectInputStream (bis);
		    iptable = (Node) ois.readObject();
		    this.action.updateClientTable(iptable);
		} 
		catch(Exception e)
		{
			//Exception code
		}
	}
	
	private void parseDisconnect()
	{
		this.action.disconnect(this.recvIP);
	}
	
}

