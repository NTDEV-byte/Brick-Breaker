package com.breaker.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;
import com.breaker.Game;
import com.breaker.ball.Ball;
import com.breaker.paddle.Paddle;

public abstract class Power extends Entity{
	

		public static final int PWIDTH = 5;
		public static final int PHEIGHT = 5;

	
		public static final int BALLS = 0;
		public static final int CHANCE = 1;
		public static final int DAMAGE = 2;
		public static final int GROW = 3;
		public static final int DECREASE = 4;
		public static final int GUN = 5;
	
		private static final int POWERS = 6;
		
		protected boolean catched;
		protected Paddle pad;
		protected Ball ball;
		
		
		public Power(Game game,Rectangle rectangle,Color color) {
			 	this.bounds = rectangle;
			 	this.color = color;
			    this.game = game;
			    this.pad = game.getPad();
			    this.ball = game.getBall();
		}
		

		public void update() {
		  bounds.y+=1;
		  if(game.getPad().getBounds().intersects(bounds)){
			  	 catched = true;
		  }
		  if(bounds.y >= Game.HEIGHT) {
			  destroy();
			  bounds.setBounds(0, 0, 0, 0);
		  }
		}

	public abstract void activateEffet(); 
		
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public boolean isCatched() {
		return catched;
	}

	public void setCatched(boolean catched) {
		this.catched = catched;
	}
	
	public static Power generateRandomPower(Game game,int x,int y) {
		 int power = (int)(Math.random() * POWERS);
		 switch(power) { 
		 case BALLS:
			  return new Balls(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case CHANCE:
			 return new Chance(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case DAMAGE:
			 return new Damage(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case GROW:
			 return new Grow(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case DECREASE:
			 return new Decrease(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case GUN:
			 return new Gun(game);
		 }
		 return new Grow(game,new Rectangle(x,y,PWIDTH,PHEIGHT));
	}
	
	
	

}
