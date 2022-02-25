package Model;

import java.awt.*;

import MyComponent.MyCell;
/*
 * 该类用于存储游戏数据，二维数组wall[][]存储每个坐标点的状态（有正方形或没有正方形）
 * score存储分数，level存储当前难度，该类还提供了一些方法用于判断当前的方块是否可以左移、右移、下移、做旋转变换
 * 能否消行以及判断游戏是否结束。
 */
public class GameData {

	public int deleteLine=0;
	private boolean[][] wall;
	public int score =0;
	public int newScore=0;
	public int difficulty=1;
	public int level =0;
	
	public GameData(){
		this.wall = new boolean[11][19];//墙有19行11列
		for(int cell_x=0;cell_x<11;cell_x++)
			for(int cell_y=0;cell_y<19;cell_y++)
				wall[cell_x][cell_y]=false;
	}
	//是否可以左移
	public boolean isleftside(MyCell nowcell){
		
		for(int i=0;i<4;i++){
			if(nowcell.cell_x[i]==0 || wall[nowcell.cell_x[i]-1][nowcell.cell_y[i]])		
				return true;
		}
		return false;
	}
	//是否可以右移
	public boolean isrightside(MyCell nowcell){
		
		for(int i=0;i<4;i++){
			if(nowcell.cell_x[i]==10  || wall[nowcell.cell_x[i]+1][nowcell.cell_y[i]])
				return true;
		}
		return false;
	}
	//方块是否被放下
	public boolean isput(MyCell nowcell){
		
		boolean isput = false;

		for(int i=0;i<4;i++)
			if( nowcell.cell_y[i]>=18 || wall[nowcell.cell_x[i]][nowcell.cell_y[i]+1] ){
				isput = true;
				break;
			}

		if(isput)
			for(int j=0;j<4;j++)
				wall[nowcell.cell_x[j]][nowcell.cell_y[j]]=true;
		
		return isput;
	}
	//判断当前方块是否可以变换
	public boolean judgeChange(MyCell nowcell){
		for(int i=0;i<4;i++){
			int change_x = nowcell.cell_y[i]-nowcell.cell_y[0]+nowcell.cell_x[0] ;
			int change_y = nowcell.cell_x[0]-nowcell.cell_x[i]+nowcell.cell_y[0] ;
			if(  change_x<0 || change_y<0 || change_x>10 || wall[change_x][change_y])
				return false;
		}
		return true;
	}
	//判断当前游戏是否结束
	public boolean gameover(){
		
		for(int cell_x=0;cell_x<11;cell_x++){
			if(wall[cell_x][0] == true)
				return true;
		}
		return false;
		
	}
	
	//判断是否可以消行
	public boolean hasPoped(){
		
		boolean iscancel=false;
		int tmpline=0;						
		for(int cell_y=18;cell_y>=0;cell_y--)
			for(int cell_x=0;cell_x<11;cell_x++){
				if(wall[cell_x][cell_y] != true)
					break;

				if(cell_x==10){
					tmpline++;
					deleteLine++;
					deleteLine(cell_y);
					cell_y++;
					iscancel=true;
				}
			}
		
		if(tmpline != 0){
			score +=tmpline*tmpline;
			if(score > (2+2*level*level))
				level++;
		}
		return iscancel;
	}
	
	public void deleteLine(int line){
		for(int cell_y=line;cell_y>0;cell_y--)
			for(int cell_x=0;cell_x<11;cell_x++){
				wall[cell_x][cell_y] = wall[cell_x][cell_y-1];
			}	
		
	}

	public void drawwall(Graphics g,int mode){
		MyCell rect = new MyCell(0);
		for(int cell_x=0;cell_x<11;cell_x++)
			for(int cell_y=0;cell_y<19;cell_y++)
				if(wall[cell_x][cell_y]==true){
					if(mode==1){
						rect.drawRect(g, 506, 32, cell_x, cell_y);
					}else {
						rect.drawRect(g, 12, 32, cell_x, cell_y);
					}
				}
	}
}
