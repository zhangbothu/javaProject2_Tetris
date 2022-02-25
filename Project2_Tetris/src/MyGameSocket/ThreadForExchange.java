package MyGameSocket;


import java.io.*;
import java.net.Socket;

import javax.swing.JLabel;

import Controller.ServerGameController;
import MyComponent.MyGamePart;
import MyComponent.MyLabelWithBG;
import View.DoubleGamePanel;
import Controller.OppositeController;
/*
 * ClassForExchange是一个用于进程之间通信的线程，通过此线程对战双方可以实现信息交换，
 *	用户可以将自己的对战情况写入，供对方读取此时的对战信息，同时也可以读取对方发来的信息，
 *	以此来实现网络对战的功能。
 */


public class ThreadForExchange implements Runnable {
    private Socket socket;
    private OppositeController remoteController=new OppositeController(new DoubleGamePanel());
	@SuppressWarnings("unused")
	BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
	private MyLabelWithBG newbackground;
    public ThreadForExchange(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(this).start();
    }
    public void run() {
        try {
            while(true) {
              
                String message = bufferedReader.readLine();
                if(isNum(message)) {
                    int color=Integer.parseInt(message);
                    if(OppositeController.oppositeController.getCurBlock()==null){
                        OppositeController.oppositeController.setNowBlock(color);
                    }else{
                        OppositeController.oppositeController.setNowBlock(OppositeController.oppositeController.getNextBlock().color);
                        OppositeController.oppositeController.setNextBlock(color);
                    }
                }
                switch (message){
                    case "up":
                        OppositeController.oppositeController.blockUp();
                        break;
                    case "down":
                        OppositeController.oppositeController.blockDown();
                        break;
                    case "left":
                        OppositeController.oppositeController.blockLeft();
                        break;
                    case "right":
                        OppositeController.oppositeController.blockRight();
                        break;
                    case "isput":
                        OppositeController.oppositeController.blockPutted();
                        break;
                    case "ispop":
                        OppositeController.oppositeController.isBlockPop();
                        break;
                    case "gameover":
                        OppositeController.oppositeController.gameover();
                        break;
                    case "keyPause":
                        ServerGameController.gameController.pause();
                        break;
                    case "keyResume":
                        ServerGameController.gameController.resume();
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("出错了: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("出错了:" + e.getMessage());
                }
            }
        }
    }
    
    public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
    
	public void sendMessage(String str) {
		try {
			bufferedWriter.write(str);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}