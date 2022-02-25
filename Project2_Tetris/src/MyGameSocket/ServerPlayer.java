package MyGameSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Server为服务器端，用于新建一个IP地址，即在游戏中可以创建网络对战房间
 */
public class ServerPlayer {
	private static ThreadForExchange serverExchangeThread;
	@SuppressWarnings("unused")
	private static ThreadForExchange serverExchangeThread1;

	public static void InitServer(int PORT) {
		try {
			ServerSocket ss = new ServerSocket(PORT);
			System.out.println("创建" + PORT + "成功");
			Socket socket1 = ss.accept();
			serverExchangeThread = new ThreadForExchange(socket1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ThreadForExchange getExchangeThread() {
		return serverExchangeThread;
	}

}