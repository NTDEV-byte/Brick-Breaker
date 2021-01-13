package com.breaker.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Entity;
import com.breaker.Game;

public class Bullet extends Entity{
		

	
			public Bullet(int x,int y) { 
				this.bounds = new Rectangle(x,y,2,1);
				this.color =  Color.yellow;
				this.damage = 1;
			}

	@Override
	public void update() {
		bounds.y-=1;
		if(game.getManager().getCurrentLevel().collisionOccuredWithEntity(this)) { 
				destroy();
		}
		if(bounds.y >= Game.HEIGHT) destroy();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}	
	
		

}
