package MyComponent;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * 创建了一个自定义标签类，将该类对象add到SingleGamePanel或DoubleGamePanel中可以自定义游戏背景。
 */
public class MyLabelWithBG  extends JLabel{
	
	private static final long serialVersionUID = 1L;
	private Image background;
	
	public MyLabelWithBG(int width,int height,String address){
        ImageIcon icon=new ImageIcon(address);
        background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
//        ImageIcon icon=new ImageIcon(address);
//        background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
//		ImageIcon icon=new ImageIcon(address);
//      background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
	}
}
