package MyComponent;

//左侧的游戏视图
import javax.swing.*;
import java.awt.*;
/*
 * 该类定义了游戏视图窗口
 */
public class MyGamePart extends JLabel {

	/**
	 * serialVersionUID
	 */
	public int x;
	public int y;
	public int h;
	public int w;
	private static final long serialVersionUID = 3144082323765440087L;
	private static final int SIZE = 7;
	private static final Image GamePartImage = new ImageIcon("Resources/Pictures/window").getImage();
	private static final int IMGW = GamePartImage.getWidth(null);
	private static final int IMGH = GamePartImage.getHeight(null);

	public MyGamePart(int x, int y, int w, int h) {
		this.x = x - 5;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public MyGamePart(int x, int y, int w, int h, String strUrl) {
		this(x, y, w, h);

		ImageIcon icon = new ImageIcon(strUrl);
		icon.setImage(icon.getImage().getScaledInstance((int) (w * 0.4), (int) (h * 0.6), Image.SCALE_DEFAULT));
		setIcon(icon);
		setFocusable(true);
	}

	public void creatwindow(Graphics g) {

		g.drawImage(GamePartImage, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);

		g.drawImage(GamePartImage, x + SIZE, y, x + w - SIZE, y + SIZE, SIZE, 0, IMGW - SIZE, SIZE, null);

		g.drawImage(GamePartImage, x + w - SIZE, y, x + w, y + SIZE, IMGW - SIZE, 0, IMGW, SIZE, null);

		g.drawImage(GamePartImage, x, y + SIZE, x + SIZE, y + h - SIZE, 0, SIZE, SIZE, IMGH - SIZE, null);

		g.drawImage(GamePartImage, x + SIZE, y + SIZE, x + w - SIZE, y + h - SIZE, SIZE, SIZE, IMGW - SIZE, IMGH - SIZE,
				null);
		g.drawImage(GamePartImage, x + w - SIZE, y + SIZE, x + w, y + h - SIZE, IMGW - SIZE, SIZE, IMGW, IMGH - SIZE,
				null);
		g.drawImage(GamePartImage, x, y + h - SIZE, x + SIZE, y + h, 0, IMGH - SIZE, SIZE, IMGH, null);
		g.drawImage(GamePartImage, x + SIZE, y + h - SIZE, x + w - SIZE, y + h, SIZE, IMGH - SIZE, IMGW - SIZE, IMGH,
				null);
		g.drawImage(GamePartImage, x + w - SIZE, y + h - SIZE, x + w, y + h, IMGW - SIZE, IMGH - SIZE, IMGW, IMGH,
				null);
	}

}
