package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends myAbstractSpaceship{
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private Set<? extends CrewMember> crewMembers;
	private int annualMaintenanceCost;
	private int cargoCapacity;
	private int passengerCapacity;
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, 10,crewMembers, 0);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getCargoCapacity(){
		return this.cargoCapacity;
	}

	public int getPassengerCapacity(){
		return this.passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 3000 + 5 * this.cargoCapacity + 3 * this.passengerCapacity;
	}

	@Override
	public String toString() {
		return "TransportShip\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tCargoCapacity=" + this.getCargoCapacity() +"\n"+
				"\tPassengerCapacity="+ this.getPassengerCapacity();
	}
}
