package view;
import javax.swing.*;
import controller.*;

public class MyTankGame extends JFrame{
	private static final long serialVersionUID = 1L;
	Mypanel mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame mykGame = new MyTankGame();
		
	}
	
	public MyTankGame(){
		mp = new Mypanel();
		Thread thread = new Thread(mp);
		thread.start();
		this.add(mp);
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
