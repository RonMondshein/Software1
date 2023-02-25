package il.ac.tau.cs.sw1.ex9.starfleet;
import javax.xml.transform.Source;
import java.util.Objects;
import java.util.Set;
import java.util.Comparator;

abstract public class myAbstractSpaceship implements Spaceship{
    public String name;
    public int commissionYear;
    public float maximalspeed;
    public int firePower;
    public Set<? extends CrewMember> crewMembers;
    public int annualMaintenanceCost;
    public myAbstractSpaceship(String name, int commissionYear, float maximalspeed, int firePower, Set<? extends CrewMember> crewMembers,
                               int annualMaintenanceCost  ){
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalspeed = maximalspeed;
        this.firePower = firePower;
        this.crewMembers = crewMembers;
        this.annualMaintenanceCost = annualMaintenanceCost;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public int getCommissionYear() {
        return this.commissionYear;
    }

    @Override
    public float getMaximalSpeed() {
        return this.maximalspeed;
    }

    @Override
    public int getFirePower() {
        return this.firePower;
    }

    @Override
    public Set<? extends CrewMember> getCrewMembers() {
        return this.crewMembers;
    }

    @Override
    public int getAnnualMaintenanceCost() {
        return this.annualMaintenanceCost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof myAbstractSpaceship that)) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
