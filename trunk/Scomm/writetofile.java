//writes serialized contents to file "table"
package Scomm;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class writetofile {

public void writef(Object str){

String filename = "table";

FileOutputStream fos = null;
     ObjectOutputStream out = null;
     try
     {
    	 File ftarget=new File(filename);
			ftarget.createNewFile();
			
       fos = new FileOutputStream(filename);
       out = new ObjectOutputStream(fos);
       out.writeObject(str);
       out.close();
       
       
     }
     catch(IOException ex)
     {
       ex.printStackTrace();
    }
	}
}

