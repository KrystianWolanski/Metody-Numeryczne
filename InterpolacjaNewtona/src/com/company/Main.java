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

		System.out.println("Współczynniki wielomianu to:");
		System.out.println(y[0]);
        int copy_n = n;
        for(int wezel=0;wezel<n-1;wezel++){
			fx = y[0];
			for(int i=0;i<copy_n-1;i++){
				y[i] = (fx - y[i+1]) / (x[wezel] - x[i+1+wezel]);
			}
			copy_n--;
			System.out.println(y[0]);

		}
    }
}
