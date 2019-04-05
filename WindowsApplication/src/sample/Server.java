package sample;

import java.io.*;
import java.net.*;

public class Server extends Thread{
	protected ServerSocket serverSocket;
	public Socket socket;
	protected static int PORT = 8080;


	public void run(){
        try{

            serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connection on Port "  +  serverSocket.getLocalPort());

            // run client forever
            while (true){
                // wait for client to connect
                socket = serverSocket.accept();
                System.out.println("Client connected...");
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        }catch(IOException e){ System.err.println("IOEXception while creating server connection"); }
    }


}