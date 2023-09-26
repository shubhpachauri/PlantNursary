package com.amdocs.exp;

public class nursaryException extends Exception {
	
	String mssg;
	public nursaryException(String str)
	{
		mssg = str;
	}
	
	public String toString()
	{
		return ("Somethign event wrong : "+mssg);
	}
	
	

}
