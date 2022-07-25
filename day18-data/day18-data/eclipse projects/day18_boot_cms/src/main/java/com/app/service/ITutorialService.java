package com.app.service;

import java.util.List;

import com.app.pojos.Tutorial;

public interface ITutorialService {
	Tutorial getTutorialDetails(String tutName);

	List<String> getTutorialNamesByTopic(long topicId);

	String validateNAddTutotrial(Tutorial transientTut, long attribute);
}
