package com.kiesoft.domain.role;

import java.util.List;

import com.kiesoft.domain.BaseEntity;
import com.kiesoft.domain.user.User; 

public interface Role extends BaseEntity {

    String getRolename();
    List<? extends User> getUsers();

}