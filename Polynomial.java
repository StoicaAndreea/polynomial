package com.company;
import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Integer> polynomial;
    private ArrayList<Integer> roots;
    private ArrayList<Integer> multiplicity;
    private int degree;

    public Polynomial(){       //implicit constructor
        degree=-1;
        roots= new ArrayList<>();
        multiplicity= new ArrayList<>();
        polynomial= new ArrayList<>();
    }
    /*
    public Polynomial(ArrayList<Integer> p, int n){        //constructor with parameters
        degree=n;
        polynomial= new ArrayList<Integer>(p);

    }*/

    public Polynomial(Polynomial p){      //copy constructor
        degree=p.getDegree();
        polynomial=new ArrayList<>(p.getPolynomial());
        multiplicity= new ArrayList<>();
        roots= new ArrayList<>();
        this.arrange();
        this.setRoots();
    }

    public Polynomial(ArrayList<Integer> p){       //constructor with one parameter
        degree=p.size()-1;
        polynomial= new ArrayList<>(p);
        multiplicity= new ArrayList<>();
        roots= new ArrayList<>();
        this.arrange();
        this.setRoots();
    }

    public void arrange(){      //function that cleans a polynomial(gets rid of null values)
        int i=getDegree();
        while(i>=0 && polynomial.get(i)==0){
            polynomial.remove(i);
            i--;
        }
        degree=i;
    }

    public ArrayList<Integer> getPolynomial(){        //function that retrieves the array
        return polynomial;
    }

    public Integer getEl(int i){       //function that retrieves an element from a position
        return polynomial.get(i);
    }

    public int getDegree(){     //function that retrieves the degree
        return degree;
    }

    public void setEmptyPolynomial(Polynomial p){      //function to change the polynomial
        this.clearPolynomial();
        for(int i=0;i<=p.getDegree();i++){
            this.addEl(p.getEl(i));
        }
        degree=p.getDegree();
        this.arrange();
        this.setRoots();
    }

    public void setEl(int i, Integer val){      //function that changes an element from index
        polynomial.set(i,val);
    }

    public void addEl(Integer val){     //function that adds an element
        polynomial.add(val);
        degree++;
    }

    public void addRoot(Integer val){       //function that adds a new root to the list of roots
        roots.add(val);
    }

    public void clearPolynomial(){      //function that clears a polynomial
        polynomial.clear();
        degree=-1;
    }

    public ArrayList<Integer> getRoots(){       //function that returns the roots
        return roots;
    }

    public int noOfRoots(){             //function that returns the number of roots a polynomial has
        return roots.size();
    }

    public int getRoot(int i){      //function that returns a root from an index
        return roots.get(i);
    }

    public int getMultiplicity(int i){      //function that returns the multiplicity of the root from index
        return multiplicity.get(i);
    }

    public ArrayList<Integer>getMultiplicities(){       //function that returns the multiplicities of the roots
        return this.multiplicity;
    }
    public ArrayList<Integer> getDivisors(){        //function that finds the divisors of the element of the polynomial with the degree 0
        ArrayList<Integer> res= new ArrayList<>();
        if(this.getDegree()>-1){
            int n=this.getEl(0);
            for(int i=1; i<=Math.sqrt(Math.abs(n));i++){
                if(n%i==0 && !res.contains(i)){
                    if (n/i == i)
                    {res.add(i);}
                    else{
                        res.add(i);
                        if(!res.contains(n/i))
                        res.add(n/i);
                    }
                }
            }
        }
        return res;
    }

    public boolean isRoot(int el, ArrayList<Integer>p){     //utility function that verifies if an element is root for a polynomial
        int sum=0;
        sum=p.get(0);
        for(int i=1;i<p.size();i++){
            sum+=p.get(i)*Math.pow(el,i);}
        return sum == 0;
    }

     public void setRoots() {       //function that finds the roots and their multiplicities
        ArrayList<Integer> divisors=new ArrayList<>(this.getDivisors());
        for(int el: divisors){
            if (isRoot(el,this.polynomial) && !this.roots.contains(el)){
                this.addRoot(el);
                setMultiplicity(el);
            }
            if (isRoot(-el,this.polynomial) && !this.roots.contains(-el)){
                this.addRoot(-el);
                setMultiplicity(-el);
            }
        }
     }

     public void setMultiplicity(int el){       //function that finds the multiplicity of a root
        int e;
        ArrayList<Integer> pol=new ArrayList<>(this.polynomial);
        int mult = 0;
        int size;
        while ((isRoot(el, pol)) /*&& (pol.size()>0)*/) {
            mult++;
            for (int i = 1; i < pol.size(); i++) {
                e=pol.get(i);
                pol.set(i - 1, e*i);
            }
            size=pol.size() - 1;
            pol.remove(size);
        }
        this.multiplicity.add(mult);
     }
}
