package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends myAbstractSpaceship{
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private Set<? extends CrewMember> crewMembers;
	private List<Weapon> weapons;
	private int totalFirePower = 10;
	final int annualMaintenanceCostBasic = 2500;
	private int annualMaintenanceCost = 0;

	private int numOfStealthCruiser = 0;
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, 0, crewMembers, 0);
		this.weapons = weapons;
		this.maximalspeed = maximalSpeed;
		this.totalFirePower = getFirePower();
		this.annualMaintenanceCost = getAnnualMaintenanceCost();
		this.totalFirePower = this.getFirePower();
	}

	public List<Weapon> getWeapons(){
		return this.weapons;
	}
	public void addStealthCruiser(){
		this.numOfStealthCruiser++;
	}
	public void setWeapons(List<Weapon> weapons){
		this.weapons = weapons;
	}


	public int getNumOfStealthCruiser(){
		return this.numOfStealthCruiser;
	}


	@Override
	public int getFirePower() {
		int total = 10;
		for(Weapon weapon : this.weapons){
			total += weapon.getFirePower();
		}
		return total;
	}



	@Override
	public int getAnnualMaintenanceCost() {
		int total = this.annualMaintenanceCostBasic;
		for(Weapon weapon : this.weapons){
			total += weapon.getAnnualMaintenanceCost();
		}
		total += (int)(Math.floor(this.maximalspeed * 1000));
		return total;
	}


	@Override
	public String toString() {
		return "Fighter\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tWeaponArray=" + this.getWeapons();
	}
}

