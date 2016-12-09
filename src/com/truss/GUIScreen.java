package com.truss;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIScreen
{

	public GUIScreen(Main main, TrussScreen trussScreen)
	{
		 JButton addJoint = new JButton("Add joint");
		 
		 addJoint.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				new JointInputScreen(trussScreen);
			}
		 });
		 
		 JButton editJoint = new JButton("Edit joint");
		 editJoint.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				new JointChoiceScreen(trussScreen);
			}
		 });
		 
		 JButton addBeam = new JButton("Add Beam");
		 addBeam.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				new BeamInputScreen(trussScreen,false);
			}
		 });
		 JButton deleteBeam = new JButton("Delete Beam");
		 deleteBeam.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				new BeamInputScreen(trussScreen,true);
			}
		 });
		 JButton solveSys = new JButton("Solve System");
		 solveSys.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				trussScreen.solveSystem();
				trussScreen.reeval();
			}
		 });
		 JButton perfectJoints = new JButton("Run Sim");
		 perfectJoints.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent arg0)
			{
				trussScreen.perfectJoints();
				
			}
		 });
		 
		 main.setLayout(new BorderLayout());
		 JPanel buttonPanel = new JPanel();
		 buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		 buttonPanel.add(addJoint);
		 buttonPanel.add(editJoint);
		 buttonPanel.add(addBeam);
		 buttonPanel.add(deleteBeam);
		 buttonPanel.add(solveSys);
		 buttonPanel.add(perfectJoints); 
		 main.add(buttonPanel,BorderLayout.SOUTH);

	}

}
