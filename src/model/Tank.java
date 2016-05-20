package model;

import java.util.Vector;

public class Tank {
	int x = 0;
	int y = 0;
	//0 represent up, 1 right, 2 down, 3 left
	int direct = 0;
	//tank's speed
	int speed = 5;
	int color = 0;
	boolean islive = true;
	Vector<Shot> ss = new Vector<Shot>();
	
	public Vector<Shot> getSs() {
		return ss;
	}

	public void setSs(Vector<Shot> ss) {
		this.ss = ss;
	}
	
	public boolean isIslive() {
		return islive;
	}

	public void setIslive(boolean islive) {
		this.islive = islive;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x, int y){
		this.x = x;
		this.y = y;
	}
}
