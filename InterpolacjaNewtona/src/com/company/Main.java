package com.company;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Podaj liczbe argumentów: ");
		final int n = scan.nextInt();

		int x[] = new int[n];
		double y[] = new double[n];
		double fx;
		for(int i=0;i<n;i++){
			System.out.print("x"+i+" = ");
			x[i] = scan.nextInt();
			System.out.print("y(x"+i+") = ");
			y[i] = scan.nextDouble();
			System.out.println();
		}

		StringBuilder wezly= new StringBuilder();


		System.out.print("N(x) = "+y[0]);

		for(int wezel=0;wezel<n-1;wezel++){
			for(int i=1+wezel;i<n;i++){
				y[i] = (y[wezel] - y[i]) / (x[wezel] - x[i]);
			}
			fx = y[wezel+1];
			if(x[wezel]>=0)
				wezly.append("(x - ").append(x[wezel]).append(")");
			else
				wezly.append("(x + ").append(x[wezel]*-1).append(")");
			System.out.print(fx + wezly.toString() +" + ");

		}
	}
}
