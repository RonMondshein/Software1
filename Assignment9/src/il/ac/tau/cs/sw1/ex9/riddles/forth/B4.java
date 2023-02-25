package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.List;
import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class B4 implements Iterator<String> {
    private static String[] strings;
    private static int k;
    private static int count;
    private static String s;
    private Iterator<String> it;
    private List<String> lst;


    public B4(String[] strings, int k){
        this.strings =strings;
        lst = Arrays.asList(strings);
        this.it = lst.iterator();
        this.k = k;
        this.count = 0;

    }

    @Override
    public boolean hasNext() {
        if (count == 0){
            return it.hasNext();
        }
        else if (count < k*this.lst.size()){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String next() {
        s= lst.get(count%this.lst.size());
        count++;
        return s;

    }
	
}
