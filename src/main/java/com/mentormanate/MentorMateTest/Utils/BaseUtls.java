package com.mentormanate.MentorMateTest.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtls {

	public String getCurrentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	}
	
}
