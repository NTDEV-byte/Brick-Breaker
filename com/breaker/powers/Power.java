package com.breaker.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;
import com.breaker.Game;
import com.breaker.ball.Ball;
import com.breaker.paddle.Paddle;

public abstract class Power extends Entity{
	
	/***
	 * Balls   white
	 * Chance  pink
	 * Damage  magenta
	 * Grow    green
	 * Decrease red
	 * Gun yellow
	 * 
	 * 
	 */

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
		protected Paddle pad = Game.game.getPad();
		protected Ball ball = Game.game.getBall();
		
		
		public Power(Rectangle rectangle,Color color) {
			 	this.bounds = rectangle;
			 	this.color = color;
		}
		
		public Power() {
			
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
	
	public static Power generateRandomPower(int x,int y) {
		 int power = (int)(Math.random() * POWERS);
		 switch(power) { 
		 case BALLS:
			  return new Balls(new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case CHANCE:
			 return new Chance(new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case DAMAGE:
			 return new Damage(new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case GROW:
			 return new Grow(new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case DECREASE:
			 return new Decrease(new Rectangle(x,y,PWIDTH,PHEIGHT));
		 case GUN:
			 return new Gun();
		 }
		 return new Grow(new Rectangle(x,y,PWIDTH,PHEIGHT));
	}
	
	
	

}
