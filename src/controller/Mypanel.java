package controller;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

import model.Bomb;
import model.EnemyTank;
import model.Hero;
import model.Shot;
import model.Tank;
import model.playboard;

public class Mypanel extends JPanel implements MouseListener,KeyListener,Runnable
{
	private static final long serialVersionUID = 1L;
	//player's tank
	Hero hero = null;
	//enemy tank
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	//explosion
	Vector<Bomb> bombs = new Vector<Bomb>();
	int etsize = 3;
	//bomb image
	Image image1 = null;
	
	public Mypanel(){
		//initialize my tank
		hero = new Hero(100, 100);
		hero.setColor(1);
		//initialize enemy tank
		for(int i=0;i<etsize;i++){
			EnemyTank eTank = new EnemyTank((int)(Math.random()*(playboard.x-30)),
					(int)(Math.random()*(playboard.y-30)));
			eTank.setDirect((int)(Math.random()*4));
			eTank.setSpeed(1+(int)(Math.random()*5));	
			Thread tetank = new Thread(eTank);
			tetank.start();
			ets.add(eTank);
		}
		image1 = Toolkit.getDefaultToolkit().getImage
				(this.getClass().getResource("/explosion.gif"));
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//draw the play board
		g.fillRect(0, 0, playboard.x, playboard.y);
		
		//draw player's tank
		if(hero.isIslive()){
			this.drawtank(hero.getX(), hero.getY(), g, hero.getDirect(), hero.getColor());
		}
		//take out each bullet from ss and draw the bullet
		for(int i=0;i<hero.getSs().size();i++){
			Shot subShot = hero.getSs().get(i);
			if(subShot!=null&&subShot.isValid()){
				g.draw3DRect(subShot.getX(), subShot.getY(), 2, 2, false);
			}
			if(subShot.isValid()== false){
				hero.getSs().remove(subShot);
			}
		}
		
		//draw bomb(explosion)
		for(int i=0;i<bombs.size();i++){
			Bomb b = bombs.get(i);
			if(b.getLifetime()>6){
				g.drawImage(image1, b.getX(), b.getY(), 50, 50, this);
			}else if(b.getLifetime()>3){
				g.drawImage(image1, b.getX(), b.getY(), 50, 50, this);
			}else{
				g.drawImage(image1, b.getX(), b.getY(), 50, 50, this);
			}
			
			b.reducelife();
			
			if(b.getLifetime()==0){
				bombs.remove(b);
			}
		}
		
		//draw enenmy's tank
		for(int i=0;i<ets.size();i++){
			EnemyTank eTank = ets.get(i);
			if(eTank.isIslive()){
				this.drawtank(eTank.getX(), eTank.getY(), 
					g, eTank.getDirect(), eTank.getColor());
				//draw bullet
				for(int j=0;j<eTank.getSs().size();j++){
					Shot eShot = eTank.getSs().get(j);
					if(eShot!=null&&eShot.isValid()){
						g.draw3DRect(eShot.getX(), eShot.getY(), 2, 2, false);
					}
					if(eShot.isValid()== false){
						ets.get(i).getSs().remove(eShot);
					}
				}
			}
		}
	}
	
	//check if bullet hit a tank
	public void checkhit(Shot s, Tank etk)
	{
		//check the direction of the tank
		switch(etk.getDirect()){
			case 0:
			case 2:
				if(s.getX()>etk.getX()&&s.getX()<etk.getX()+20
						&&s.getY()>etk.getY()&&s.getY()<etk.getY()+30){
					//hit the tank
					s.setValid(false);
					etk.setIslive(false);
					//create a bomb object
					Bomb bomb = new Bomb(etk.getX(), etk.getY());
					bombs.add(bomb);
				}
				break;
			case 1:
			case 3:
				if(s.getX()>etk.getX()&&s.getX()<etk.getX()+30
						&&s.getY()>etk.getY()&&s.getY()<etk.getY()+20){
					s.setValid(false);
					etk.setIslive(false);
					//create a bomb object
					Bomb bomb = new Bomb(etk.getX(), etk.getY());
					bombs.add(bomb);
				}
				break;
		}
	}
	
	public void drawtank(int x, int y, Graphics g, int direct, int type ){
		//tank type
		switch (type){
			case 0:
				g.setColor(Color.CYAN);
				break;
			case 1:
				g.setColor(Color.yellow);
				break;	
		}
		
		switch (direct){
		case 0://up
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15, y, 5, 30,false);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://right
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5,false);
			g.fill3DRect(x+5, y+5, 20, 10,false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);
			break;	
		case 2://down
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15, y, 5, 30,false);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3://left
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5,false);
			g.fill3DRect(x+5, y+5, 20, 10,false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}							
	}

	public void mouseClicked(MouseEvent arg0) {
	}
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_W){
			//set my tank's direct
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if (arg0.getKeyCode() == KeyEvent.VK_D){
			//set my tank's direct
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if (arg0.getKeyCode() == KeyEvent.VK_S){
			//set my tank's direct
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if (arg0.getKeyCode() == KeyEvent.VK_A){
			//set my tank's direct
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		//shoot the bullet
		if (arg0.getKeyCode() == KeyEvent.VK_J){
			this.hero.shoot();
		}
		//redraw the panel
		this.repaint();
	}
	
	public void keyReleased(KeyEvent arg0) {
	}
	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void checktankgothit(int who){
		//who: 1 for enemy tank, 0 for player's tank
		if(who == 1){
			for(int i=0;i<hero.getSs().size();i++){
				//take out each bullet
				Shot s = hero.getSs().get(i);
				if(s.isValid()){
					for(int j=0;j<etsize;j++){
						//take out an enemy tank
						EnemyTank eTank = ets.get(j);
						if(eTank.isIslive()){
							this.checkhit(s, eTank);
						}
					}
				}
			}
		}else{
			for(int i=0;i<etsize;i++){
				//take out an enemy tank
				EnemyTank eTank = ets.get(i);
				//take out each bullet
				for(int j=0;j<eTank.getSs().size();j++){
					//System.out.println(j);
					Shot enemyshot = eTank.getSs().get(j);
					if(enemyshot.isValid()){
						this.checkhit(enemyshot, hero);
					}
				}
			}
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(!hero.isIslive()){
				break;
			}		
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//check if player tank destroyed
			this.checktankgothit(0);
			//check if enemy tank destroyed
			this.checktankgothit(1);
			//refresh the game view
			this.repaint();
		}
	}
}
