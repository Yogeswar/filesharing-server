package Scomm;

import java.security.Key;





public interface MsgAction {
	
	
	public void disconnect(String ip);
	public void updateClientTable(Node st);
	public void Register(Node st);
}
