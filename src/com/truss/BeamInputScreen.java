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

public class BeamInputScreen extends JFrame
{
	JTextField first;
	JTextField second;

	TrussScreen parent;
	
	boolean isDelete;

	public BeamInputScreen(TrussScreen ts, boolean delete)
	{
		isDelete = delete;
		parent = ts;
		initFrame();
	}

	private void finish()
	{
		
		ArrayList<Joint> joints = parent.joints;
		Joint a=null;
		Joint b=null;
		for (Joint j : joints)
		{
			if (j.ID.equals(first.getText()))
			{
				a = j;
			}
			else if (j.ID.equals(second.getText()))
			{
				b = j;
			}
		}
		if(a!=null&&b!=null)
		{
			if(isDelete)
				parent.deleteBeam(a,b);
			else
				parent.addBeam(a, b);
		}
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void initFrame()
	{
		JPanel screen = new JPanel();

		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

		
		JPanel first_joint_search = new JPanel();
		first_joint_search.setLayout(new FlowLayout(FlowLayout.LEFT));
		first_joint_search.add(new JLabel("Enter first joint"));
		first = new JTextField();
		first.setPreferredSize(new Dimension(100, 20));
		first_joint_search.add(first);
		screen.add(first_joint_search);

		JPanel second_joint_search = new JPanel();
		second_joint_search.setLayout(new FlowLayout(FlowLayout.LEFT));
		second_joint_search.add(new JLabel("Enter second joint"));
		second = new JTextField();
		second.setPreferredSize(new Dimension(100, 20));
		second_joint_search.add(second);
		screen.add(second_joint_search);


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
