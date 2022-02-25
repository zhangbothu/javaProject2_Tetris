package MyComponent;

/*
 * 方块大类
 */
import javax.swing.*;
import java.awt.*;
/*
 * 这是方块类，每个方块由4个正方形组成，cell_x[]和cell_y[]分别存储4个正方形的x坐标和y坐标；
 * 一共有7种形状的方块，所以可以根据color变量选择方块的形状和颜色：switch语句设置初始状态下
 * 4个矩形块的坐标，同时根据color值选择并绘制了myrect.png的不同区域。同时该类还定义了方块
 * 在不同操作下的坐标变换:change,movedown,moveleft,moveright.
 */
public class MyCell {

	protected static final int CellWidth = 20;
	protected static final int CellHeight = 20;
	public int[] cell_x = new int[4];// x坐标 4个矩形块的x坐标
	public int[] cell_y = new int[4];// y坐标 4个矩形块的y坐标
	public int color;// 已预先定义好了一副图形，图形由7个cell单元格组成。
	public Image Cell_img = new ImageIcon("Resources/Pictures/game/myrect.png").getImage();
	public MyCell(int color) {
		this.color = color;
		switch (color) {
		case 1:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 6;
			this.cell_x[3] = 7;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = -1;
			this.cell_y[3] = -1;
			break;
		case 2:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 6;
			this.cell_x[3] = 5;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = -1;
			this.cell_y[3] = 0;
			break;
		case 3:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 5;
			this.cell_x[3] = 4;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = 0;
			this.cell_y[3] = 0;
			break;
		case 4:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 6;
			this.cell_x[3] = 6;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = -1;
			this.cell_y[3] = 0;
			break;
		case 5:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 6;
			this.cell_x[3] = 4;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = -1;
			this.cell_y[3] = 0;
			break;
		case 6:
			this.cell_x[0] = 5;
			this.cell_x[1] = 6;
			this.cell_x[2] = 5;
			this.cell_x[3] = 4;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = 0;
			this.cell_y[3] = 0;
			break;
		case 7:
			this.cell_x[0] = 5;
			this.cell_x[1] = 4;
			this.cell_x[2] = 5;
			this.cell_x[3] = 6;
			this.cell_y[0] = -1;
			this.cell_y[1] = -1;
			this.cell_y[2] = 0;
			this.cell_y[3] = 0;
			break;
		}
	}

	public void draw(Graphics g, int pos_x, int pos_y) { // 绘制4个正方形块组成的俄罗斯块
		for (int i = 0; i < 4; i++) {
			drawRect(g, pos_x, pos_y, cell_x[i], cell_y[i]);
		}
	}

	/*
	 * img - 要绘制的指定图像。如果 img 为 null，则此方法不执行任何操作。 dx1 - 目标矩形第一个角的 cell_x 坐标。 dy1 -
	 * 目标矩形第一个角的 cell_y 坐标。 dx2 - 目标矩形第二个角的 cell_x 坐标。 dy2 - 目标矩形第二个角的 cell_y 坐标。
	 * sx1 - 源矩形第一个角的 cell_x 坐标。 sy1 - 源矩形第一个角的 cell_y 坐标。 sx2 - 源矩形第二个角的 cell_x 坐标。
	 * sy2 - 源矩形第二个角的 cell_y 坐标。 observer - 当缩放并转换了更多图像时要通知的对象。
	 */
	public void drawRect(Graphics g, int pos_x, int pos_y, int cell_x, int cell_y) {
		g.drawImage(Cell_img, pos_x + cell_x * CellWidth, pos_y + cell_y * CellHeight, pos_x + (cell_x + 1) * CellWidth,
				pos_y + (cell_y + 1) * CellHeight, color * CellWidth, 0, (color + 1) * CellWidth, CellHeight, null);

	}

	public void change() {
		int tmpx = 0;
		if (color != 3 && tmpx != -1)
			for (int i = 0; i < 4; i++) {
				tmpx = this.cell_x[i];
				this.cell_x[i] = this.cell_y[i] - this.cell_y[0] + this.cell_x[0];
				this.cell_y[i] = this.cell_x[0] - tmpx + this.cell_y[0];
			}
	}

	public void movedown() {
		for (int i = 0; i < 4; i++)
			this.cell_y[i] = this.cell_y[i] + 1;

	}

	public void moveleft() {

		for (int i = 0; i < 4; i++)
			this.cell_x[i] = this.cell_x[i] - 1;

	}

	public void moveright() {
		for (int i = 0; i < 4; i++)
			this.cell_x[i] = this.cell_x[i] + 1;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
