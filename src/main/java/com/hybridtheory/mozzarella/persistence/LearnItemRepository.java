package com.hybridtheory.mozzarella.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hybridtheory.mozarella.wordteacher.learnmaterials.LearnItem;

public interface LearnItemRepository extends JpaRepository<LearnItem,Integer> {

	@Query("select r.learnItem from Student s join s.learnItemManager m join m.results r where r.learnItem.learnItemList.id in :listIds and s.id = :studentId order by r.priority")
	public List<LearnItem> getLearnItemsForStudent(@Param("studentId") Integer studentId, @Param("listIds") List<Integer> learnItemListids, Pageable pageable);
	
}
