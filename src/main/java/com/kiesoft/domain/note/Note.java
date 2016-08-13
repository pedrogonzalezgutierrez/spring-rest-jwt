package com.kiesoft.domain.note;

import com.kiesoft.domain.BaseEntity;
import com.kiesoft.domain.user.User;

public interface Note extends BaseEntity {

	User getUser();
	String getTitle();
	String getDescription();
    
}
