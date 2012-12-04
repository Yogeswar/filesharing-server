/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import java.io.Serializable;

/**
 *
 * @author Yogi
 */
public class Node implements Serializable{
    
    private String ip;
    private String id;
    private boolean deferred = false;
    private int logicalTimestamp;
    
    public Node(String id, String ip, int timestamp){
        this.id = id;
        this.ip = ip;
        this.logicalTimestamp = timestamp;       
    }
    
    public Node(String id, String ip){
        this.id = id;
        this.ip = ip;
    }
    
    public void increment(){
        this.logicalTimestamp++;
    }
    
    public boolean isLatest(int ts){
        if(this.logicalTimestamp > ts){
            return false;
        }
        return true;
        
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeferred() {
        return deferred;
    }

    public void setDeferred(boolean deferred) {
        this.deferred = deferred;
    }

    public int getLogicalTimestamp() {
        return logicalTimestamp;
    }

    public void setLogicalTimestamp(int logicalTimestamp) {
        this.logicalTimestamp = logicalTimestamp;
    }        
    
}
