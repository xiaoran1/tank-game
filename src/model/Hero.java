package model;

public class Hero extends Tank{
	Shot s = null;
	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void shoot(){	
		switch(this.direct){
			case 0:
				s = new Shot(x+10, y,0);
				getSs().add(s);
				break;
			case 1:
				s = new Shot(x+30, y+10,1);
				getSs().add(s);
				break;
			case 2:
				s = new Shot(x+10, y+30,2);
				getSs().add(s);
				break;
			case 3:
				s = new Shot(x, y+10,3);
				getSs().add(s);
				break;
		}
		Thread t = new Thread(s);
		t.start();
	}
	
	//tank move upwards
	public void moveUp(){
		this.y -= this.speed;
	}
	//tank move right
	public void moveRight(){
		this.x += this.speed;
	}
	//tank move downwards
	public void moveDown(){
		this.y += this.speed;
	}
	//tank move left
	public void moveLeft(){
		this.x -= this.speed;
	}
}
