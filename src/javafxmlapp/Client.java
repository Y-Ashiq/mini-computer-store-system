/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapp;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Youssef
 */

//here the client server is the supplier
public class Client implements Runnable{
    public void runClient() throws Exception{
       System.out.println("Starting receiving files....");
       byte[] bte = new byte[10]; // make the file size to be ready to send
       
       Socket socket = new Socket("localhost" , 5000);// make a socket for the supplier
       
       InputStream in = socket.getInputStream();// initialize the input to read the data 
       
              //initilize to write flie data and transfer it to another end user
       FileOutputStream fo = new FileOutputStream("Database/Products/Test.txt");
              //initialize the file to send from zero byte to the full size of the file
       in.read(bte, 0, bte.length);
       fo.write(bte, 0, bte.length);
       in.close();
       fo.close();
       socket.close();
        
      System.out.println("files received successfully");
    }
    
    public synchronized void run(){
        try{
            runClient();
        }
        catch (Exception e){
            
        }
    }
}
