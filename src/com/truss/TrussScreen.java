package com.truss;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import Jama.Matrix;

public class TrussScreen
{
	public ArrayList<Joint> joints = new ArrayList<Joint>();
	public ArrayList<Beam> beams = new ArrayList<Beam>();
	
	private int center_x;
	private int center_y;
	private float scale_factor;
	private float max_size;
	
	private final float step_size = 0.01f;
	
	private Main parent;
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(Main.TRUSS_DISPLAY_OFFSET_X-5, Main.TRUSS_DISPLAY_OFFSET_Y-5, Main.TRUSS_DISPLAY_WIDTH+10, Main.TRUSS_DISPLAY_HEIGHT+10);
		for (Joint joint : joints)
		{
			g.setColor(Color.BLACK);
			int x_cent = Main.TRUSS_DISPLAY_OFFSET_X+getNewPointX(joint.x);
			int y_cent =  Main.TRUSS_DISPLAY_HEIGHT+Main.TRUSS_DISPLAY_OFFSET_Y-getNewPointY(joint.y);
			g.fillOval(x_cent-2,y_cent-2, 5, 5);
			g.setColor(Color.BLUE);
			String jointInfo = joint.ID;
			if(joint.isFixedSupport)
			{
				jointInfo = jointInfo.concat(" (support)");
			}
			else if(joint.isRoller)
			{
				jointInfo = jointInfo.concat(" (roller)");
			}
			if(joint.ext_force>0)
			{
				jointInfo = jointInfo.concat(" (F="+joint.ext_force +"lb @ "+joint.force_angle+" deg)");
			}
			g.drawString(jointInfo, x_cent+10, y_cent+10);
			
		}
		for (Beam beam: beams)
		{
			g.setColor(Color.BLACK);
			int x1 = Main.TRUSS_DISPLAY_OFFSET_X+getNewPointX(beam.startJoint().x);
			int y1 = Main.TRUSS_DISPLAY_HEIGHT+Main.TRUSS_DISPLAY_OFFSET_Y-getNewPointY(beam.startJoint().y);
			int x2 = Main.TRUSS_DISPLAY_OFFSET_X+getNewPointX(beam.endJoint().x);
			int y2 = Main.TRUSS_DISPLAY_HEIGHT+Main.TRUSS_DISPLAY_OFFSET_Y-getNewPointY(beam.endJoint().y);
			g.drawLine(x1, y1,x2,y2);
			
			g.setColor(Color.RED);
			g.drawString(""+beam.force, (x1+x2) / 2, (y1+y2) / 2);
			
		}
	}
	
	private int getNewPointX(float original_point_x)
	{
		return (int)(((original_point_x-center_x)*scale_factor)+(Main.TRUSS_DISPLAY_WIDTH/2.0));
	}
	private int getNewPointY(float original_point_y)
	{
		return (int)(((original_point_y-center_y)*scale_factor)+(Main.TRUSS_DISPLAY_HEIGHT/2.0));		
	}
	
	
	private void evaluateCenterAndScaling()
	{
		float max_x_dist=joints.get(0).x;
		float max_y_dist=joints.get(0).y;
		float min_x_dist=joints.get(0).x;
		float min_y_dist=joints.get(0).y;
		
		for (Joint joint : joints)
		{
			if(joint.x>max_x_dist)
			{
				max_x_dist = joint.x;
			}
			else if(joint.x<min_x_dist)
			{
				min_x_dist = joint.x;
			}
			if(joint.y>max_y_dist)
			{
				max_y_dist = joint.y;
			}
			else if(joint.y<min_y_dist)
			{
				min_y_dist = joint.y;
			}
		}
		
		if(joints.size() == 1)
		{
			scale_factor = 1;
		}
		else if((max_x_dist-min_x_dist)/(float)Main.TRUSS_DISPLAY_WIDTH>(max_y_dist-min_y_dist)/(float)Main.TRUSS_DISPLAY_HEIGHT )
		{
			scale_factor = (max_x_dist-min_x_dist)/(float)Main.TRUSS_DISPLAY_WIDTH;
		}
		else
		{
			scale_factor = (max_y_dist-min_y_dist)/(float)Main.TRUSS_DISPLAY_HEIGHT;
		}
		scale_factor = 1/scale_factor;
		max_size = (max_x_dist-min_x_dist);
		center_x = (int)((max_x_dist+min_x_dist) / 2.0);
		center_y = (int)((max_y_dist+min_y_dist) / 2.0);
		
	}
	
	public TrussScreen(Main parent)
	{
		this.parent = parent;
		
		Joint a = new Joint();
		a.setPos(0, 0);
		a.isFixedSupport = true;
		addJoint(a);
		Joint b = new Joint();
		b.isRoller=true;
		b.setPos(28, 0);
		addJoint(b);
		Joint d = new Joint();
		d.ext_force = 1000;
		d.force_angle = 90;
		d.setPos(13, 10);
		addJoint(d);
		Joint c = new Joint();
		c.setPos(14, -7);
		addJoint(c);
		Joint e = new Joint();
		e.setPos(14, 0);
		addJoint(e);
		
		
		
		
//		Joint a = new Joint();
//		a.setPos(0, 0);
//		a.isFixedSupport = true;
//		addJoint(a);
//		Joint b = new Joint();
//		b.isRoller=true;
//		b.setPos(28, 0);
//		addJoint(b);
//		Joint d = new Joint();
//		d.ext_force = 1000;
//		d.force_angle = 90;
//		d.setPos(13, 10);
//		addJoint(d);
//		Joint c = new Joint();
//		c.setPos(14, -7);
//		addJoint(c);
//		Joint e = new Joint();
//		e.setPos(23, 6.5f);
//		addJoint(e);
//		
//		beams.add(new Beam(a,d));
//		beams.add(new Beam(d,e));
//		beams.add(new Beam(e,b));
//		beams.add(new Beam(a,c));
//		beams.add(new Beam(d,c));
//		beams.add(new Beam(e,c));
//		beams.add(new Beam(b,c));
		
		
//		Joint a = new Joint();
//		a.setPos(0, 0);
//		a.isFixedSupport = true;
//		addJoint(a);
//		Joint b = new Joint();
//		b.isRoller=true;
//		b.setPos(28, 0);
//		addJoint(b);
//		Joint d = new Joint();
//		d.ext_force = 100;
//		d.force_angle = 90;
//		d.setPos(13, 10);
//		addJoint(d);
//		Joint c = new Joint();
//		c.setPos(8, 9);
//		addJoint(c);
//		Joint e = new Joint();
//		e.setPos(10, -3);
//		addJoint(e);
//		Joint f = new Joint();
//		f.setPos(16, -6);
//		addJoint(f);
//		Joint g = new Joint();
//		g.setPos(20, 8);
//		addJoint(g);
//		
//		beams.add(new Beam(a,c));
//		beams.add(new Beam(a,e));
//		beams.add(new Beam(c,e));
//		beams.add(new Beam(c,d));
//		beams.add(new Beam(d,e));
//		beams.add(new Beam(d,f));
//		beams.add(new Beam(e,f));
//		beams.add(new Beam(d,g));
//		beams.add(new Beam(f,g));
//		beams.add(new Beam(f,b));
//		beams.add(new Beam(b,g));
		
		
		
		

		solveSystem();
		reeval();
		
		//perfectJoints();
		System.out.println(max_force);
		
	}
	public void addJoint(Joint j)
	{
		joints.add(j);

		reeval();
	}


	public void addBeam(Joint a, Joint b)
	{
		for(Beam beam : beams)
		{
			String beamID = beam.startJoint().ID+beam.endJoint().ID;
			if(beamID.equals(a.ID+b.ID)||beamID.equals(b.ID+a.ID))
				return;
		}
		beams.add(new Beam(a,b));
		
		reeval();
		
	}
	

	public void deleteBeam(Joint a, Joint b)
	{
		for(Beam beam : beams)
		{
			String beamID = beam.startJoint().ID+beam.endJoint().ID;
			if(beamID.equals(a.ID+b.ID)||beamID.equals(b.ID+a.ID))
			{
				beams.remove(beam);
				break;
			}
		}
		reeval();
	}
	
	float max_force;
	float max_length;
	float mean_force_length;
	
	public void solveSystem()
	{
		Joint roller = null;
		Joint support = null;
		
		max_force = 0;
		max_length = 0;
		mean_force_length = 0;
		
		ArrayList<Float> forces = new ArrayList<Float>();
		ArrayList<Float> force_angles = new ArrayList<Float>();
		ArrayList<Float> forces_x = new ArrayList<Float>();
		ArrayList<Float> forces_y = new ArrayList<Float>();
		

		double[][] lhsArray = new double[3+(joints.size()*2)][3+beams.size()];
        //[Equations],[Variables]
		//0th equation is moment, first is sum y, second is sum x
		//0th variable is roller y, first is support y, second is support x
		double[] rhsArray = new double[3+(joints.size()*2)];
		//Constants. Should all be zero except for 0,1,2
       

		for(int i = 0; i<joints.size();i++)
		{
			if(joints.get(i).ext_force!=0)
			{
				forces.add(joints.get(i).ext_force);
				force_angles.add(joints.get(i).force_angle);
				forces_x.add(joints.get(i).x);
				forces_y.add(joints.get(i).y);
			}
			
			if(joints.get(i).isFixedSupport)
			{
				support = joints.get(i);
				
				
			}
			else if(joints.get(i).isRoller)
			{
				roller = joints.get(i);
			}
		}
		float moment = 0;
		float force_sum_y = 0;
		float force_sum_x = 0;
		for(int i = 0; i < forces.size(); i++)
		{
			force_sum_x += forces.get(i)*Math.cos((Math.PI/180)*force_angles.get(i));
			force_sum_y += forces.get(i)*Math.sin((Math.PI/180)*force_angles.get(i));
			
			moment+=(forces_x.get(i)-support.x)*forces.get(i)*Math.sin((Math.PI/180)*force_angles.get(i));
			moment+=(forces_y.get(i)-support.y)*forces.get(i)*Math.cos((Math.PI/180)*force_angles.get(i));	
		}
		;
		
		//The y reaction of the roller
		lhsArray[1][0] = 1;

		//The y reaction of support
		lhsArray[1][1] = 1;
		
		//The x reaction of support
		lhsArray[2][2] = 1;
		
		
		lhsArray[0][0] = roller.x-support.x;
		
		rhsArray[0] = moment;
		rhsArray[1] = force_sum_y;
		rhsArray[2] = force_sum_x;
		
		
		//Y sum then x sum;
		for(int i =0; i<joints.size();i++)
		{
			if(joints.get(i).isRoller)
			{
				lhsArray[3+(i*2)][0]=1;
			}
			else if(joints.get(i).isFixedSupport)
			{
				lhsArray[3+(i*2)][1]=1;
				lhsArray[4+(i*2)][2]=1;	
			}
			rhsArray[3+(i*2)] = joints.get(i).ext_force * Math.sin((Math.PI/180)*joints.get(i).force_angle);
			rhsArray[4+(i*2)] = joints.get(i).ext_force * Math.cos((Math.PI/180)*joints.get(i).force_angle);
			
			for(Beam b : joints.get(i).attachedBeams)
			{
				lhsArray[3+(i*2)][3+findBeam(b)] = slopeOfBeamY(b,joints.get(i));
				lhsArray[4+(i*2)][3+findBeam(b)] = slopeOfBeamX(b,joints.get(i));				
			}
		}
		
        double[][] lhs2Array = lhsArray;
        double[] rhs2Array = rhsArray;
        //Creating Matrix Objects with arrays
        Matrix lhs2 = new Matrix(lhs2Array);
        Matrix rhs2 = new Matrix(rhs2Array, 3+(joints.size()*2));

        //Calculate Solved Matrix
        Matrix ans = lhs2.solve(rhs2);
		
        
        System.out.println("y react of roller " + ans.get(0, 0));
        System.out.println("y react of support " + ans.get(1, 0));
        System.out.println("x react of support " + ans.get(2, 0));
        for(int i = 0; i < beams.size(); i++)
        {
        	float ans_force = (float) ans.get(i+3,0);
        	beams.get(i).force = ans_force;
        	if(Math.abs(ans_force)>max_force) max_force = Math.abs(ans_force);
        	mean_force_length += Math.abs(ans_force);
        	max_length += beams.get(i).beamLength(); 
        }
        mean_force_length = mean_force_length*max_length;
		
		System.out.println(max_force);
	}
	
	public void perfectJoints()
	{
		new Thread() {
			public void run(){
				boolean isLooking = true;
				while (isLooking)
				{
					isLooking=false;
					for(Joint j : joints)
					{
						if(!j.isFixedSupport&&!j.isRoller)
						{
							if(j.ext_force==0)
							{
								float original_x = j.x;
								float originalMax = max_force;
								float originalMean = mean_force_length;
								j.x+=max_size*step_size;
								solveSystem();
								float rightMax = max_force;
								float rightMean = mean_force_length;
								j.x=original_x;
								j.x-=max_size*step_size;
								solveSystem();
								float leftMax = max_force;
								float leftMean = mean_force_length;
								j.x = original_x;
								if(rightMax == originalMax && leftMax >= originalMax)
								{
									if(rightMean<originalMean)
									{
										j.x+=max_size*step_size;
										solveSystem();
										isLooking = true;
									}
								}
								else if(leftMax == originalMax && rightMax >= originalMax)
								{
									if(leftMean<originalMean)
									{
										j.x-=max_size*step_size;
										solveSystem();
										isLooking = true;							
									}
								}
								else if(rightMax<originalMax)
								{
									j.x+=max_size*step_size;
									solveSystem();
									isLooking = true;
								}
								else if(leftMax<originalMax)
								{
									j.x-=max_size*step_size;
									solveSystem();
									isLooking = true;
								}
							}
							
							
							
							solveSystem();
//							try
//							{
//								Thread.sleep(100);
//							}
//							catch (InterruptedException e)
//							{
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							reeval();
//							parent.paintNow();
							
							
							
							float original_y = j.y;
							float originalMax = max_force;
							float originalMean = mean_force_length;
							j.y+=max_size*step_size;
							solveSystem();
							float upMax = max_force;
							float upMean = mean_force_length;
							j.y=original_y;
							j.y-=max_size*step_size;
							solveSystem();
							float downMax = max_force;
							float downMean = mean_force_length;
							j.y = original_y;
							
							if(upMax == originalMax && downMax >= originalMax)
							{
								if(upMean<originalMean)
								{
									j.y+=max_size*step_size;
									solveSystem();
									isLooking = true;
								}
							}
							else if(downMax == originalMax && upMax >= originalMax)
							{
								if(downMean<originalMean)
								{
									j.y-=max_size*step_size;
									solveSystem();
									isLooking = true;							
								}
							}
							else if(upMax<originalMax)
							{
								j.y+=max_size*step_size;
								solveSystem();
								isLooking = true;
							}
							else if(downMax<originalMax)
							{
								j.y-=max_size*step_size;
								solveSystem();
								isLooking = true;
							}
							
							
							
							
							
							solveSystem();
							try
							{
								Thread.sleep(100);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							reeval();
							parent.paintNow();
							
							
							
						}
					}
				}
			}
		}.run();
	}
	
	private void iterateVertical(Joint j,int sign)
	{
		float originalMax = max_force;
		j.y+=max_size*step_size * (float)sign;
		solveSystem();
		if(max_force<originalMax)
			iterateVertical(j,sign);
		else
			j.y-=max_size*step_size * (float)sign;
	}
	
	private void iterateHorizontal(Joint j,int sign)
	{
		float originalMax = max_force;
		j.x+=max_size*step_size * (float)sign;
		solveSystem();
		if(max_force<originalMax)
			iterateHorizontal(j,sign);
		else
			j.x-=max_size*step_size * (float)sign;
	}


	private double slopeOfBeamX(Beam b,Joint j)
	{
		float rise = b.endJoint().y - b.startJoint().y;
		float run = Math.abs(b.endJoint().x - b.startJoint().x);
		float sign = 0;
		if(j.ID.equals(b.startJoint().ID))
		{
			sign = (b.endJoint().x>j.x)?(1):(-1);
		}
		else
		{
			sign = (b.startJoint().x>j.x)?(1):(-1);
		}

		return sign*run/Math.sqrt((rise*rise) + (run*run));
		
	}
	private double slopeOfBeamY(Beam b,Joint j)
	{
		float rise = Math.abs(b.endJoint().y - b.startJoint().y);
		float run = b.endJoint().x - b.startJoint().x;
		
		float sign = 0;
		if(j.ID.equals(b.startJoint().ID))
		{
			sign = (b.endJoint().y>j.y)?(1):(-1);
		}
		else
		{
			sign = (b.startJoint().y>j.y)?(1):(-1);
		}
		return sign*rise/Math.sqrt((rise*rise) + (run*run));
		
	}

	private int findBeam(Beam b)
	{
		for(int i = 0; i<beams.size();i++)
		{
			String beamID = beams.get(i).startJoint().ID+beams.get(i).endJoint().ID;
			if(beamID.equals(b.startJoint().ID+b.endJoint().ID)||beamID.equals(b.endJoint().ID+b.startJoint().ID))
				return i;
		}
		return -1;
	}
	
	public void reeval()
	{

		evaluateCenterAndScaling();
		parent.repaint();
		
	}


}
