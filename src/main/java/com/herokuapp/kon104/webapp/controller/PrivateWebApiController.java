package com.herokuapp.kon104.webapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.herokuapp.kon104.webapp.domain.SentenceJpn2EngRecord;
import com.herokuapp.kon104.webapp.domain.StudyEnglishService;

/**
 * WebAPI Controller
 */
@RestController
@RequestMapping("/private/webapi")
public class PrivateWebApiController
{

	@Autowired
	private StudyEnglishService studyeng;

    // {{{ public List<SentenceJpn2EngRecord> webapiStudyEnglish()
	@GetMapping("/studyeng")
    public List<SentenceJpn2EngRecord> webapiStudyEnglish()
    {
		List<SentenceJpn2EngRecord> resultList = this.studyeng.getList();
		return resultList;
    }
	// }}}

}
