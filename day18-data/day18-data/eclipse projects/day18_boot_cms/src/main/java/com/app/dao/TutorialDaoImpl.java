//package com.app.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.app.pojos.Tutorial;
//
//@Repository
//@Transactional
//public class TutorialDaoImpl implements ITutorialDao {
//	//dep : entity mgr
//	@Autowired
//	private EntityManager mgr;
//
//	@Override
//	public List<String> getTutorialNamesByTopic(long topicId) {
//		String jpql="select t.tutName from Tutorial t where t.topic.id =:id order by t.visits desc";
//		return mgr.createQuery(jpql, String.class).setParameter("id", topicId).getResultList();
//	}
//
//	@Override
//	public Tutorial getTutorialDetails(String tutName) {
//		String jpql="select t from Tutorial t where t.tutName=:nm";
//		 Tutorial tutorial = mgr.createQuery(jpql, Tutorial.class).setParameter("nm", tutName).getSingleResult();
//		 //=> persistent 
//		 tutorial.setVisits(tutorial.getVisits()+1);
//		 return tutorial;
//	}
//	
//
//}
