package elevatormain;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class ElevatorObject extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected int x, y;
	protected int velX, velY;
	
	public ElevatorObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	
}
