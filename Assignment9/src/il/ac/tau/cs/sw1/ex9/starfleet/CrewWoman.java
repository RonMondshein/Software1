package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Objects;

public class CrewWoman implements CrewMember{
	String name;
	int age;
	int yearsInService;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CrewWoman)) return false;
		CrewWoman crewWoman = (CrewWoman) o;
		return getName().equals(crewWoman.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}

	public CrewWoman(int age, int yearsInService, String name){
		this.name = name;
		this.age = age;
		this.yearsInService = yearsInService;
	}

	public String getName(){
		return this.name;
	}

	public int getAge(){
		return this.age;
	}

	public int getYearsInService(){
		return this.yearsInService;
	}


}
