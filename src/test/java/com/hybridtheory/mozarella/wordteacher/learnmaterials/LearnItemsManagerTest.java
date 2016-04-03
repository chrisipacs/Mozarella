package com.hybridtheory.mozarella.wordteacher.learnmaterials;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.hybridtheory.mozarella.users.Student;


public class LearnItemsManagerTest {

	@Test
	public void learnItemsManager_initializeWithValidName() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		
		//When
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		
		//Then
		assertTrue(learnItemsManager.getOwner() == testStudent1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_createWithNullUser() {
		//Given
		
		//When & Then
		new LearnItemsManager(null);
	}
	
	@Test
	public void learnItemsManager_createLearnItemsListWithValidName() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);

		//When
		LearnItemsList newLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");

		
		//Then
		assertTrue(learnItemsManager.getLearnItemsLists().contains(newLearnItemsList));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_createLearnItemsListWithNull() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);

		//When & Then
		learnItemsManager.createLearnItemsList(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_createAlreadyExistingLearnItemsList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		learnItemsManager.createLearnItemsList("testLearnItemsList");

		//When & Then
		learnItemsManager.createLearnItemsList("testLearnItemsList");
	}
	
	@Test
	public void learnItemsManager_addNewValidWordToExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		
		//When
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "testword", "xyz");
		LearnItemsList learnItemsList = learnItemsManager.getLearnItemsList("testLearnItemsList");
		
		//Then
		assertTrue(learnItemsList.getNumberOfLearnItemsInList()==1);
	}
	
	@Test
	public void learnItemsManager_addNewValidMultiWordToExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		
		//When
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "test multi word", "abc xyz");
		LearnItemsList learnItemsList = learnItemsManager.getLearnItemsList("testLearnItemsList");
		
		//Then
		assertTrue(learnItemsList.getNumberOfLearnItemsInList()==1);
	}
	
	@Test
	public void learnItemsManager_addNewValidLearnItemToExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		LearnItemFactory learnItemFactory = new LearnItemFactory();
		LearnItem learnItem = learnItemFactory.createLearnItem("testword", "xyz");
		
		//When
		learnItemsManager.addNewLearnItemToLearnItemsList(existingLearnItemsList, learnItem);
		
		//Then
		assertTrue(existingLearnItemsList.getNumberOfLearnItemsInList()==1);
	}
	
	@Test
	public void learnItemsManager_getValidWordFromExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "testword", "xyz");

		//When
		LearnItem learnItem = learnItemsManager.getLearnItemFromList(existingLearnItemsList, "testword");
		
		//Then
		assertTrue(learnItem.getText() == "testword" && learnItem.getTranslation() == "xyz");
	}
	
	@Test
	public void learnItemsManager_getValidMultiWordFromExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "test multi word", "abc xyz");

		//When
		LearnItem learnItem = learnItemsManager.getLearnItemFromList(existingLearnItemsList, "test multi word");
		
		//Then
		assertTrue(learnItem.getText() == "test multi word" && learnItem.getTranslation() == "abc xyz");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_addNewValidWordToNotExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		
		//When & Then
		learnItemsManager.createNewLearnItemToLearnItemsList(null, "testword", "xyz");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_addNewValidMultiWordToNotExistingList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		
		//When & Then
		learnItemsManager.createNewLearnItemToLearnItemsList(null, "test multi word", "abc xyz");
	}
	
	@Test
	public void learnItemsManager_removeValidWordFromValidList() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "testword", "xyz");
		LearnItem learnItem = learnItemsManager.getLearnItemFromList(existingLearnItemsList, "testword");
		
		//When
		learnItemsManager.removeLearnItemFromLearnItemsList(existingLearnItemsList, learnItem);
		
		//Then
		assertTrue(existingLearnItemsList.getNumberOfLearnItemsInList()==0);
	}
	
	@Test
	public void learnItemsManager_setStrengthOfLearnItem_valid() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "testword", "xyz");
		LearnItem learnItem = learnItemsManager.getLearnItemFromList(existingLearnItemsList, "testword");

		//When
		learnItemsManager.setStrengthOfLearnItem(learnItem, 1);
		
		//Then
		assertTrue(learnItem.getStrength()==1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void learnItemsManager_setStrengthOfLearnItem_invalid() {
		//Given
		Student testStudent1 = new Student("testStudent1");
		LearnItemsManager learnItemsManager = new LearnItemsManager(testStudent1);
		LearnItemsList existingLearnItemsList = learnItemsManager.createLearnItemsList("testLearnItemsList");
		learnItemsManager.createNewLearnItemToLearnItemsList(existingLearnItemsList, "testword", "xyz");
		LearnItem learnItem = learnItemsManager.getLearnItemFromList(existingLearnItemsList, "testword");

		//When & Then
		learnItemsManager.setStrengthOfLearnItem(learnItem, 11);
	}
}
