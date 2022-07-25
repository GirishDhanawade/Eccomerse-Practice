package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Tutorial;

public interface ITutorialDao extends JpaRepository<Tutorial, Long>{
	@Query("select t.tutName from Tutorial t where t.topic.id =:id order by t.visits desc")
	List<String> getTutorialNamesByTopic(@Param("id") long topicId);
	
//	@Query("select t.tutName from Tutorial t where t.topic.id =?1 order by t.visits desc")
//	List<String> getTutorialNamesByTopic(long topicId);


	//Tutorial getTutorialDetails(String tutName);
	Optional<Tutorial> findByTutName(String tName);
}
