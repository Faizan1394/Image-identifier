package sample;

import java.io.*;
import java.net.*;

public class Server {
	protected ServerSocket serverSocket;
	protected Socket socket;
	protected static int PORT = 8080;

	public Server(){
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
        }
        catch(IOException e){ System.err.println("IOEXception while creating server connection"); }
    }

    public static void main(String args[]){ Server server = new Server(); }
}