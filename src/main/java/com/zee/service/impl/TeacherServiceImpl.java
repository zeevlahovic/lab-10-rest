package com.zee.service.impl;

import com.zee.dto.TeacherDTO;
import com.zee.entity.Teacher;
import com.zee.exception.AlreadyExistsException;
import com.zee.exception.NotFoundException;
import com.zee.repository.AddressRepository;
import com.zee.repository.TeacherRepository;
import com.zee.service.TeacherService;
import com.zee.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              AddressRepository addressRepository,
                              MapperUtil mapperUtil) {
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository
                .findAll()
                .stream()
                .map(teacher -> mapperUtil.convert(teacher, new TeacherDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findByUsername(String username) {
        Teacher foundTeacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Teacher not found!"));
        return mapperUtil.convert(foundTeacher, new TeacherDTO());
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        Optional<Teacher> foundTeacher = teacherRepository.findByUsername(teacherDTO.getUsername());

        if (foundTeacher.isPresent()) {
            throw new AlreadyExistsException("Teacher already exists!");
        }

        addressRepository.findByAddressNo(teacherDTO.getAddressNo())
                .orElseThrow(() -> new NotFoundException("Address not found!"));

        Teacher teacherToSave = mapperUtil.convert(teacherDTO, new Teacher());
        Teacher savedTeacher = teacherRepository.save(teacherToSave);

        return mapperUtil.convert(savedTeacher, new TeacherDTO());

    }

}
