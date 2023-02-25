package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.*;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		List<Spaceship> fleetList = new ArrayList<Spaceship>(fleet);
		Collections.sort(fleetList, new mySpaceshipComparator());
		List<String> fleetListName = new ArrayList<String>();
		for (Spaceship spaceship: fleetList){
			fleetListName.add(spaceship.toString());
		}
		return fleetListName;




	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> instanceMap = new HashMap<>();
		for(Spaceship spaceship: fleet){
			if(instanceMap.containsKey(spaceship.getClass().getSimpleName())) {
				int i = instanceMap.get(spaceship.getClass().getSimpleName()) + 1;
				instanceMap.put(spaceship.getClass().getSimpleName(), i);
			}
			else {
				instanceMap.put(spaceship.getClass().getSimpleName(), 1);
			}

		}
		return instanceMap;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int totalCost = 0;
		for(Spaceship spaceship: fleet){
			totalCost += spaceship.getAnnualMaintenanceCost();
		}
		return totalCost;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> weaponsNames = new HashSet<>();
		for(Spaceship spaceship: fleet){
			if(spaceship instanceof Fighter)
			{
				for(Weapon weapon: ((Fighter) spaceship).getWeapons()) {
					weaponsNames.add(weapon.getName());
				}
			}
			if(spaceship instanceof Bomber)
			{
				for(Weapon weapon: ((Bomber) spaceship).getWeapons()) {
					weaponsNames.add(weapon.getName());
				}
			}
		}
		return weaponsNames;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int totalNumOfCrew = 0;
		for(Spaceship spaceship: fleet){
			totalNumOfCrew += spaceship.getCrewMembers().size();
		}
		return totalNumOfCrew;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float totalAge = 0f;
		int totalNumOfOfficers = 0;
		for(Spaceship spaceship: fleet){
			for(CrewMember crewMember: spaceship.getCrewMembers()){
				if(crewMember instanceof Officer){
					totalNumOfOfficers ++;
					totalAge += crewMember.getAge();
				}
			}
		}
		return totalAge/totalNumOfOfficers;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> highestRankingOfficerPerShip = new HashMap<>();
		OfficerRank rank = OfficerRank.Ensign;
		CrewMember highestRankingOfficer = null;
		for(Spaceship spaceship: fleet){
			for(CrewMember crewMember: spaceship.getCrewMembers()){
				if(crewMember instanceof Officer){
					if(((Officer) crewMember).getRank().compareTo(rank) > 0){
						rank = ((Officer) crewMember).getRank();
						highestRankingOfficer = crewMember;
					}
				}
			}
			if(highestRankingOfficer != null){
				highestRankingOfficerPerShip.put((Officer) highestRankingOfficer, spaceship);
			}
			highestRankingOfficer = null;
			rank = OfficerRank.Ensign;
		}


		return highestRankingOfficerPerShip;

	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> ranksTimes = new HashMap<>();

		for(Spaceship spaceship: fleet){
			for(CrewMember crewMember: spaceship.getCrewMembers()){
				if(crewMember instanceof Officer){
					if(ranksTimes.containsKey(((Officer) crewMember).getRank())){
						ranksTimes.put(((Officer) crewMember).getRank(), ranksTimes.get(((Officer) crewMember).getRank()) + 1);
					} else {
						ranksTimes.put(((Officer) crewMember).getRank(), 1);
					}
				}
			}
		}
		Set<Map.Entry<OfficerRank, Integer>> set = ranksTimes.entrySet();
		List<Map.Entry<OfficerRank, Integer>> ranksList = new ArrayList<>(set);
		Collections.sort(ranksList, new myOfficerRankComparator());

		return ranksList;
	}

}
