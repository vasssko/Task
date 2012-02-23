package task6;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class CreateOurWindow extends JPanel implements ActionListener {
	
	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 50;
	
	protected JButton bAdd, bRemove, bClear;
	protected JLabel l;
	protected JTextField textField;
	protected Canvas canvas;
	
	private static List<JLabel> labels = new ArrayList<JLabel>();
	  
	public CreateOurWindow() {
		
		setPreferredSize(new Dimension(510, 450)); //450 => 350

		canvas = new Canvas();	
		canvas.setBackground(Color.WHITE);
		canvas.setPreferredSize(new Dimension(500, 300));
		
		l = new JLabel("Name: ");
		  
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		  
		bAdd = new JButton("Add");
		bAdd.setVerticalTextPosition(AbstractButton.CENTER);
		bAdd.setHorizontalTextPosition(AbstractButton.CENTER);
		bAdd.setActionCommand("Add");

		bRemove = new JButton("Remove");
		bRemove.setVerticalTextPosition(AbstractButton.CENTER);
		bRemove.setHorizontalTextPosition(AbstractButton.CENTER);
		bRemove.setActionCommand("Remove");

		bClear = new JButton("Clear");
		bClear.setVerticalTextPosition(AbstractButton.CENTER);
		bClear.setHorizontalTextPosition(AbstractButton.CENTER);
		bClear.setActionCommand("Clear");
		  

		//Listen for actions on buttons:
		bAdd.addActionListener(this);
		bRemove.addActionListener(this);
		bClear.addActionListener(this);

		bAdd.setToolTipText("Enter name and click this button to add TextBox with entered name.");
		bRemove.setToolTipText("Enter name and click this button to remove TextBox with entered name.");
		bClear.setToolTipText("Click this button to clear all area.");

		//Add Components to this container, using the default FlowLayout.
		add(canvas);
		add(l);
		add(textField);
		add(bAdd);
		add(bRemove);
		add(bClear);   
	  }
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add")) {
			String nameButton = textField.getText();
			createLabel(nameButton);
			textField.setText("");
		} 
		if (e.getActionCommand().equals("Remove")) {
			String nameButton = textField.getText();
			removeLabel(nameButton);
			textField.setText("");
		}
		if (e.getActionCommand().equals("Clear")) {
			clearLabel();
		}
	}

	private void createLabel(String nameLabel) {
		JLabel l = new JLabel(nameLabel);
		setLayout(null);
		l.setBounds(195, 380, BOX_WIDTH, BOX_HEIGHT);
		l.setBorder(new LineBorder(Color.BLACK, 1));
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setOpaque(true); 
		l.setBackground(Color.WHITE);
		
		//setComponentZOrder(l, getComponentZOrder(canvas) + 1);
		
		l.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
			private int prevX;
			private int prevY;
			
			public void mousePressed(MouseEvent e)
			{
			    prevX = e.getX();
			    prevY = e.getY();
			}
			
			public void mouseDragged(MouseEvent e)
			{
				JLabel label = (JLabel) e.getSource();
			    label.setBounds((int) (label.getX() + (e.getX()-prevX)), (int) (label.getY() + (e.getY()-prevY)), BOX_WIDTH, BOX_HEIGHT );
			    prevX = e.getX();
			    prevY = e.getY();
			    updateUI();
			}
		});
		add(l);
		labels.add(l);
		updateUI();
	}	
	
	private void removeLabel(String nameButton) {
		int index = 0;
		boolean startRemove = false;
		for(int i = 0; i < labels.size(); i++){
			if (labels.get(i).getText().equals(nameButton)){
				index = i;
				startRemove = true;
			}
		}
		if (startRemove){
			JLabel l = labels.remove(index);
			remove(l);
			updateUI();
		}
	}
	
	private void clearLabel() {
		while(labels.size() != 0){
			JLabel l = labels.remove(0);
			remove(l);
		}
		updateUI();
	}
	
}
