package model;

public class Bomb {
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
	public int getLifetime() {
		return lifetime;
	}
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	public boolean isIslive() {
		return islive;
	}
	public void setIslive(boolean islive) {
		this.islive = islive;
	}
	int x,y;
	int lifetime = 9;
	boolean islive = true;
	
	public Bomb(int x, int y){
		this.x = x;
		this.y=y;
	}
	public void reducelife(){
		if(lifetime>0){
			lifetime--;
		}else{
			this.islive = false;
		}
	}
}
