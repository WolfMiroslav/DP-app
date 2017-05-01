package entities;

import enums.EAgeEnum;
import enums.EGenderEnum;
import enums.ESkillsEnum;

public class CUser {
	private String personName;
	private ESkillsEnum personSkills;
	private ESkillsEnum searchSkills;
	private EGenderEnum personGender;
	private EAgeEnum personAge;
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public ESkillsEnum getPersonSkills() {
		return personSkills;
	}
	
	public void setPersonSkills(ESkillsEnum personSkills) {
		this.personSkills = personSkills;
	}
	
	public ESkillsEnum getSearchSkills() {
		return searchSkills;
	}

	public void setSearchSkills(ESkillsEnum searchSkills) {
		this.searchSkills = searchSkills;
	}

	public EGenderEnum getPersonGender() {
		return personGender;
	}
	
	public void setPersonGender(EGenderEnum personGender) {
		this.personGender = personGender;
	}
	
	public EAgeEnum getPersonAge() {
		return personAge;
	}
	
	public void setPersonAge(EAgeEnum personAge) {
		this.personAge = personAge;
	}
}
