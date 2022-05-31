/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapp;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


// here the server is the computer store 
public class Server implements Runnable{
    public void runServer() throws Exception{
       System.out.println("Starting sending files....");

       byte[] bte = new byte[10]; // intiate the file size to be ready to send
        
       ServerSocket ssocket = new ServerSocket(5000);// make a server socket 
       Socket socket = null;
       
       socket = ssocket.accept();// open the session between the client and the server 
       
              //initilize the file location to read 
       FileInputStream filein = new FileInputStream("Database/Server/Test.txt");
       
       
              //prepare the file to send from zero byte to actually file size
       filein.read(bte , 0 , bte.length);
       OutputStream out = socket.getOutputStream();
       out.write(bte , 0 , bte.length);
       System.out.println("files sent successfully");
       filein.close();
       out.close();
       socket.close();
       ssocket.close();
    }
    
    public synchronized void run(){
        try{
            runServer();
        }
        catch (Exception e){
            
        }
    }
}

