package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter {
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private Set<? extends CrewMember> crewMembers;
	private List<Weapon> weapons;
	final int annualMaintenanceCostBasic = 4000;
	private int annualMaintenanceCost = 0;



	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.weapons = weapons;
		this.crewMembers = crewMembers;
		this.maximalspeed = maximalSpeed;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int total = 0;
		for(Weapon weapon: super.getWeapons()){
			total += weapon.getAnnualMaintenanceCost();
		}
		total += super.getCrewMembers().size() * 500;
		total += (int)(this.maximalspeed * 500);
		total += this.annualMaintenanceCostBasic;
		this.annualMaintenanceCost = total;
		return total;
	}

	@Override
	public String toString() {
		return "ColonialViper\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tWeaponArray=" + this.getWeapons();
	}
}
