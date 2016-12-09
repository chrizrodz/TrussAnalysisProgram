package com.truss;

public class Beam
{
	private Joint a;
	private Joint b;
	
	public float force;
	
	public Beam(Joint a, Joint b)
	{
		this.a = a;
		this.b = b;
		a.attachedBeams.add(this);
		b.attachedBeams.add(this);
	}
	
	public Joint startJoint()
	{
		return a;
	}
	public Joint endJoint()
	{
		return b;
	}

	public float beamLength()
	{
		return (float) Math.sqrt(((a.x-b.x)*(a.x-b.x)) + ((a.y-b.y)*(a.y-b.y)));
	}
}
