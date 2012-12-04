package Scomm;

import java.awt.Container;
import Scomm.DsmClient;
import Scomm.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Convert;



public class ServerController implements MsgAction
{

	private DsmMessageParser parser;
	private List<Node> iptable;
	private Convert conv;
	private DsmClient cl;
	
	public ServerController()
	{
		System.out.println("Server Starting...");
		parser = new DsmMessageParser();
		parser.addActionProvider(this);
		
		//Register to server
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerController();
	}

	@Override
	public void updateClientTable(Node st) {
		System.out.println("Update from " + st.getIp());
		Node temp;
		Iterator<Node> iterator = this.iptable.iterator();
		while(iterator.hasNext())
		{
			temp = iterator.next();
			if(temp.getIp().equals(st.getIp()))
			{
				iterator.remove();
				this.iptable.add(st);
				break;
			}
				
		}
		sendMessage();
	}

	public void Register(Node st){
		//Register the IP address
		//Register the mode
		System.out.print("Registering ");
		System.out.println(st.getIp());
		if(this.iptable == null)
		{
			this.iptable = new ArrayList<Node>();
		}
		this.iptable.add(st);
		sendMessage();
	}
	
	public void disconnect(String ip){
		Node temp;
		System.out.println("Disconnected : ");
		System.out.println(ip);
		Iterator<Node> iterator = this.iptable.iterator();
		while(iterator.hasNext())
		{
			temp = iterator.next();
			if(temp.getIp().equals(ip))
			{
				iterator.remove();
			}
				
		}
		sendMessage();
	}
	
	
	public void sendMessage()
	{
		Convert conv = new Convert();
		byte[] mainData = conv.toByteArray(this.iptable);
		byte[] data = new byte[mainData.length + 1];
		data[0] = 1;
		System.arraycopy(mainData, 0, data, 1, mainData.length);
		Iterator<Node> iterator = this.iptable.iterator();
		while(iterator.hasNext())
		{
			cl = new DsmClient(data, iterator.next().getIp());
				
		}
	}
	
	
	
}

