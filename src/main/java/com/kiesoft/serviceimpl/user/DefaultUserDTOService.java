package com.kiesoft.serviceimpl.user;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.jpa.user.UserEntity;
import com.kiesoft.repository.UserRepository;
import com.kiesoft.service.note.UserDTOService;

@Transactional
public class DefaultUserDTOService implements UserDTOService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Override
	public UserDTO save(UserDTO dto) {
		try {
			return dozerBeanMapper.map(repository.save(dozerBeanMapper.map(dto, UserEntity.class)), UserDTO.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDTO findOne(Long id) {
		return dozerBeanMapper.map(repository.findOne(id), UserDTO.class);
	}

	@Override
	public Page<UserDTO> findAll(Pageable page) {
		List<UserDTO> listDTO=new ArrayList<>();
		for( UserEntity noteEntity : repository.findAll() ) {
			listDTO.add(dozerBeanMapper.map(noteEntity, UserDTO.class));
		}
		return new PageImpl<>(listDTO, page, repository.count());
	}

	@Override
	public UserDTO findByUsername(String username) {
		if( username != null ) {
			UserEntity entity = repository.findByUsername(username);
			return entity == null ? null : dozerBeanMapper.map(entity, UserDTO.class);
		}
		return null;
	}

}
