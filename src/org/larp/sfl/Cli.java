package org.larp.sfl;
import java.net.Socket;
import java.io.IOException;
import java.io.*;

public class Cli {
	  private String msg;
	  private Socket cli;
	  private static InputStreamReader streamIn =  new     
	  InputStreamReader(System.in);
	  private static BufferedReader in =  new BufferedReader(streamIn, 1);

	  public Cli()
	  {
	    msg="";
	  }

	  public String readKey()
	  {
	    String line = null;
	    try
	    {
	      line = in.readLine();
	    }
	    catch (IOException e)
	    {
	      System.out.println("(Cliente) Error in SimpleIO.readLine: " +  e.getMessage());
	      System.exit(-1);
	    }
	    return line;
	  }

	  //Se crea una instancia del socket client
	  public boolean createSocketClient(String host, int port)
	  {
	    try
	    {
	      cli = new Socket(host, port);
	      return true;
	    }
	    catch (IOException e)
	    {
	      return false;
	    }
	  }

	  public String getServerMsj()
	  {
	    try
	    {
	      BufferedReader rd = new 
	      BufferedReader(new InputStreamReader(cli.getInputStream()));
	      String contenido="**************************************\n";
	      while (rd.ready())
	      {
	        contenido+= rd.readLine()+"\n";
	      }
	      rd.close();
	      contenido+="**************************************\n";
	      return contenido;
	    }
	    catch (IOException e)
	    {
	      return "(Cliente) Error en proceso de obtención de mensaje desde el servidor";
	    }
	  }

	  public boolean socketSend(String data)
	  {
	    try
	    {
	      PrintStream ps = new PrintStream(cli.getOutputStream());
	      ps.println(data);
	      ps.flush();
	      return true;
	    }
	    catch(IOException e)
	    {
	      return false;
	    }
	  }
	  
	  public static void main(String[] args)
	  {
	    try
	    {
	      Cli c = new Cli();
	      System.out.print("(Cliente) Introduzca Mensaje:");
	      c.msg = c.readKey();
	      c.createSocketClient("localhost", 1024);
	      c.socketSend(c.msg);
	      System.out.print(c.getServerMsj());
	      c.cli.close();
	    }
	    catch(Exception e)
	    {
	      System.out.print(e.getMessage());
	    }
	  }
	}
