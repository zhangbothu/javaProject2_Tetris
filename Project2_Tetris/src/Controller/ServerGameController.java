package Controller;
import View.DoubleGamePanel;
import View.SingleGamePanel;

import javax.swing.*;

import Model.GameData;
import MyComponent.MyCell;
import MyGameSocket.ThreadForExchange;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/*
 * 将此时的Jpanel对象传入到TetrisGameController对象的引用中，
 */
public class ServerGameController {

	public static ServerGameController gameController;
	private GameData gamedata;
	private MyCell curBlock;
	private MyCell nextBlock;
	private JPanel panel;
	private DoubleGamePanel dgpanel;
	private GameData newGameData = new GameData();
	private Timer timer;
	private ThreadForExchange tforExchange;
	private boolean isRunning = false;

	public ServerGameController(JPanel panel) {
		this.panel = (SingleGamePanel) panel;
	}

	public ServerGameController(ThreadForExchange thread, DoubleGamePanel panel) {
		this.tforExchange = thread;
		this.panel = (DoubleGamePanel) panel;
	}
	
	public MyCell getCurBlock() {
		return curBlock;
	}

	public MyCell getNextBlock() {
		return nextBlock;
	}

	public GameData getGamedata() {
		return gamedata;
	}

	private class GameSpeed extends TimerTask {
		private int nowSpeed = 5;

		public void run() {

			if (!isRunning) {
				return;
			}

			if (nowSpeed <= 0) {
				if (gamedata.isput(curBlock)) {
					if (gamedata.gameover()) {
						System.out.println("游戏结束！");
						isRunning = false;
						if (tforExchange != null) {
							tforExchange.sendMessage("gameover");
							int myScore = gamedata.score;
							int oppositeScore = OppositeController.oppositeController.getGameData().score;

							String str = Integer.toString(myScore) + ":" + Integer.toString(oppositeScore) + ",";
							if (myScore > oppositeScore) {
								// 赢
								JOptionPane.showMessageDialog(panel, str + "恭喜你获胜了");
							} else if (myScore < oppositeScore) {
								// 输
								JOptionPane.showMessageDialog(panel, str + "很遗憾你输了");
							} else {
								// 平局
								JOptionPane.showMessageDialog(panel, str + "平局");
							}
						} else {
							int myScore = gamedata.score;
							JOptionPane.showMessageDialog(panel, "游戏结束，" + "分数为:" + Integer.toString(myScore));
						}
						return;
					}
					curBlock.setColor(0);
					Random random = new Random();
					if (tforExchange != null) {
						tforExchange.sendMessage("isput");
					}
					int randomNumber = random.nextInt(7) + 1;
					curBlock = new MyCell(nextBlock.color);
					nextBlock = new MyCell(randomNumber);
					if (tforExchange != null) {
						tforExchange.sendMessage(Integer.toString(randomNumber));
					}
					if (gamedata.hasPoped()) {
						if (tforExchange != null) {
							tforExchange.sendMessage("ispop");
						}
					}
				} else {
					curBlock.movedown();
					if (tforExchange != null)
						tforExchange.sendMessage("down");
				}
				panel.repaint();
				nowSpeed = 10 - gamedata.level;
			} else {
				nowSpeed--;
			}
		}
	}

	public void startNewGame() {
		
		gamedata = new GameData();
		isRunning = true;
		timer = new Timer();
		timer.schedule(new GameSpeed(), 100, 30);
		this.curBlock = new MyCell(1);
		this.nextBlock = new MyCell(2);
		
	}

	public void keyUp() {
		if (!isRunning)
			return;
		if (!gamedata.judgeChange(curBlock))
			return;

		curBlock.change();
		if (tforExchange != null)
			tforExchange.sendMessage("up");
		panel.repaint();

	}

	public void keyDown() {
		if (!isRunning)
			return;
		if (gamedata.isput(curBlock))
			return;

		curBlock.movedown();
		if (tforExchange != null)
			tforExchange.sendMessage("down");
		panel.repaint();

	}

	public void keyLeft() {
		if (!isRunning)
			return;
		if (gamedata.isleftside(curBlock))
			return;

		curBlock.moveleft();
		if (tforExchange != null)
			tforExchange.sendMessage("left");
		panel.repaint();

	}

	public void keyRight() {
		if (!isRunning)
			return;
		if (gamedata.isrightside(curBlock))
			return;

		curBlock.moveright();
		if (tforExchange != null)
			tforExchange.sendMessage("right");
		panel.repaint();

	}

	public void keyPause() {
		isRunning = false;
		if (tforExchange != null)
			tforExchange.sendMessage("keyPause");
	}

	public void pause() {
		isRunning = false;
	}

	public void resume() {
		isRunning = true;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void keyResume() {
		isRunning = true;
		if (tforExchange != null)
			tforExchange.sendMessage("keyResume");
	}
	
	public int gameover() {
		System.out.print("gameover");
		isRunning = false;
		return gamedata.score;
	}
}
