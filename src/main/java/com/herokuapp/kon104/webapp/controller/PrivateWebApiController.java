package com.herokuapp.kon104.webapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.herokuapp.kon104.webapp.domain.SentenceJpn2Eng;
import com.herokuapp.kon104.webapp.domain.StudyEnglishService;

/**
 * WebAPI Controller
 */
@RestController
@RequestMapping("/private/webapi")
public class PrivateWebApiController
{

	@Autowired
	StudyEnglishService studyEnglishService;

    // {{{ public List<SentenceJpn2Eng> getStudyEnglish()
	@GetMapping("/studyeng")
    public List<SentenceJpn2Eng> getStudyEnglish()
    {
		List<SentenceJpn2Eng> resultList = studyEnglishService.main();
		return resultList;
    }
	// }}}

}
