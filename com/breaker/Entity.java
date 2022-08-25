package com.breaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.breaker.input.InputHandler;

public abstract class Entity {

		protected Game game;
		protected Color color;
		protected Rectangle bounds;
		protected Random random = new Random();
		protected int dx,dy;
		protected InputHandler input = Game.keys;
		protected boolean destroyed;
		protected int damage;
		
		
		public abstract void update();
		public abstract void render(Graphics g);
		
		
		public void destroy() { 
			this.destroyed = true;
		}
		
		public boolean hasBeenDestroyed() { 
			return destroyed;
		}
		
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public Rectangle getBounds() {
			return bounds;
		}
		public void setBounds(Rectangle bounds) {
			this.bounds = bounds;
		}
		public Random getRandom() {
			return random;
		}
		public void setRandom(Random random) {
			this.random = random;
		}
		public int getDx() {
			return dx;
		}
		public void setDx(int dx) {
			this.dx = dx;
		}
		public int getDy() {
			return dy;
		}
		public void setDy(int dy) {
			this.dy = dy;
		}
		public void init(Game game) { 
				this.game = game;
		}
		
		
		public static void showEntity(Entity e) {
			 System.out.println("X: "+e.getBounds().x);
			 System.out.println("Y: "+e.getBounds().y);
			 System.out.println("W: "+e.getBounds().width);
			 System.out.println("H: "+e.getBounds().height);
			 System.out.println("Destroyed: "+e.destroyed);
		}
		public int getDamage() {
			return damage;
		}
		public void setDamage(int damage) {
			this.damage = damage;
		}
		
		
}
