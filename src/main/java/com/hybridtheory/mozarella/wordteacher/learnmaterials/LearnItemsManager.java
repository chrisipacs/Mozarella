package com.hybridtheory.mozarella.wordteacher.learnmaterials;

import java.util.*;
import java.util.stream.Collectors;

import com.hybridtheory.mozarella.users.Student;

public class LearnItemsManager {
	private Student owner = null;
	private List<LearnItemsList> learnItemsLists = new ArrayList<LearnItemsList>();
	private LearnItemFactory learnItemFactory = new LearnItemFactory();

	//TODO: Is this good design? Like this every user will have their own LearnItemManager, which will raise the need towards the User object to know about this Manager itself. Not sure if this is necessary...
	public LearnItemsManager(Student student) {
		if (student != null) {
			this.owner = student;
		} else {
			throw new IllegalArgumentException("Invalid input when creating LearnItemsList");
		}
	}

	public Student getOwner() {
		return owner;
	}

	public LearnItemsList createLearnItemsList(String name) {
		LearnItemsList newLearnItemsList = new LearnItemsList(name);
		if (name != null && !learnItemsLists.contains(newLearnItemsList)) {
			learnItemsLists.add(newLearnItemsList);
			return newLearnItemsList;
		} else {
			throw new IllegalArgumentException("Invalid input when creating LearnItemsList");
		}
	}
	
	public List<LearnItemsList> getLearnItemsLists() {
		//TODO
		return learnItemsLists;
	}
	
//	public LearnItemsList getLearnItemsList(LearnItemsList learnItemsList) {
	public LearnItemsList getLearnItemsList(String nameOfList) {
		for (LearnItemsList lit : learnItemsLists) {
			if (lit.getName() == nameOfList) {
				return lit;
			}
		}
		return null;
	}
	
	public void createNewLearnItemToLearnItemsList(LearnItemsList learnItemsList, String text, String translation) {
		//TODO: check text and translation too!
		if (learnItemsList == null || !learnItemsLists.contains(learnItemsList)) {
			throw new IllegalArgumentException("Invalid input when creating LearnItemsList");
		} else {
			LearnItem newLearnItem = null;
			newLearnItem = learnItemFactory.createLearnItem(text, translation);
			learnItemsList.addLearnItem(newLearnItem);
		}
	}
	
	public void addNewLearnItemToLearnItemsList(LearnItemsList learnItemsList, LearnItem learnItem) {
		if (learnItemsList == null || !learnItemsLists.contains(learnItemsList) || learnItem == null) {
			throw new IllegalArgumentException("Invalid input when creating LearnItemsList");
		} else {
			learnItemsList.addLearnItem(learnItem);
		}
	}	
	
	public LearnItem getLearnItemFromList(LearnItemsList learnItemsList, String textOfLearnItem) {
		//TODO: checks
		return learnItemsList.getLearnItem(textOfLearnItem);
	}

	//TODO: modify existing word in list
	//TODO: add alternative(s) to existing words in list
	//TODO: modify priority of learn item
	//TODO: modify strength
	
	public void setStrengthOfLearnItem(LearnItem learnItem, int strength) {
		if (learnItem != null && strength >= 0 && strength <= 10) {
			learnItem.setStrength(strength);
		} else {
			throw new IllegalArgumentException("Invalid input when creating setting strength for Learn Item");
		}
	}
	
	
	public void removeLearnItemFromLearnItemsList(LearnItemsList learnItemsList, LearnItem learnItem) {
		//TODO: checks
		learnItemsList.removeLearnItem(getLearnItemFromList(learnItemsList, learnItem.getText()));
	}
	
	protected List<String> getExistingListNames() {

		return learnItemsLists.stream().map(LearnItemsList::getName).collect(Collectors.toList());
		
		/*//TODO: Java8
		int i = 0;
		for (LearnItemsList learnItemsList : learnItemsLists) {
			ownedLearnItemsListNames[i] = learnItemsList.getName();
			i++;
		}
		return ownedLearnItemsListNames; */
	}
}