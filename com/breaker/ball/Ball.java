package com.breaker.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;
import com.breaker.Game;

public class Ball extends Entity{
	
		public static final int WIDTH_BALL = 10; 
		public static final int HEIGHT_BALL = 10;
		private static int ADJUST_X = 2;
		private static int ADJUST_Y = 2;
		public enum BALL_DIRECTION{NORD,NORDEST,NORDOUEST,SUDEST,SUD,SUDOUEST};
	
		private BALL_DIRECTION direction;
		private int damage;
		
		public Ball(Rectangle rectangle , Color color) { 
			this.bounds = rectangle;
			this.color = color;
			this.direction = BALL_DIRECTION.NORDEST;
			this.damage = 1;
		}

		@Override
		public void update() {
			mouvement();
			collision();
			collisionWithPad();
			collisionWithBricks();
		}
		
		private void collisionWithBricks() { 
			if(game.getManager().getCurrentLevel().collisionOccured(this)) {
			if(direction == BALL_DIRECTION.NORD) { 
				direction = BALL_DIRECTION.SUD;
			}
			else if(direction == BALL_DIRECTION.NORDEST) { 
				direction = BALL_DIRECTION.SUDEST;
			}
			else if(direction == BALL_DIRECTION.NORDOUEST) { 
				direction = BALL_DIRECTION.SUDOUEST;
			}
			}
		}
		
		private void collisionWithPad() { 
			if(game.getPad().getBounds().intersects(bounds)) { 
				  if(direction == BALL_DIRECTION.SUD) {
					   direction = BALL_DIRECTION.NORD;
				  }
				  else if(direction == BALL_DIRECTION.SUDEST) {
					   direction = BALL_DIRECTION.NORDEST;
				  }
				  else if(direction == BALL_DIRECTION.SUDOUEST) {
					  direction = BALL_DIRECTION.NORDOUEST;
				  }
				  
			}
		}
		
		private void mouvement() { 
			if(direction == BALL_DIRECTION.NORD) {
				   bounds.y-=1;
			}
			else if(direction == BALL_DIRECTION.NORDEST) {
				 bounds.y-=1;
				 bounds.x+=1;
			}
			else if(direction == BALL_DIRECTION.NORDOUEST) {
				 bounds.y-=1;
				 bounds.x-=1;
			}
			else if(direction == BALL_DIRECTION.SUD) {
				 bounds.y+=1;
			}
			else if(direction == BALL_DIRECTION.SUDEST) {
				 bounds.y+=1;
				 bounds.x+=1;
			}
			else if(direction == BALL_DIRECTION.SUDOUEST) {
				 bounds.y+=1;
				 bounds.x-=1;
			}
		}
		
		private boolean collision() {
			if(bounds.intersects(0, ADJUST_Y, Game.WIDTH, ADJUST_Y)) {
				 if(direction == BALL_DIRECTION.NORD) {
					  direction = BALL_DIRECTION.SUD;
				 }
				 else if(direction == BALL_DIRECTION.NORDEST) {
					     direction = BALL_DIRECTION.SUDEST;
				 }
				 else if(direction == BALL_DIRECTION.NORDOUEST) {
					 direction = BALL_DIRECTION.SUDOUEST;
				 }
				return true;
			}
			else if(bounds.intersects(Game.WIDTH - ADJUST_X ,0,Game.WIDTH - ADJUST_X,Game.HEIGHT)) {
				 if(direction == BALL_DIRECTION.NORDEST) {
					  direction = BALL_DIRECTION.NORDOUEST;
				 }
				 else if(direction == BALL_DIRECTION.SUDEST) {
					     direction = BALL_DIRECTION.SUDOUEST;
				 }
				return true;
			}
			else if(bounds.intersects(0,Game.HEIGHT - ADJUST_Y,Game.WIDTH,Game.HEIGHT - ADJUST_Y)) {
				 if(direction == BALL_DIRECTION.SUD) {
					  direction = BALL_DIRECTION.NORD;
				 }
				 else if(direction == BALL_DIRECTION.SUDEST) {
					     direction = BALL_DIRECTION.NORDEST;
				 }
				 else if(direction == BALL_DIRECTION.SUDOUEST) {
					 direction = BALL_DIRECTION.NORDOUEST;
				 }
				return true;
			}
			else if(bounds.intersects(ADJUST_X,0,ADJUST_X,Game.HEIGHT)) { 
				 if(direction == BALL_DIRECTION.NORDOUEST) {
					  direction = BALL_DIRECTION.NORDEST;
				 }
				 else if(direction == BALL_DIRECTION.SUDOUEST) {
					     direction = BALL_DIRECTION.SUDEST;
				 }
				return true;
			}
			return false;
		}
		@Override
		public void render(Graphics g) {
			g.setColor(color);
			g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
		}

		public BALL_DIRECTION getDirection() {
			return direction;
		}

		public void setDirection(BALL_DIRECTION direction) {
			this.direction = direction;
		}

		public static int getWidthBall() {
			return WIDTH_BALL;
		}

		public int getDamage() {
			return damage;
		}

		public void setDamage(int damage) {
			this.damage = damage;
		}

}
