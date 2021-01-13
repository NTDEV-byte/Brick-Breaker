package com.breaker.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.breaker.Game;
import com.breaker.input.InputHandler;
import com.breaker.paddle.Paddle;

public class Gun extends Power{
	
	private InputHandler input = Game.keys;
	private static Paddle pad = game.getPad();
	private static int offset = pad.getBounds().width / 2;
	private static final double RATE = 50;
	
	private List<Bullet> bullets;
	private boolean activated = true;
	private static Rectangle gun = new Rectangle(pad.getBounds().x + offset,pad.getBounds().y,2,2);
	private double start = System.currentTimeMillis();
	private boolean canFire = true;
	
	
	public Gun() {
		super(gun, Color.yellow);
		bullets = new ArrayList<Bullet>();
	}
	
	
	public void render(Graphics g) { 
		if(activated) { 
			for(Bullet b : bullets) { 
				b.render(g);
			}
		}
	}
	
	public void update() { 
		if(activated) {
			if(input.space && canFire) { 
				bullets.add(new Bullet(gun.x,gun.y));
			}
			for(Bullet b : bullets) { 
				b.update();
			}
		}
		gun.setLocation(pad.getBounds().x + pad.getBounds().width / 2, pad.getBounds().y);
		removeDestroyedBullets();
		if(System.currentTimeMillis()- start >= RATE) {
			 canFire = true;
			 start+=RATE;
		}
		else canFire = false;
	}
	
	private void removeDestroyedBullets() { 
		for(int i=0;i<bullets.size();i++) {
			   if(bullets.get(i).hasBeenDestroyed()) { 
				   bullets.remove(i);
			   }
		}
	}
	
	@Override
	public void activateEffet() {
		activated = true;
	}

}
