package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ServerThread extends Thread{
	protected Socket socket;
	InputStream inputStream;
	MainController mc;

	public ServerThread(Socket socket,MainController mc) {
		super();
		this.mc = mc;
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
			ImageIO.write(image, "png", new File("src\\resources\\readImage.png"));
			ImageIO.write(image, "png", new File("src\\readImage.png"));


			Process p = Runtime.getRuntime().exec("python src/CNNTest.py src/readImage.png");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String prediction = in.readLine();
//			System.out.println(prediction);



//			String ret;
//			while((ret = in.readLine()) != null){
//				System.out.println(ret);
//			}

			mc.updateImage();
			mc.updateInfo(prediction);
//			mc.updateInfo("roses");

			socket.close();

		}
		catch (Exception e) { e.printStackTrace(); }
	}
}