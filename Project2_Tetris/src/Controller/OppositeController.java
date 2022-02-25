package Controller;

import javax.swing.*;

import Model.GameData;
import MyComponent.MyCell;
import View.DoubleGamePanel;

/* 服务器端和客户端控制类中分别含有该类静态对象，目的是方便获取彼此的游戏数据，
 * 此类是客户端游戏控制类,内部的GameData对象记录客户端的游戏数据,
 * 同时定义了客户端进行操作方块的一系列方法,
 * 当客户端游戏结束时，可以通过服务器端的静态控制类对象获取服务器端玩家的分数，
 * 经比较后弹出游戏结果对话框。
 * 
 */
public class OppositeController {

	public static OppositeController oppositeController;
	private DoubleGamePanel panel;//视图层V
	private GameData gameData = new GameData();//存储游戏数据M
	private MyCell curBlock;//当前方块
	private MyCell nextBlock;//下一方块

	public OppositeController(DoubleGamePanel panel) {
		this.panel = panel;
		curBlock = new MyCell(1);
		nextBlock = new MyCell(2);
	}
	
	public GameData getGameData() {
		return gameData;
	}

	public void blockUp() {
		curBlock.change();
		panel.repaint();
	}

	public void blockDown() {
		curBlock.movedown();
		panel.repaint();
	}

	public void blockLeft() {
		curBlock.moveleft();
		panel.repaint();

	}

	public void blockRight() {
		curBlock.moveright();
		panel.repaint();
	}

	public void blockPutted() {
		gameData.isput(curBlock);
		curBlock.setColor(0);
	}

	public void setNowBlock(int chooseColor) {
		curBlock = new MyCell(chooseColor);
	}

	public void setNextBlock(int chooseColor) {
		nextBlock = new MyCell(chooseColor);
	}

	public MyCell getCurBlock() {
		return curBlock;
	}

	public MyCell getNextBlock() {
		return nextBlock;
	}

	public void isBlockPop() {
		gameData.hasPoped();
	}

	public void gameover() {
		int oppositeScore = ServerGameController.gameController.gameover();
		int serverScore = gameData.score;
		String result = Integer.toString(serverScore) + ":" + Integer.toString(oppositeScore) + ",";
		if (serverScore < oppositeScore) {
			// 获胜
			JOptionPane.showMessageDialog(panel, result + "恭喜你获胜了");
		} else if (serverScore > oppositeScore) {
			// 输
			JOptionPane.showMessageDialog(panel, result + "很遗憾你输了");
		} else {
			// 平局
			JOptionPane.showMessageDialog(panel, result + "平局");
		}
	}

}
