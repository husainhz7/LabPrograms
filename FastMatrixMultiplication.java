package com.zhcet.husain;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Multiply extends Thread{
    int x;
    Multiply(int a)
    {
        x=a;
    }

    public void run(){
        for(int j = 0; j<Main.p; j++){
            Main.matC[x][j] = 0;
            for(int k = 0; k < Main.q;k++)
            {
                Main.matC[x][j] += Main.matA[x][k] * Main.matB[k][j];
            }
        }
    }
}


public class Main {

    static int p;
    static int q;
    static int[][] matA;
    static int[][] matB;
    static int[][] matC;

    public static void main(String[] args) {
        ArrayList<Multiply> list = new ArrayList<Multiply>();
        Scanner s = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter p and q");
        p = s.nextInt();
        q = s.nextInt();
        matA = new int[p][q];

        for (int i = 0; i < p; i++)
        {
            for(int j = 0; j < q; j++)
            {
                matA[i][j] = rand.nextInt(10);
            }
        }
        System.out.println("Enter m and n");
        int m = s.nextInt();
        int n = s.nextInt();
        matB = new int[m][n];
        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                matB[i][j] = rand.nextInt(10);
            }
        }

        if(q!=m)
        {
            System.out.println("We can't do the matrix product!");
            System.exit(-1);
        }

        matC = new int[p][n];

        //normal
        long startTime = System.nanoTime();
        for(int i = 0; i < p; i++)
        {
            for(int j = 0; j < n; j++)
            {
                matC[i][j] = 0;
                for(int k = 0; k < q;k++)
                {
                    matC[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }
        for(int i = 0; i < p; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(matC[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        long duration = System.nanoTime() - startTime;
        System.out.println("Normal time: "+duration+" nanoseconds");

        //MultiThreaded
        for(int i = 0; i < p; i++){
            Multiply thread = new Multiply(i);
            list.add(thread);
        }
        startTime = System.nanoTime();
        for(Multiply a: list){
            a.setPriority(1);
            a.start();
        }

        for(int i = 0; i < p; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(matC[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        duration = System.nanoTime() - startTime;
        System.out.println("Multithredead time: "+duration+" nanoSeconds");

    }
}
