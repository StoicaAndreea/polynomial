package com.company;
import java.util.ArrayList;
import java.util.Collections;

public class PolynomialRepository {
    private ArrayList<Polynomial> polynomials;
    private int size;

    public PolynomialRepository(){      //implicit constructor
        this.polynomials=new ArrayList<>();
        size=0;
    }

    public PolynomialRepository(ArrayList<Polynomial> arr){     //constructor that uses a list of polynomials
        this.polynomials=new ArrayList<>(arr);
        size=arr.size();
    }

    public int getSize(){       //function that returns the size of the repository
        return size;
    }

    public ArrayList<Polynomial> getPolynomials(){      //function that returns the vector of polynomials
        return polynomials;
    }

    public Polynomial getPolynomial(int i){     //function that returns the polynomial from an index
        return polynomials.get(i);
    }
    public int add(Polynomial p){       //function that adds a new polynomial into the repository if the polynomial is not null
        if(p.getDegree()!=-1){
            this.polynomials.add(p);
            this.size++;
            return 0;
        }
        return -1;
    }

    public Polynomial elementWithThwMaxMultiplicity(){      //function that returns the polynomial with the root that has the biggest multiplicity
        Polynomial max= new Polynomial();
        int mult=0;
        int mm=0;
        for(Polynomial polyn:polynomials){
            for (int i=0;i< polyn.getMultiplicities().size();i++)
                if (polyn.getMultiplicity(i)>mm) mm=polyn.getMultiplicity(i);
            if (mm>mult){
                max.setEmptyPolynomial(polyn);
                mult=mm;
            }
        }
        return max;
    }
}
