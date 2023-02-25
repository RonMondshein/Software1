package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Comparator;


class mySpaceshipComparator implements Comparator<Spaceship> {

    @Override
    public int compare(Spaceship ship1, Spaceship ship2) {
        if (ship1.getFirePower() != ship2.getFirePower()) {
            return Integer.compare(ship2.getFirePower(), ship1.getFirePower());
        } else if (ship1.getCommissionYear() != ship2.getCommissionYear()) {
            return Integer.compare(ship2.getCommissionYear(), ship1.getCommissionYear());
        } else {
            return ship1.getName().compareTo(ship2.getName());
        }


    }
}

