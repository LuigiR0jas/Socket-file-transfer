package org.larp.sfl;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
	
	  public final static int SOCKET_PORT = 13267; 
	  public final static String FILE_TO_SEND = "/home/luigi/source.txt";  

	  public static void main (String [] args ) throws Exception {
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try {
	      servsock = new ServerSocket(SOCKET_PORT);
	      while (true) {
	        System.out.println("Waiting for connections...");
	        try {
	          sock = servsock.accept();
	          System.out.println("Accepted connection : " + sock);
	          File myFile = new File (FILE_TO_SEND);
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	          os = sock.getOutputStream();
	          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
	          System.out.println("Done.");
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	      }
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	  }
	}