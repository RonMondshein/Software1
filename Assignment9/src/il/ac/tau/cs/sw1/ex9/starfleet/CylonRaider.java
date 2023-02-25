package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter{
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private Set<? extends CrewMember> crewMembers;
	private List<Weapon> weapons;
	final int annualMaintenanceCostBasic = 3500;
	private int annualMaintenanceCost = 0;

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.weapons = weapons;
		super.setWeapons(this.weapons);
		this.maximalspeed = maximalSpeed;
		this.crewMembers = crewMembers;


	}

	@Override
	public int getAnnualMaintenanceCost() {
		int total = 0;
		for(Weapon weapon: super.getWeapons()){
			total += weapon.getAnnualMaintenanceCost();
		}
		total += this.annualMaintenanceCostBasic;
		total += (int)(this.maximalspeed * 1200);
		total += super.getCrewMembers().size() * 500;
		this.annualMaintenanceCost = total;
		return total;
	}


	@Override
	public String toString() {
		return "CylonRaider\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tWeaponArray=" + this.getWeapons();
	}
}
