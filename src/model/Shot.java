package model;

public class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 3;
	boolean valid = true;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
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
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Shot(int x, int y,int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct; 
	}
	public void run() {
		while(this.valid){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x<0||x>playboard.x-15||y<0||y>playboard.y-15){
				this.valid = false;
				break;
			}
			switch(direct){
				case 0:
					y-=speed;
					break;
				case 1:
					x+=speed;
					break;
				case 2:
					y+=speed;
					break;
				case 3:
					x-=speed;
					break;
			}
			//System.out.println("current bullet pos X, Y:"+x+" "+y);
		}
	}
}
