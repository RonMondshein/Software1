package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3{
public String message;
    public B3(String s){
        super(s);
        this.message = s;

    }
    @Override
    public void foo(String s) throws B3 {
        if (s.equals(this.message)) {
            throw new B3(this.message);
        }
    }

    public String getMessage() {
        return this.message;
    }
	
}