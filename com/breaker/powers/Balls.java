package com.breaker.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.breaker.Game;
import com.breaker.ball.Ball;

public class Balls extends Power{

    public static final double DURATION = 20000;
    
    
	private static int GEN = 3;
	
	private List<Ball> balls;
	private double spawn;
	private boolean launched;
	
	
	public Balls(Rectangle rectangle) {
		super(rectangle, Color.white);
		balls = new ArrayList<Ball>();
		generate();
		spawn = System.currentTimeMillis();
	}
	
	private void generate() { 
		for(int i=0;i<GEN;i++) {
			Ball b = new Ball(new Rectangle(random.nextInt(Game.WIDTH),random.nextInt(Game.HEIGHT),Ball.WIDTH_BALL / 2,Ball.HEIGHT_BALL / 2),new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			b.setDirection(Ball.BALL_DIRECTION.values()[random.nextInt(Ball.BALL_DIRECTION.values().length)]);
			balls.add(b);
		}
	}

	
	public void update() { 
		bounds.y+=1;
		if(launched) {
		
	 if(game.getPad().getBounds().intersects(bounds)){
		  	 catched = true;
	  }
	  if(bounds.y >= Game.HEIGHT) {
		 // destroy();
		  bounds.setBounds(0, 0, 0, 0);
	  }
	  for(Ball b : balls) {
		  b.update();
		}
	  if(System.currentTimeMillis() - spawn >= DURATION ) destroy();
		}
	}
	@Override
	public void activateEffet() {
		launched = true;
	}
	
	public void renderB(Graphics g) { 
		if(launched) {
			g.setColor(color);
			for(Ball b : balls) { 
				g.fillOval(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
			}
		}
		}
	
	

}
