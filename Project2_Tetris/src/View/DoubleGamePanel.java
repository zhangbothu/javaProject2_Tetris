package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ServerGameController;
import Model.MusicBgm;
import MyComponent.MyGamePart;
import MyComponent.MyButton;
import MyComponent.MyLabelWithBG;
import Controller.OppositeController;

/*
 * 联网对战界面，由背景和游戏区域和功能区域组成，定义了TetrisGameController类的对象和OppositeController类的对象，由这两个对象实现
 * 服务器端和客户端界面的游戏操作和数据记录。
 */
public class DoubleGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServerGameController localController;
	private OppositeController remoteController;
	private MyLabelWithBG newbackground;
	private MyGamePart mainWindow;
	private JLabel lblScroe, lblLevel;
	private MyGamePart mainWindow2;
	private JLabel lblScroe2, lblLevel2;
	private MyButton btnMusic;
	private MyButton btnResume;
	private MyButton btnPause;
	private MyButton btnRotate;
	private MyButton btnLeft;
	private MyButton btnRight;
	private MyButton btnDown;
	private MyButton btnHelp;

	public void setLocalController(ServerGameController gameController) {
		this.localController = gameController;
	}

	public void setRemoteController(OppositeController remoteController) {
		this.remoteController = remoteController;
	}

	/**
	 * Create the panel.
	 */
	public DoubleGamePanel() {
		setLayout(null);

		int width = 155;
		int heiht = 53;

		newbackground = new MyLabelWithBG(1000, 460, "Resources/Pictures/background/2.jpeg");

		mainWindow = new MyGamePart(10, 10, 233, 405);
		mainWindow.setBounds(10, 10, 233, 405);
		add(mainWindow);

		JLabel lblLabei = new JLabel("音乐");
		lblLabei.setForeground(Color.YELLOW);
		lblLabei.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei.setBounds(362, 380, 54, 15);
		add(lblLabei);

		JLabel lblLabei_1 = new JLabel("难度");
		lblLabei_1.setForeground(Color.GREEN);
		lblLabei_1.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei_1.setBounds(283, 77, 54, 15);
		add(lblLabei_1);

		lblLevel = new JLabel("0");
		lblLevel.setFont(new Font("黑体", Font.BOLD, 20));
		lblLevel.setForeground(Color.GREEN);
		lblLevel.setBounds(293, 102, 54, 15);
		add(lblLevel);

		JLabel label_1 = new JLabel("分数");
		label_1.setForeground(Color.GREEN);
		label_1.setFont(new Font("黑体", Font.BOLD, 16));
		label_1.setBounds(285, 151, 54, 15);
		add(label_1);

		lblScroe = new JLabel("0");
		lblScroe.setFont(new Font("黑体", Font.BOLD, 20));
		lblScroe.setForeground(Color.GREEN);
		lblScroe.setBounds(293, 176, 54, 15);
		add(lblScroe);

		JLabel label_3 = new JLabel("下一个形状");
		label_3.setForeground(Color.GREEN);
		label_3.setFont(new Font("黑体", Font.BOLD, 16));
		label_3.setBounds(268, 219, 86, 15);
		add(label_3);

		mainWindow2 = new MyGamePart(505, 10, 233, 405);
		mainWindow2.setBounds(505, 10, 233, 405);
		add(mainWindow2);

		JLabel label = new JLabel("难度");
		label.setForeground(Color.RED);
		label.setFont(new Font("黑体", Font.BOLD, 16));
		label.setBounds(420, 77, 54, 15);
		add(label);

		lblLevel2 = new JLabel("0");
		lblLevel2.setForeground(Color.RED);
		lblLevel2.setFont(new Font("黑体", Font.BOLD, 20));
		lblLevel2.setBounds(430, 102, 54, 15);
		add(lblLevel2);

		JLabel label_4 = new JLabel("分数");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("黑体", Font.BOLD, 16));
		label_4.setBounds(422, 151, 54, 15);
		add(label_4);

		lblScroe2 = new JLabel("0");
		lblScroe2.setForeground(Color.RED);
		lblScroe2.setFont(new Font("黑体", Font.BOLD, 20));
		lblScroe2.setBounds(430, 176, 54, 15);
		add(lblScroe2);

		JLabel label_6 = new JLabel("下一个形状");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("黑体", Font.BOLD, 16));
		label_6.setBounds(405, 219, 86, 15);
		add(label_6);

		btnResume = new MyButton("Resources/Pictures/window/null.png", "继续", 86, 52);
		btnResume.setForeground(Color.YELLOW);
		btnResume.setFont(new Font("黑体", Font.BOLD, 20));
		btnResume.setBounds(235, 354, 86, 23);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyResume();
				if (!MusicBgm.isRunning()) {
					MusicBgm.bgmBeginPlay();
				}
			}
		});
		add(btnResume);

		btnPause = new MyButton("Resources/Pictures/window/null.png", "暂停", 86, 52);
		btnPause.setFont(new Font("黑体", Font.BOLD, 20));
		btnPause.setForeground(Color.YELLOW);
		btnPause.setBounds(327, 354, 86, 23);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyPause();
				if (MusicBgm.isRunning()) {
					MusicBgm.bgmStop();
				}
			}
		});
		add(btnPause);
		btnHelp = new MyButton("Resources/Pictures/window/null.png", "帮助", 86, 52);
		btnHelp.setFont(new Font("黑体", Font.BOLD, 20));
		btnHelp.setForeground(Color.YELLOW);
		btnHelp.setBounds(418, 354, 86, 23);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnRotate, "按↑旋转，←左移，→右移，↓下移，按SPACE键暂停，R键继续游戏");
			}
		});
		add(btnHelp);
		// rotate
		btnRotate = new MyButton("Resources/Pictures/button/rotater.png", "", 30, 25);
		btnRotate.setBounds(362, 280, 30, 25);
		btnRotate.setBorderPainted(false);
		btnRotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyUp();
			}
		});
		add(btnRotate);

		// 点击事件控制方块移动
		btnLeft = new MyButton("Resources/Pictures/button/leftl.png", "", 30, 25);
		btnLeft.setBounds(320, 300, 30, 25);
		btnLeft.setBorderPainted(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyLeft();
			}
		});
		add(btnLeft);
		// 下降
		btnDown = new MyButton("Resources/Pictures/button/downd.png", "", 30, 25);
		btnDown.setBounds(362, 320, 30, 25);
		btnDown.setBorderPainted(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyDown();
			}
		});
		add(btnDown);
		// 向右
		btnRight = new MyButton("Resources/Pictures/button/rightr.png", "", 30, 25);
		btnRight.setBounds(402, 300, 30, 25);
		btnRight.setBorderPainted(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyRight();
			}
		});
		add(btnRight);

		btnMusic = new MyButton("Resources/Pictures/button/声音1.png", "", 30, 25);
		btnMusic.setBounds(362, 400, 30, 25);
		btnMusic.setBorderPainted(false);
		btnMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MusicBgm.isturnOn()) {
					MusicBgm.setturnOn(false);
					MusicBgm.bgmStop();
					btnMusic.setBgImage("Resources/Pictures/button/静音1.png");
				} else {
					MusicBgm.setturnOn(true);
					MusicBgm.bgmBeginPlay();
					btnMusic.setBgImage("Resources/Pictures/button/声音1.png");
				}
			}
		});
		add(btnMusic);

		mainWindow.setFocusable(false);
		btnResume.setFocusable(false);
		btnPause.setFocusable(false);
		btnMusic.setFocusable(false);
		btnRotate.setFocusable(false);
		btnLeft.setFocusable(false);
		btnHelp.setFocusable(false);
		btnRight.setFocusable(false);
		btnDown.setFocusable(false);
//		lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(340, 57, 2, 259);  //376, 57, 2, 259
//		ImageIcon icon1=new ImageIcon("Graphics/window/white.png");
//        icon1.setImage(icon1.getImage().getScaledInstance((int)(2*0.9),(int)(259*0.9),Image.SCALE_DEFAULT)); 
//        lblNewLabel.setIcon(icon1);
//		add(lblNewLabel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		newbackground.draw(g);

		mainWindow.creatwindow(g);

		// test
		g.setColor(new Color(255, 255, 255, 255));
		// 设置主屏边框
		((Graphics2D) g).setStroke(new BasicStroke(3L));
		g.drawRect(10, 11, 222, 402);

		g.setColor(new Color(255, 255, 255, 255));
		// 设置主屏边框
		((Graphics2D) g).setStroke(new BasicStroke(3L));
		g.drawRect(506, 11, 222, 402);

		mainWindow2.creatwindow(g);
		try {
			lblLevel.setText(Integer.toString(localController.getGamedata().level));
			lblScroe.setText(Integer.toString(localController.getGamedata().score));

			lblLevel2.setText(Integer.toString(remoteController.getGameData().level));
			lblScroe2.setText(Integer.toString(remoteController.getGameData().score));

			localController.getCurBlock().draw(g, 12, 32);
			localController.getNextBlock().draw(g, 200, 265);
			localController.getGamedata().drawwall(g, 0);

			remoteController.getCurBlock().draw(g, 506, 32);
			remoteController.getNextBlock().draw(g, 335, 265);
			remoteController.getGameData().drawwall(g, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
