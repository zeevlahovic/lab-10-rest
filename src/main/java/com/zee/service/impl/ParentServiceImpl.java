package com.zee.service.impl;

import com.zee.dto.ParentDTO;
import com.zee.repository.ParentRepository;
import com.zee.service.ParentService;
import com.zee.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final MapperUtil mapperUtil;

    public ParentServiceImpl(ParentRepository parentRepository, MapperUtil mapperUtil) {
        this.parentRepository = parentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ParentDTO> findAll() {
        return parentRepository
                .findAll()
                .stream()
                .map(parent -> mapperUtil.convert(parent, new ParentDTO()))
                .collect(Collectors.toList());
    }

}
