package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends myAbstractSpaceship{
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private Set<? extends CrewMember> crewMembers;
	private List<Weapon> weapons;
	private int numberOfTechnicians;
	private int totalFirePower = 10;
	final int annualMaintenanceCostBasic = 5000;
	private int annualMaintenanceCost = 0;



	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, 0, crewMembers, 0);

		this.weapons = weapons;
		this.numberOfTechnicians = numberOfTechnicians;
		this.firePower = getFirePower();
		this.annualMaintenanceCost = getAnnualMaintenanceCost();
	}

	public List<Weapon> getWeapons(){
		return this.weapons;
	}

	@Override
	public int getFirePower() {
		int total = 10 ;
		for(Weapon weapon : this.weapons){
			total += weapon.getFirePower();
		}
		return total;
	}

	public int getNumberOfTechnicians() {
		return numberOfTechnicians;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		double weaponsCost = 0;
		for(Weapon weapon : this.weapons){
			weaponsCost += weapon.getAnnualMaintenanceCost();
		}

		int total = 0;

		weaponsCost =(weaponsCost)*(1-this.numberOfTechnicians*0.1);
		total += (int)(Math.floor(weaponsCost));
		total += this.annualMaintenanceCostBasic;
		return total;
	}

	@Override
	public String toString() {
		return "Bomber\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tWeaponArray=" + this.getWeapons()+ "\n"+
				"\tNumberOfTechnicians="+ this.getNumberOfTechnicians();
	}
}
