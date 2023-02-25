package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item>{
    int capacity;
    List<Item> lst;

    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        lst = lst1;
    }

    public static class Item {
        double weight, value;
        Item(double w, double v) {
            weight = w;
            value = v;
        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }
    }

    @Override
    public Iterator<Item> selection() {
        for (int i = 0; i < lst.size() - 1; i++) {
            for (int j = i; j < lst.size() ; j++) {
                if (lst.get(i).value / lst.get(i).weight < lst.get(j).value / lst.get(j).weight) {
                    Item temp = lst.get(i);
                    lst.set(i, lst.get(j));
                    lst.set(j, temp);
                }
            }
        }
        return lst.iterator();
    }

    private Double sum (List<Item> lst){
        double sum = 0;
        for (Item element : lst){
            sum += element.weight;
        }
        return sum;
    }


    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        if (sum(candidates_lst) + element.weight > capacity){
            double part = (capacity - sum(candidates_lst))/ element.weight;
            element.value = part*(element.value);
            element.weight = part*(element.weight);
        }
        return true;

    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {
        candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {
        return sum(candidates_lst) == capacity;
    }
}
