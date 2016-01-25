package com.univ.callback;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.junit.Test;

public class JUnit {

	@Test
	public void test(){
		ActionListener al = new TimerPrinter();
		
		Timer t = new Timer(1000,al);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}
}
