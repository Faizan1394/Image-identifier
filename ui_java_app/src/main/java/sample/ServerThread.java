package sample;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class ServerThread extends Thread{
	protected Socket socket;
	InputStream inputStream;

	public ServerThread(Socket socket) {
		super();
		this.socket = socket;

		 try {
			 inputStream = socket.getInputStream();
		 	} 
		 catch (IOException e) { e.printStackTrace(); }
	}

	public void run() {
		try {
			DataInputStream input = new DataInputStream(inputStream);

			byte[] data;
			int len= input.readInt();
			data = new byte[len];
			if (len > 0) {
				input.readFully(data,0,data.length);
			}
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(data));
			ImageIO.write(image, "png", new File("src/readImage.png"));
		}
		catch (Exception e) { e.printStackTrace(); }
	}
}