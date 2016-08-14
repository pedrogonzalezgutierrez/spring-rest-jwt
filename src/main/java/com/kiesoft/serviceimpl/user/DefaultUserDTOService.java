package com.kiesoft.serviceimpl.user;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kiesoft.dto.metadata.MetadataUsernameDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.exceptions.PersistenceProblemException;
import com.kiesoft.jpa.user.UserEntity;
import com.kiesoft.repository.UserRepository;
import com.kiesoft.service.user.UserDTOService;

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
		} catch (ConstraintViolationException e) {
			throw new PersistenceProblemException(e);
		}
	}

	@Override
	public UserDTO findOne(Long id) {
		return dozerBeanMapper.map(repository.findOne(id), UserDTO.class);
	}

	@Override
	public Page<UserDTO> findAll(Pageable page) {
		List<UserDTO> listDTO=new ArrayList<>();
		for( UserEntity entity : repository.findAll(page) ) {
			listDTO.add(dozerBeanMapper.map(entity, UserDTO.class));
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

	@Override
	public Page<MetadataUsernameDTO> findAllByUsername(Pageable page) {
		List<MetadataUsernameDTO> ret=new ArrayList<>();
		for( UserEntity entity : repository.findAll(page) ) {
			ret.add(dozerBeanMapper.map(entity, MetadataUsernameDTO.class));
		}
		return new PageImpl<>(ret, page, repository.count());
	}

}
