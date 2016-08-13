package com.kiesoft.serviceimpl.note;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kiesoft.dto.note.NoteDTO;
import com.kiesoft.jpa.note.NoteEntity;
import com.kiesoft.repository.NoteRepository;
import com.kiesoft.service.user.NoteDTOService;

@Transactional
public class DefaultNoteDTOService implements NoteDTOService {

	@Autowired
	private NoteRepository repository;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Override
	public NoteDTO save(NoteDTO dto) {
		try {
			return dozerBeanMapper.map(repository.save(dozerBeanMapper.map(dto, NoteEntity.class)), NoteDTO.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public NoteDTO findOne(Long id) {
		return dozerBeanMapper.map(repository.findOne(id), NoteDTO.class);
	}

	@Override
	public Page<NoteDTO> findAll(Pageable page) {
		List<NoteDTO> listDTO=new ArrayList<>();
		for( NoteEntity noteEntity : repository.findAll() ) {
			listDTO.add(dozerBeanMapper.map(noteEntity, NoteDTO.class));
		}
		return new PageImpl<>(listDTO, page, repository.count());
	}

}
