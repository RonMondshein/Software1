package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Comparator;
import java.util.Map;

class myOfficerRankComparator implements Comparator<Map.Entry<OfficerRank, Integer>> {

    @Override
    public int compare(Map.Entry<OfficerRank, Integer> rank1, Map.Entry<OfficerRank, Integer> rank2) {
        if (rank1.getValue() != rank2.getValue()) {
            return Integer.compare(rank1.getValue(), rank2.getValue());
        } else {
            return rank1.getKey().compareTo(rank2.getKey());
        }
    }
}
