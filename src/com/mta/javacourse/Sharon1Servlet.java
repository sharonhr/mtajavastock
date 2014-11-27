package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Sharon1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		//ex2
		int num1, num2, num3;
		num1=3;
		num2=4; 
		num3=7;
		int result = ((num1+num2)*num3);
		
		String resultStr =new String("<h1> Result of " + "(" + num1 + "+" + num2 + ")" + "*" +num3 + "=" +result+"</h1>");
		
		//EX3
		
		//part1
		double result2;
		double radius;
		radius = 50;
		result2 = Math.pow(radius, 2) * Math.PI;
		String result2Str = new String("Area of circle with radius " + radius + " is " + result2 + " square CM");
		
		//part2
		double oppositeLength;
		double angleB= 30;
		double angleBToRadians;
		int hypotenuse= 50;
		angleBToRadians= Math.toRadians(angleB);
		oppositeLength= Math.sin(angleBToRadians)*hypotenuse;
		String result3Str= new String("Length of opposite where angle B is " + angleB + " degrees and Hypotenuse length is " + hypotenuse +" cm is: "+oppositeLength+" cm");
		
		//part3
		int base=20;
		int exp=13;
		double powerResult= Math.pow(base,exp);
		String result4Str=new String("Power of " + base + " with exp of " + exp + " is: "+powerResult );
		
		String FinalStr= new String(resultStr + "<br>" + result2Str + "<br>" + result3Str + "<br>" + result4Str);
		resp.getWriter().println(FinalStr);
	}
}
