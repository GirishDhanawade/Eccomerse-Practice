package com.app.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ITopicDao;
import com.app.dao.ITutorialDao;
import com.app.pojos.Topic;
import com.app.pojos.Tutorial;

@Service
@Transactional
public class TutorialServiceImpl implements ITutorialService {
	// dep : tut dao i/f
	@Autowired
	private ITutorialDao tutDao;

	@Autowired
	private ITopicDao topicDao;

	@Override
	public Tutorial getTutorialDetails(String tutName) {
		// TODO Auto-generated method stub
		Tutorial tutorial = tutDao.findByTutName(tutName)
				.orElseThrow(() -> new RuntimeException("Invalid tut name!!!!"));
		// tutorial : PERSISTENT
		tutorial.setVisits(tutorial.getVisits() + 1);
		return tutorial;
	}// @Transactional method rets successfully -- tx commit o.w run time exc ---tx
		// rollback
		// tutorial : DETACHED

	@Override
	public List<String> getTutorialNamesByTopic(long topicId) {
		// TODO Auto-generated method stub
		return tutDao.getTutorialNamesByTopic(topicId);
	}

	@Override
	public String validateNAddTutotrial(Tutorial transientTut, long topicId) {
		// validate inputs
		long period = Period.between(transientTut.getPublishDate(), LocalDate.now()).toTotalMonths();
		if (period < 6 && transientTut.getContents().length() < 255) {
			// valid new tut inputs --proceed to saving tut details
			Topic availableTopic = topicDao.findById(topicId)
					.orElseThrow(() -> new RuntimeException("Invalid Topic ID!!!!!"));
			// => valid topic id
			transientTut.setTopic(availableTopic);// establish uni dir asso Tutorial ---> Topic
			Tutorial persistentTut = tutDao.save(transientTut);
			return "Added new tut with ID=" + persistentTut;

		}
		throw new RuntimeException("Invalid Inputs.....");
	}

}
