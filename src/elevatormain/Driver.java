/**
 * 
 */
package elevatormain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * @author alexbabalitis
 *
 */

public class Driver {
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private final static int DELAY_MS = 10;

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Simple Example");
				
		Content content = new Content(WIDTH/2 - 32, HEIGHT - 52, 10);		
		frame.setContentPane(content);

		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBackground(Color.GRAY);
		frame.setVisible(true);
		
		Timer myTimer = new Timer(DELAY_MS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content.tick();
			}
		});
		myTimer.start();
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
