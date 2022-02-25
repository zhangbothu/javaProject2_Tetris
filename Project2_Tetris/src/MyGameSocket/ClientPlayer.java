package MyGameSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * Client为客户端，用于连接服务器已创建的IP，即在游戏中可加入网络对战房间。
 */
public class ClientPlayer {
    private static ThreadForExchange clientExchangeThread;

	public static void InitClient(int PORT) {
		try {
			Socket socket = new Socket("localhost", PORT);
			System.out.println("IP:" + socket.getLocalAddress() + "端口号:" + socket.getPort());
			clientExchangeThread = new ThreadForExchange(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public static ThreadForExchange getExchangeThread(){
        return clientExchangeThread;
    }
}
