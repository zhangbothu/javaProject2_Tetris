package View;

import javax.swing.JPanel;

import Controller.ServerGameController;
import Model.MusicBgm;
import MyComponent.MyGamePart;
import MyComponent.MyButton;
import MyComponent.MyLabelWithBG;
import Controller.KeyEventsController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BasicStroke;
import java.awt.Color;

/*
 * 单机游戏界面，由背景和游戏区域和功能区域组成，定义了TetrisGameController类的对象，由该对象实现
 * 由该对象控制当前界面的游戏操作和数据记录。
 */
public class SingleGamePanel extends JPanel {
	private ServerGameController localController;
	private MyLabelWithBG newbackground;//单机游戏界面的背景
	private MyGamePart mainWindow;//左侧游戏窗口
	private MyButton btnMusic;
	private MyButton btnHelp;
	private MyButton btnLeft;
	private MyButton btnDown;
	private MyButton btnRight;
	private MyButton btnResume;
	private MyButton btnRotate;
	private MyButton btnPause;
	
	private JLabel lblScroe,lblLevel;//成绩标签、难度标签
	
	
	public void setLocalController(ServerGameController gameController){
		this.localController=gameController;
	}
	
	/**
	 * Create the panel.
	 */
	public SingleGamePanel() {
		setLayout(null);
		mainWindow = new MyGamePart(12, 10, 228, 405);
		mainWindow.setBounds(25, 10, 236, 405);
		add(mainWindow);
		newbackground = new MyLabelWithBG(514, 460,"Resources/Pictures/background/2.jpeg");
		JLabel lblLabei= new JLabel("音乐");
		lblLabei.setForeground(Color.GREEN);
		lblLabei.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei.setBounds(340, 27, 54, 15);//283, 27, 54, 15
		add(lblLabei);
		
		JLabel lblLabei_1 = new JLabel("难度");
		lblLabei_1.setForeground(Color.GREEN);
		lblLabei_1.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei_1.setBounds(300, 27, 54, 15);
		add(lblLabei_1);
		
		lblLevel = new JLabel("0");
		lblLevel.setFont(new Font("黑体", Font.BOLD, 20));
		lblLevel.setForeground(Color.GREEN);
		lblLevel.setBounds(312, 50, 54, 15);
		add(lblLevel);
		
		JLabel label_1 = new JLabel("分数");
		label_1.setForeground(Color.GREEN);
		label_1.setFont(new Font("黑体", Font.BOLD, 16));
		label_1.setBounds(260, 27, 54, 15);
		add(label_1);
		//分数标签
		lblScroe = new JLabel("0");
		lblScroe.setFont(new Font("黑体", Font.BOLD, 20));
		lblScroe.setForeground(Color.GREEN);
		lblScroe.setBounds(272, 50, 54, 15);
		add(lblScroe);
		
		JLabel label_3 = new JLabel("下一个形状");
		label_3.setForeground(Color.GREEN);
		label_3.setFont(new Font("黑体", Font.BOLD,16));
		label_3.setBounds(268, 219, 86, 15);
		add(label_3);
		//恢复按钮
		btnResume = new MyButton("Resources/Pictures/window/null.png","继续",86,52);
		btnResume.setForeground(Color.GREEN);
		btnResume.setFont(new Font("黑体", Font.BOLD,20));
		btnResume.setBounds(271, 325, 86, 23);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyResume();
				if(!MusicBgm.isRunning()){
					MusicBgm.bgmBeginPlay();
				}
			}
		});
		add(btnResume);
		
		btnPause = new MyButton("Resources/Pictures/window/null.png","暂停",86,52);
		btnPause.setFont(new Font("黑体", Font.BOLD,20));
		btnPause.setForeground(Color.GREEN);
		btnPause.setBounds(271, 363, 86, 28);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyPause();
				if(MusicBgm.isRunning()){
					MusicBgm.bgmStop();
				}
			}
		});
		add(btnPause);
		
		//点击事件控制方块移动
		btnLeft = new MyButton("Resources/Pictures/button/left.png","",
				30, 25);
		btnLeft.setBounds(260, 120, 30, 25);
		btnLeft.setBorderPainted(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyLeft();
			}
		});
		add(btnLeft);
		//下降
		btnDown = new MyButton("Resources/Pictures/button/down.png","",
				30, 25);
		btnDown.setBounds(300, 150, 30, 25);
		btnDown.setBorderPainted(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyDown();
			}
		});
		add(btnDown);
		//向右
		btnRight = new MyButton("Resources/Pictures/button/right.png","",
				30, 25);
		btnRight.setBounds(340, 120, 30, 25);
		btnRight.setBorderPainted(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyRight();
			}
		});
		add(btnRight);
		
		btnRotate = new MyButton("Resources/Pictures/button/rotate.png","",
				30, 25);
		btnRotate.setBounds(300, 90, 30, 25);
		btnRotate.setBorderPainted(false);
		btnRotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyUp();
			}
		});
		add(btnRotate);
		//帮助
		btnHelp = new MyButton("Resources/Pictures/window/null.png", "帮助", 86, 52);
		btnHelp.setFont(new Font("黑体", Font.BOLD, 20));
		btnHelp.setForeground(Color.GREEN);
		btnHelp.setBounds(271, 403, 86, 23);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "按↑旋转，←左移，→右移，↓下移，按SPACE键暂停，R键继续游戏");
			}
		});
		add(btnHelp);
		//音乐切换按钮
		btnMusic = new MyButton("Resources/Pictures/button/声音.png","",
				30, 25);
		btnMusic.setBounds(342, 45, 30, 25);
		btnMusic.setBorderPainted(false);
		btnMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MusicBgm.isturnOn()){
					MusicBgm.setturnOn(false);
					MusicBgm.bgmStop();
					btnMusic.setBgImage("Resources/Pictures/button/静音.png");
				}else{
					MusicBgm.setturnOn(true);
					MusicBgm.bgmBeginPlay();
					btnMusic.setBgImage("Resources/Pictures/button/声音.png");
				}
			}
		});
		add(btnMusic);
		//防止按键失效
		mainWindow.setFocusable(false);
		btnResume.setFocusable(false);
		btnPause.setFocusable(false);
		btnMusic.setFocusable(false);
		btnLeft.setFocusable(false);
		btnDown.setFocusable(false);
		btnRight.setFocusable(false);
		btnRotate.setFocusable(false);
		btnHelp.setFocusable(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		newbackground.draw(g);
		mainWindow.creatwindow(g);
		try{
			lblLevel.setText(Integer.toString(localController.getGamedata().level));
			lblScroe.setText(Integer.toString(localController.getGamedata().score));
			
			localController.getCurBlock().draw(g, 12, 32);
			localController.getNextBlock().draw(g, 200, 265);
			localController.getGamedata().drawwall(g,0);
			
			g.setColor(new Color(255, 255, 255, 255));
	        // 设置主屏边框
	        ((Graphics2D) g).setStroke(new BasicStroke(3L));
	        g.drawRect(10, 11, 222, 402);
//			//尝试//
//			g.translate(12, 12);
//			for(int i=0;i<20;i++)
//			{
//				//内层循环控制列数
//				for(int j=0;j<10;j++)
//				{
//					int x = j*20;
//					int y = i*20;
//					g.drawRect(x, y, 20, 20);
//				}
//			}
//			////
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
