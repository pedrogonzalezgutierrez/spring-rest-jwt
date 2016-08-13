package com.kiesoft.domain.user;

import java.util.List;

import com.kiesoft.domain.BaseEntity;
import com.kiesoft.domain.note.Note;
import com.kiesoft.domain.role.Role;

public interface User extends BaseEntity {

    String getUsername();
    String getPassword();
    List<? extends Role> getRoles();
    
    List<? extends Note> getNotes();

}
