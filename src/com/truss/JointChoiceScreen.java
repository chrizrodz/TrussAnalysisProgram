package com.truss;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JointChoiceScreen extends JFrame
{
	JTextField jointID;

	public JointChoiceScreen(TrussScreen ts)
	{
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JPanel jointsearch = new JPanel();
		jointsearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		jointsearch.add(new JLabel("Enter Joint ID"));
		jointID = new JTextField();
		jointID.setPreferredSize(new Dimension(100, 20));
		jointsearch.add(jointID);
		p.add(jointsearch);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton edit = new JButton("EDIT");
		edit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				finish(ts);
			}

		});
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				delete(ts);
			}

		});
		buttons.add(edit);
		buttons.add(delete);
		p.add(buttons);
		this.add(p);
		this.pack();
		this.setVisible(true);
	}
	
	private void finish(TrussScreen ts)
	{
		ArrayList<Joint> joints = ts.joints;
		for (Joint j : joints)
		{
			if (j.ID.equals(jointID.getText()))
			{

				new JointInputScreen(ts,j);
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	private void delete(TrussScreen ts)
	{
		ArrayList<Joint> joints = ts.joints;
		Joint toRemove = null;
		for (Joint j : joints)
		{
			if (j.ID.equals(jointID.getText()))
			{

				toRemove = j;
			}
		}
		for(Beam b : toRemove.attachedBeams)
		{
			ts.beams.remove(b);
		}
		ts.joints.remove(toRemove);
		ts.reeval();
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));		
	}
}
