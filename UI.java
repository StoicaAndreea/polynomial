package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public UI(){}

    public void printPolynomial(Polynomial p){       //function to print a polynomial
        System.out.print("P= ");
        if(p.getDegree()==-1)System.out.print(0);
        else
            for (int i=0; i<=p.getDegree(); i++) {
                //if (polynomial[i] != 0) {
                System.out.print(p.getEl(i));
                if (i != 0) {
                    System.out.print("x^" + i);
                }
                if (i != p.getDegree()) {
                    System.out.print(" + ");
                }
            }
        System.out.println();
    }

    public void printRoots(Polynomial p){           //function that prints the roots of a polynimial
        if(p.noOfRoots()>0)
        for(int i=0;i<p.noOfRoots();i++){
            System.out.println("    root: "+p.getRoot(i)+"      multiplicity "+p.getMultiplicity(i));
        }
        else System.out.println("   the polynomial has no roots");
    }

    public static int printMenu(){      //function that prints the menu and receives the option
        System.out.println("\n MENU:");
        System.out.println("    1) give one polynomial and see it's roots and multiplicity");
        System.out.println("    2) give polynomials");
        System.out.println("    3) see the polynomial with the root that has the maximum multiplicity");
        System.out.println("    4) see the polynomials");
        System.out.println("    0) Exit");
        Scanner s=new Scanner(System.in);
        int option;
        try{option=s.nextInt();
            return option;}
        catch(Exception var) {
            System.out.print("Wrong! give integer");
            return printMenu();}
    }

    public Polynomial getPolynomial(){     //function that gets a  polynomial
        ArrayList<Integer> p1 = new ArrayList<>();
        try{
            System.out.print("what degree will the polynomial have?");
            Scanner scn = new Scanner(System.in);
            int degr = scn.nextInt();
                for(int i=0;i<=degr;i++){
                    System.out.print("val for the elem with the degree "+i);
                    p1.add(scn.nextInt());
                }
                Polynomial polin=new Polynomial(p1);
                return polin;
        }
        catch(Exception var) {
            System.out.println("Wrong! give integer");
            return getPolynomial();}
    }

    public void Main(){     //control
        int opt=printMenu();
        Polynomial polin1=new Polynomial();
        PolynomialRepository repo=new PolynomialRepository();
        while(opt!=0){
            switch(opt){
                //case 0: {System.out.println("bye..."); break;}
                case 1: {
                    polin1.setEmptyPolynomial(getPolynomial());
                    printPolynomial(polin1);
                    printRoots(polin1);
                    break;
                }
                case 2: {
                    int sem=0;
                    while(sem!=-1){
                        Polynomial p=new Polynomial();
                        p.setEmptyPolynomial(getPolynomial());
                        sem=repo.add(p);
                        System.out.println("    added a new polynomial");
                    }
                    System.out.println("        THE ADDING HAS STOPPED SINCE YOU ADDED THE EMPTY POLYNOMIAL");
                    break;
                }
                case 3: {
                    Polynomial pol=new Polynomial(repo.elementWithThwMaxMultiplicity());
                    System.out.print("the polynomial is: ");
                    printPolynomial(pol);
                    printRoots(pol);
                    break;
                }
                case 4: {
                    for(int i=0;i<repo.getSize();i++){
                        printPolynomial(repo.getPolynomial(i));
                        printRoots(repo.getPolynomial(i));
                    }
                    break;}
                default: {System.out.println("wrong option");}
            }
            opt=printMenu();
        }
        System.out.println("bye...");

    }
}

