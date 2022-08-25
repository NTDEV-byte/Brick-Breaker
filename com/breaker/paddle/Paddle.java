package com.breaker.paddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;
import com.breaker.Game;

public class Paddle extends Entity{
		
		private int lives;
		
			public Paddle(Rectangle bounds , Color c) {
				  this.bounds = bounds;
				  this.color = c;
				  this.lives = 3;
			}

			@Override
		public void update() {
		   if(bounds.x > 0) {
			   if(input.left) { 
				   bounds.x-=1;
			   }
		   }
		  if(bounds.x < Game.WIDTH - bounds.width) {
			  if(input.right) {
				    bounds.x+=1;
			   }
		  }
		  
		}
			
			@Override
		public void render(Graphics g) {
				g.setColor(Color.green);
				g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			}


			public int getLives() {
				return lives;
			}


			public void setLives(int lives) {
				this.lives = lives;
			}

}
