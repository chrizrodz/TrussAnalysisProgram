package com.truss;
import java.awt.*; // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*; // Using Swing's components and containers

import com.truss.TrussScreen;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Main extends JFrame
{

	public static void main(String[] args)
	{
		// Run the GUI codes on the Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Main(); // Let the constructor do the job
			}
		});
	}
	
	
	
	

	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;
	
	public static final int TRUSS_DISPLAY_WIDTH = 500;
	public static final int TRUSS_DISPLAY_HEIGHT = 400;
	public static final int TRUSS_DISPLAY_OFFSET_X = 20;
	public static final int TRUSS_DISPLAY_OFFSET_Y = 20;
	
	
	

	private GUIScreen guiScreen;
	private TrussScreen trussScreen;
	private DrawCanvas canvas;
	
	public Main()
	{
		canvas = new DrawCanvas(); // Construct the drawing canvas
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		setResizable(false);

		setContentPane(canvas);

		trussScreen = new TrussScreen(this);
		guiScreen = new GUIScreen(this,trussScreen);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Truss Analysis");
		setVisible(true);
	}
	
	public void paintNow()
	{
		canvas.paintImmediately(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
	}

	/**
	 * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
	 */
	private class DrawCanvas extends JPanel
	{
		// Override paintComponent to perform your own painting
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); // paint parent's background

			trussScreen.draw(g);
		}
	}

	// The entry main method

}