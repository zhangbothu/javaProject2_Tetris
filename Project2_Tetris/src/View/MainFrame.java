package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Controller.ServerGameController;
import Model.MusicBgm;
import MyGameSocket.ClientPlayer;
import MyGameSocket.ServerPlayer;
import Controller.KeyEventsController;
import Controller.OppositeController;

/*
 * 该类定义了游戏主界面，首先让用户选择游戏模式，当选择了单机模式，则实例化一个单机界面(singleGamePanel)对象，
 * 并将该对象作为TetrisGameController类的构造函数的参数创建了一个TetrisGameController类的对象，传递给TetrisGameController类中的静态引用，
 * 同时让singleGamePanel中的成员变量localController引用该控制类对象，给该窗口添加键盘事件监听器，并将该控制类对象作为参数传入。同时在TetrisGameController
 * 对象中实例化有GameData类对象存储游戏数据。至此，一个TetrisGameController对象中，既包含当前界面的引用，又包含当前界面的数据，从而实现了MVC间的通信。
 * 联网对战模式与此类似，即分别创建两个DoubleGamePanel对象，一个TetrisGameController类对象控制服务器端的游戏控制与数据记录，一个OppositeController类对象
 * 负责客户端的游戏控制和数据记录。
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MusicBgm.bgmBeginPlay();
		String[] options = { "单人模式", "创建网络对战房间", "进入网络对战房间" };
		int x = JOptionPane.showOptionDialog(null, "请选择游戏模式", "选择模式", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		this.setTitle("俄罗斯方块");
		this.setSize(470, 410);
		this.setResizable(false);
		this.setLocationRelativeTo(null);// 界面置于中间位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (x == 0)
			chooseGameMode(0);
		else if (x == 1)
			chooseGameMode(1);
		else if (x == 2)
			chooseGameMode(2);
		else if (x == JOptionPane.CLOSED_OPTION)
			System.exit(0);
	}

	// 选择游戏模式
	public void chooseGameMode(int gameMode) {
		System.out.println("你选择了模式" + gameMode);
		switch (gameMode) {
		case 0:
			SingleGamePanel singleGamePanel = new SingleGamePanel();
			ServerGameController.gameController = new ServerGameController(singleGamePanel);
			singleGamePanel.setLocalController(ServerGameController.gameController);
			this.setTitle("单人游戏");
			this.setSize(430, 470);
			this.setContentPane(singleGamePanel);
			this.addKeyListener(new KeyEventsController(ServerGameController.gameController));
			ServerGameController.gameController.startNewGame();
			break;
		case 1:
			String port = JOptionPane.showInputDialog("请输入游戏房间号:");
			ServerPlayer.InitServer(Integer.parseInt(port));
			System.out.println("创建房间成功，房间号为"+port);

			DoubleGamePanel doubleGamePanel1 = new DoubleGamePanel();
			ServerGameController.gameController = new ServerGameController(ServerPlayer.getExchangeThread(), doubleGamePanel1);
			OppositeController.oppositeController = new OppositeController(doubleGamePanel1);
			doubleGamePanel1.setLocalController(ServerGameController.gameController);
			doubleGamePanel1.setRemoteController(OppositeController.oppositeController);
			this.setContentPane(doubleGamePanel1);
			this.addKeyListener(new KeyEventsController(ServerGameController.gameController));
			this.setTitle("联网对战");
			this.setSize(760, 470);
			ServerGameController.gameController.startNewGame();
			break;
		case 2:
			String port2 = JOptionPane.showInputDialog("请输入要加入的房间号:");
			ClientPlayer.InitClient(Integer.parseInt(port2));
			System.out.println("正在加入房间...");

			DoubleGamePanel doubleGamePanel2 = new DoubleGamePanel();
			ServerGameController.gameController = new ServerGameController(ClientPlayer.getExchangeThread(), doubleGamePanel2);
			OppositeController.oppositeController = new OppositeController(doubleGamePanel2);
			doubleGamePanel2.setLocalController(ServerGameController.gameController);
			doubleGamePanel2.setRemoteController(OppositeController.oppositeController);
			this.setContentPane(doubleGamePanel2);
			this.addKeyListener(new KeyEventsController(ServerGameController.gameController));
			this.setTitle("联网对战");
			this.setSize(760, 470);
			ServerGameController.gameController.startNewGame();
			break;
		}
		requestFocus();
	}

}
