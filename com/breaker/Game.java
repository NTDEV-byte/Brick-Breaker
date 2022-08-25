package com.breaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.breaker.ball.Ball;
import com.breaker.input.InputHandler;
import com.breaker.level.LevelManager;
import com.breaker.paddle.Paddle;

public class Game extends JPanel{
	

			private static final long serialVersionUID = 1L;
			public static final int WIDTH = 800;
			public static final int HEIGHT = 600;
			public static InputHandler keys = new InputHandler();
			private JFrame window;
			private Paddle pad;
			private Ball ball;
			private LevelManager manager;

					public Game() { 
						window = new JFrame("Casse-Brique");
						window.setVisible(true);
						window.setResizable(false);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window.setLocationRelativeTo(null);
						setPreferredSize(new Dimension(WIDTH,HEIGHT));
						window.addKeyListener(keys);
						window.addMouseMotionListener(keys);
						window.addMouseListener(keys);
						window.add(this);
						window.pack();
					}
					
					private void initGame() { 
						pad = new Paddle(new Rectangle(150,600-20,80,10),Color.red);
						ball = new Ball(this,new Rectangle(200,200,Ball.WIDTH_BALL,Ball.HEIGHT_BALL),Color.cyan);
						pad.init(this);
						ball.init(this);
						manager = new LevelManager(this);
					}


					private boolean isReady(){
						return pad != null && ball != null && manager != null;
					}
				public void paint(Graphics g) {
						if(isReady()){
							draw(g);
							update();
						}
					 repaint();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				private void update() {
					keys.update();
					pad.update();
					ball.update();
					manager.update();
				}
				
				private void draw(Graphics g) { 
					g.setColor(Color.black);
					g.fillRect(0, 0, getWidth(), getHeight());
					ball.render(g);
					pad.render(g);
					manager.render(g);
					//generator.drawBricks(g);
				}
				public static void main(String[] args) { 
						Game game = new Game();
						game.initGame();
				}
				public Paddle getPad() {
					return pad;
				}
				public void setPad(Paddle pad) {
					this.pad = pad;
				}
				public Ball getBall() {
					return ball;
				}
				public void setBall(Ball ball) {
					this.ball = ball;
				}

				public LevelManager getManager() {
					return manager;
				}

				public void setManager(LevelManager manager) {
					this.manager = manager;
				}

}
