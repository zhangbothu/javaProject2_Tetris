package MyComponent;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * 创建带有背景图片的自定义按钮类，用于美化界面
 */
public class MyButton extends JButton{

	int width,height;
	
	private static final long serialVersionUID = 1218571878182523580L;
	public MyButton(String url,String text,int width,int height){
		this.width=width;
		this.height=height;
        ImageIcon icon1=new ImageIcon(url);
        setFocusPainted(false);
        setContentAreaFilled(false);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
        setText(text);setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
        setFocusable(true);
        setMargin(new Insets(0, 0, 0, 0));
	}
	public void setBgImage(String url){
		ImageIcon icon1=new ImageIcon(url);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
	}
	
}
