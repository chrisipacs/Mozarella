package com.hybridtheory.mozzarella.persistence;

import org.springframework.data.repository.CrudRepository;

import com.hybridtheory.mozarella.wordteacher.learnmaterials.LearnItemsList;

public interface LearnItemListRepository extends CrudRepository<LearnItemsList,Integer> {
	
	//TODO: implement this correctly
	//@Query("SELECT l FROM Learnitemslist l, Student s, where t.title in ?1")
	//public Iterable<LearnItemsList> findByStudent_Id(List<Integer> ids);
	
}
