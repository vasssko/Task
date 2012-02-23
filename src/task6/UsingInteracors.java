package task6;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class UsingInteracors {
	
	 public static void main(String[] args) {
		    JFrame frame = new JFrame("Task6: UsingInteractors");
		    frame.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        System.exit(0);
		      }
		    });
		    
		    frame.getContentPane().add(new CreateOurWindow(), FlowLayout.LEFT);
		    frame.pack();
		    frame.setVisible(true);
	}

}

