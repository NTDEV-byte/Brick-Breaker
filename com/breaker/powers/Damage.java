package com.breaker.powers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.breaker.Game;

public class Damage extends Power{

	public Damage(Rectangle rectangle) {
		super(rectangle, Color.magenta);
	}

	@Override
	public void activateEffet() {
		ball.setDamage(ball.getDamage() + 3);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setFont(new Font("Verdana",Font.PLAIN,15));
		g.drawString("Power Increased ! " + 3, Game.WIDTH / 2, Game.HEIGHT / 2);
	}
}
