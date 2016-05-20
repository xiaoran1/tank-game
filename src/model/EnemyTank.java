package model;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub	
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			for(int i=0;i<60;i++){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(ss.size()<5){
					if((int)(Math.random()*20)==1){	
						Shot esShot = new Shot(x+10, y+10, direct);
						ss.add(esShot);
						Thread teshot = new Thread(esShot);
						teshot.start();
					}
				}	
				
				int tempx = x, tempy = y;
			
				switch (this.direct) {
				case 0:
					tempy = y-speed;
					break;
				case 1:
					tempx = x+speed;
					break;
				case 2:
					tempy = y+speed;
					break;		
				case 3:
					tempx = x-speed;
					break;
				}	
				if(tempx<playboard.x-30&&tempx>0&&tempy<playboard.y-30&&tempy>0){
					x = tempx;y=tempy;
				}else{
					this.direct = (int)(Math.random()*4);				
					break;
				}
			}
			this.direct = (int)(Math.random()*4);	
			if(this.islive == false){
				break;
			}
		}
	}
}
	

