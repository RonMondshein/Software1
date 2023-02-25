package il.ac.tau.cs.sw1.ex9.riddles.second;

import java.util.Random;

public class B2 extends A2{
public boolean randomBool;

    public B2(Boolean randomBool){
        this.randomBool = randomBool;

    }
    public B2(){
        this.randomBool = true;

    }
	public B2 getA(Boolean randomBool){
        return new B2(randomBool);
    }

    public String foo(String s){
        if(this.randomBool) {
            return s.toUpperCase();
        }
        else {
            return s.toLowerCase();

        }
    }
}
