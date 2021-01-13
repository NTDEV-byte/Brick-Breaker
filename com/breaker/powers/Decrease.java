package com.breaker.powers;

import java.awt.Color;
import java.awt.Rectangle;

public class Decrease extends Power{
	
	public static final int AMOUNT = 20;
	
	public Decrease(Rectangle rectangle) {
		super(rectangle, Color.red);
	}

	@Override
	public void activateEffet() {
		pad.setBounds(new Rectangle(pad.getBounds().x,pad.getBounds().y,pad.getBounds().width - AMOUNT,pad.getBounds().height));
	}

}
