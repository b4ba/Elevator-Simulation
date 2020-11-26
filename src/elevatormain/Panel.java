package elevatormain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {
	private static final long serialVersionUID = 419152795898008678L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	//Constructor for Window class
	public Panel() {
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setBounds(new Rectangle(WIDTH, HEIGHT));
		setBackground(Color.GRAY);
		setVisible(true);
	}

}

