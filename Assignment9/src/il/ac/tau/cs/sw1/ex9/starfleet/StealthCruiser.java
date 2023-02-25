package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {
	private String name;
	private int commissionYear;
	private float maximalspeed;
	private int annualMaintenanceCost = 0;
	private Set<? extends CrewMember> crewMembers;
	private List<Weapon> weapons;
	public static List<StealthCruiser> stealth_produced = new ArrayList<StealthCruiser>();


	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name,commissionYear, maximalSpeed, crewMembers, weapons);
		this.weapons = weapons;
		this.annualMaintenanceCost = this.getAnnualMaintenanceCost();
		super.addStealthCruiser();
		this.stealth_produced.add(this);



	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name,commissionYear, maximalSpeed, crewMembers, new ArrayList<Weapon>());
		this.weapons = new ArrayList<Weapon>();
		this.weapons.add(new Weapon("Laser Cannons", 10, 100));
		this.annualMaintenanceCost = this.getAnnualMaintenanceCost();
		super.setWeapons(weapons);
		super.getFirePower();
		super.addStealthCruiser();

	}


		@Override
	public int getAnnualMaintenanceCost() {
		int total = 0;
		for(StealthCruiser stealthCruiser: this.stealth_produced){
			total += 50;
		}
		return super.getAnnualMaintenanceCost() + total;
	}

	@Override
	public String toString() {
		return "StealthCruiser\n" +
				"\tName=" + this.getName() + "\n" +
				"\tCommissionYear="+ this.getCommissionYear() + "\n" +
				"\tMaximalSpeed="+ this.getMaximalSpeed()+"\n" +
				"\tFirePower=" + this.getFirePower()+"\n" +
				"\tCrewMembers=" +this.getCrewMembers().size()+ "\n" +
				"\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				"\tWeaponArray=" + this.weapons;
	}
}
