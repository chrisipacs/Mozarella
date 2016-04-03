package com.hybridtheory.mozarella.users;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.hybridtheory.mozarella.pet.Pet;
import com.hybridtheory.mozarella.pet.cubefish.CubeFish;
import com.hybridtheory.mozarella.wordteacher.InputSanitizer;
import com.hybridtheory.mozarella.wordteacher.learnmaterials.LearnItem;
import com.hybridtheory.mozarella.wordteacher.learnmaterials.LearnItemsList;
import com.hybridtheory.mozarella.wordteacher.learnmaterials.LearnItemsManager;

@Entity
public class Student {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name = "";
	
	@Transient
	private LearnItemsManager ownLearnItemManager = null;
	
	@OneToOne(targetEntity=CubeFish.class)
	private Pet ownPet;
	
	@OneToOne(targetEntity=InputSanitizer.class)
	private InputSanitizer inputSanitizer = new InputSanitizer();
	
	public String getName() {
		return this.name;
	}
	
	public Student(String studentName) {
		initializeStudent(studentName);
	}
	
	private void initializeStudent(String studentName) {
		boolean validName = false;
		validName = inputSanitizer.checkStudentNameIsValid(studentName);
		if (!validName) {
			throw new IllegalArgumentException("Invalid name for Student");
		}
		this.name = studentName;
		ownLearnItemManager = new LearnItemsManager(this);
	}
	
	protected void initializePet(String petName) {
		ownPet = new CubeFish(petName);
	}
	
	public Pet getPet() {
			return ownPet;
	}

	protected LearnItemsManager getOwnLearnItemManager() {
		return ownLearnItemManager;
	}
	
	protected List<LearnItemsList> getOwnLearnItemLists() {
		return ownLearnItemManager.getLearnItemsLists();
	}
	
	protected LearnItemsList getALearnItemList(String nameOfList) {
		return ownLearnItemManager.getLearnItemsList(nameOfList);
	}

	protected LearnItemsList addNewLearnItemsList(String name) {
		return ownLearnItemManager.createLearnItemsList(name);
	}
	
	protected void addNewLearnItemToExistingList(LearnItemsList learnItemsList, LearnItem learnItem) {
		ownLearnItemManager.addNewLearnItemToLearnItemsList(learnItemsList, learnItem);
	}
	
	@Override
	public String toString(){
		return "Student with name " + name + "id: " + id;
	}
	
}