package com.kiesoft.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kiesoft.jpa.note.NoteEntity;

public interface NoteRepository extends PagingAndSortingRepository<NoteEntity, Long> {
	
}
