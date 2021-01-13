package com.breaker.brick;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;

public class Brick extends Entity{
	
		public static final int MAX_RESISTANCE = 1;
		private int resistance = 2;
		private boolean gifted;

		public Brick(Rectangle bounds,Color c) { 
					this.bounds = bounds;
					this.color = c;
					this.gifted = uneChanceSurDeux();
					this.resistance = random.nextInt(MAX_RESISTANCE) + 1;
			}
			
			public Brick(Rectangle bounds) { 
				this.bounds = bounds;
				this.color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
				resistance = random.nextInt(MAX_RESISTANCE) + 1;
				this.gifted = uneChanceSurDeux();
			}
			

	@Override
		public void update() {
		if(game.getBall().getBounds().intersects(bounds)) {
			 		resistance--;
		}
		if(resistance <= 0) {
			destroy();
		}
	}

	private boolean uneChanceSurDeux() { 
	    return (random.nextInt(2) == 0) ? false : true;
	}
	
	@Override
		public void render(Graphics g) {
			g.setColor(color);
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}

	public boolean isGifted() {
		return gifted;
	}

	public void setGifted(boolean gifted) {
		this.gifted = gifted;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

}
