package com.truss;

import java.util.ArrayList;

public class Joint
{
	private static boolean[] freespots = new boolean[32];
	public static int nextFreeIndex()
	{
		for(int i = 0; i<freespots.length;i++)
		{
			if(!freespots[i])
			{
				freespots[i] = true;
				return i;
			}
		}
		return -1;
	}
	
	public float x;
	public float y;
	public ArrayList<Beam> attachedBeams = new ArrayList<Beam>();
	
	public final String ID;
	
	public boolean isRoller;
	public boolean isFixedSupport;
	
	public float y_react;
	public float x_react;
	
	public float ext_force;
	public float force_angle;
	
	
	public Joint()
	{
		ID = "J"+nextFreeIndex();
	}
	
	public void setPos(float x2, float y2)
	{
		
		this.x = x2;
		this.y = y2;
	}
	
	
}
