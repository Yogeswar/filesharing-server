

/*
 * reference: http://scr4tchp4d.blogspot.com/2008/07/object-to-byte-array-and-byte-array-to.html 
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Scomm.*;

public class Convert {

	public byte[] toByteArray (Object obj)
	{
	  byte[] bytes = null;
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	  try {
	    ObjectOutputStream oos = new ObjectOutputStream(bos); 
	    oos.writeObject(obj);
	    oos.flush(); 
	    oos.close(); 
	    bos.close();
	    bytes = bos.toByteArray ();
	  }
	  catch (Exception ex) {
	    //TODO: Handle the exception
	  }
	  return bytes;
	}
	    
	public Object toObject (byte[] bytes)
	{
	  Object obj = null;
	  try {
	    ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
	    ObjectInputStream ois = new ObjectInputStream (bis);
	    obj = ois.readObject();
	  }
	  catch (Exception ex) {
	    //TODO: Handle the exception
		  ex.printStackTrace();
	  }
	  
	  return obj;
	}
}
