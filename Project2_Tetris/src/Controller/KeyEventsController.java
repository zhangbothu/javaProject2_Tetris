package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.MusicBgm;
public class KeyEventsController extends KeyAdapter{
/*
 * 将游戏控制对象传入该类的私有数据成员，实现对键盘事件的监听，
 * 当用户按下相应的按键后可以使方块移动变形，
 * 实现对界面层和数据层的更新。
 */
	@SuppressWarnings("unused")
	private boolean stopFlag=false;
	@SuppressWarnings("unused")
	private ServerGameController oppositeController;
	private ServerGameController gameController;
	public KeyEventsController(ServerGameController gameController){
		this.gameController = gameController;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(MusicBgm.isturnOn())
			MusicBgm.blockMoving();
		
		if(gameController.isRunning()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					this.gameController.keyUp();
					break;
				case KeyEvent.VK_DOWN:
					this.gameController.keyDown();
					break;
				case KeyEvent.VK_LEFT:
					this.gameController.keyLeft();
					break;
				case KeyEvent.VK_RIGHT:
					this.gameController.keyRight();
					break;
				case KeyEvent.VK_SPACE:
					this.gameController.keyPause();
					if(MusicBgm.isRunning()){
						MusicBgm.bgmStop();
					}
					break;
				default:
					break;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_R) {
			this.gameController.keyResume();
			if(!MusicBgm.isRunning()){
				MusicBgm.bgmBeginPlay();
			}
			return;
		}

	}
	
}
