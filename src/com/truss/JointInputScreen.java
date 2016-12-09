package com.truss;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JointInputScreen extends JFrame
{
	JTextField x;
	JTextField y;

	TrussScreen parent;
	
	Joint editJoint;

	public JointInputScreen(TrussScreen ts)
	{
		parent = ts;
		initFrame();
	}
	public JointInputScreen(TrussScreen ts,Joint j)
	{
		parent = ts;
		editJoint = j;
		initFrame();
	}

	private void finish()
	{
		Joint j;
		if(editJoint == null)
			j = new Joint();
		else
			j = editJoint;
		j.setPos(Float.parseFloat(x.getText()), Float.parseFloat(y.getText()));
		
		if(editJoint == null)
			parent.addJoint(j);
		else
			parent.reeval();
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void initFrame()
	{
		JPanel screen = new JPanel();

		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

		
		JPanel xpair = new JPanel();
		xpair.setLayout(new FlowLayout(FlowLayout.LEFT));
		xpair.add(new JLabel("Enter x"));
		x = new JTextField();
		x.setPreferredSize(new Dimension(100, 20));
		if(editJoint!=null)
			x.setText(""+editJoint.x);
		xpair.add(x);
		screen.add(xpair);

		JPanel ypair = new JPanel();
		ypair.add(new JLabel("Enter y"));
		y = new JTextField();
		y.setPreferredSize(new Dimension(100, 20));
		if(editJoint!=null)
			y.setText(""+editJoint.y);
		ypair.add(y);
		screen.add(ypair);

		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				finish();
				
			}
			
		});
		screen.add(confirm);

		this.add(screen);
		this.pack();
		this.setVisible(true);
	}
}
