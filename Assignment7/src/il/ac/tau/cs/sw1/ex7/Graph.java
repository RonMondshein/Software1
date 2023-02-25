package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,..., n]

    Graph(int n1, List<Edge> lst1){
        lst = lst1;
        n = n1;
    }

    public static class Edge{
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }
    }

    @Override
    public Iterator<Edge> selection() {

        /*
        Sort edges by 1. weight
        if weight equals then 2. node1
        if weight and node1 equals then 3. node2
         */
        Edge tempEdge, chosenEdge;
        boolean found = false;
        int indexFound = 0;

        for (int i = 0; i < this.lst.size() - 1; i++) {

            found = false;
            chosenEdge = this.lst.get(i);

            for (int j = i + 1; j < this.lst.size(); j++) {
                //order by weight
                if (chosenEdge.weight > this.lst.get(j).weight) {
                    //step 1
                    chosenEdge = this.lst.get(j);
                    found = true;
                    indexFound = j;
                }

            }
            if (found) {
                tempEdge = this.lst.get(i);
                this.lst.set(i, this.lst.get(indexFound));
                this.lst.set(indexFound, tempEdge);
            }
        }
        int weightI = 1;
        found = false;
        indexFound = 0;
        for (int i = 0; i < this.lst.size() - 1; i++) {
            if (this.lst.get(i).weight == this.lst.get(i + 1).weight) {
                weightI++;
            }
            if ((this.lst.get(i).weight != this.lst.get(i + 1).weight)
                    || ((weightI > 1) && (i == this.lst.size() - 2))) {
                if (i == this.lst.size() - 2){
                    i ++;
                }
                for (int j = i - weightI + 1; j < i ; j++) {
                    found = false;
                    chosenEdge = this.lst.get(j);
                    for (int k = j + 1; k < i + 1; k++){
                        if (chosenEdge.node1 > this.lst.get(k).node1) {
                            //step 2
                            chosenEdge = this.lst.get(k);
                            found = true;
                            indexFound = k;
                        }
                    }
                    if (found) {
                        tempEdge = this.lst.get(j);
                        this.lst.set(j, this.lst.get(indexFound));
                        this.lst.set(indexFound, tempEdge);
                    }
                }
                weightI = 1;
            }
        }
        int nodeI = 1;
        found = false;
        indexFound = 0;
        for (int i = 0; i < this.lst.size() - 1; i++) {
            if (this.lst.get(i).node1 == this.lst.get(i + 1).node1) {
                nodeI++;
            }


            if ((this.lst.get(i).node1 != this.lst.get(i + 1).node1)
                    || ((nodeI > 1) && (i == this.lst.size() - 2))) {
                if (i == this.lst.size() - 2) {
                    i++;
                }
            
            for (int j = i - nodeI + 1; j < i; j++) {
                found = false;
                chosenEdge = this.lst.get(j);
                for (int k = j + 1; k < i + 1; k++){
                    if (chosenEdge.node2 > this.lst.get(k).node2) {
                        //step 3
                        chosenEdge = this.lst.get(k);
                        found = true;
                        indexFound = k;
                    }
                }
                if (found) {
                    tempEdge = this.lst.get(j);
                    this.lst.set(j, this.lst.get(indexFound));
                    this.lst.set(indexFound, tempEdge);
                }
              }
                nodeI = 1;
            }

        }


        return lst.iterator();
    }

    public ArrayList<Integer>[] AllEdgesExistArray(List<Edge> candidates_lst){
        /*
        make a array of lists- the array is in the size of the node (n),
        each node have all of the nodes that he have a edge with
         */
        ArrayList<Integer>[] edegesExist = new ArrayList[this.n + 1];
        for (int i = 0; i <= this.n; i++) {
            edegesExist[i] = new ArrayList<Integer>();
        }
        for (Edge edge : candidates_lst) {
            edegesExist[edge.node1].add(edge.node2);
            edegesExist[edge.node2].add(edge.node1);
        }
        return edegesExist;
    }
    public boolean[] isCyclicRec(int node, ArrayList<Integer>[] edegesExist, boolean[] visited){
        visited[node] = true;
        for (int i = 0; i <edegesExist[node].size() ; i++) {

            int neighbour = edegesExist[node].get(i);

            if(!visited[neighbour]){
                isConnectedRec(neighbour, edegesExist, visited);
            }
        }
        return visited;
    }
    public Boolean isCyclic(ArrayList<Integer>[] edegesExist, Edge element){
        boolean[] visited = new boolean[this.n + 1];
        boolean[] find = isCyclicRec(element.node1, edegesExist, visited);
        return find[element.node2];
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
        /*
        checked a-cyclic graph
        input- list of edges that are *acyclic*
        input- Wether element close a circle or not
        */

        ArrayList<Integer>[] edegesExist = AllEdgesExistArray(candidates_lst);
        return (!isCyclic(edegesExist,element));
    }

    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
    }


    public void isConnectedRec(int node, ArrayList<Integer>[] edegesExist, boolean[] visited){
        visited[node] = true;
        for (int i = 0; i <edegesExist[node].size() ; i++) {

            int neighbour = edegesExist[node].get(i);

            if(!visited[neighbour]){
                isConnectedRec(neighbour, edegesExist, visited);
            }
        }
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
        /*
        checked 1. connected 2. span
         */
        ArrayList<Integer>[] edegesExist = AllEdgesExistArray(candidates_lst);
        boolean[] visited = new boolean[this.n + 1];
        isConnectedRec(0, edegesExist, visited);

        for (int i = 0; i <= this.n ; i++) {
            //part 1
            if(!visited[i]){
                return false;
            }
        }

        for (ArrayList<Integer> integers : edegesExist) {
            //part 2
            if (integers.size() == 0) {
                return false;
            }
        }

        return true;
    }
}
