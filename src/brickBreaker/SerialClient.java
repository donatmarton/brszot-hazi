package brickBreaker;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SerialClient extends Network {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	SerialClient(Control c) {
		super(c);
		c.setType(2);
	}

	private class ReceiverThread implements Runnable {

		public void run() {
			System.out.println("Waiting for input...");
			try {
				while (true) {
					ArrayList<Integer> received = (ArrayList<Integer>) in.readObject();
					ctrl.allDataReceived(received);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Server disconnected!");
			} finally {
				disconnect();
			}
		}
	}

	@Override
	void connect(String ip) {
		disconnect();
		try {
			socket = new Socket(ip, 10007);
			
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();

			Thread rec = new Thread(new ReceiverThread());
			rec.start();
			ctrl.playGame();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!");
		}
	}

	@Override
	void send(ArrayList<Integer> allData) {
		if (out == null)
			return;
		System.out.println("Sending allData to Server");
		try {
			out.writeObject(allData);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException ex) {
			System.err.println("Error while closing conn.");
		}
	}
}
