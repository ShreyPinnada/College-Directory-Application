package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class FacultyServiceImplement implements FacultyProfileRepository {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Override
    public List<FacultyProfile> findAdvisorsByStudentId(Long studentId) {
        // Implement the logic to find faculty advisors by student ID
        // This assumes there is a method in the repository to get this information
        return facultyProfileRepository.findByDepartment_StudentId(studentId);
    }

    @Override
    public List<FacultyProfile> findByDepartment_Id(Long userId) {
        return List.of();
    }

    @Override
    public List<FacultyProfile> findByDepartmentId(Long departmentId) {
        return facultyProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<FacultyProfile> findByDepartment_StudentId(Long studentId) {
        return List.of();
    }

    // Delegating the rest of the methods to the repository

    @Override
    public void flush() {
        facultyProfileRepository.flush();
    }

    @Override
    public <S extends FacultyProfile> S saveAndFlush(S entity) {
        return facultyProfileRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends FacultyProfile> List<S> saveAllAndFlush(Iterable<S> entities) {
        return facultyProfileRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<FacultyProfile> entities) {
        facultyProfileRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        facultyProfileRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        facultyProfileRepository.deleteAllInBatch();
    }

    @Override
    public FacultyProfile getOne(Long aLong) {
        return facultyProfileRepository.getOne(aLong);
    }

    @Override
    public FacultyProfile getById(Long aLong) {
        return facultyProfileRepository.getById(aLong);
    }

    @Override
    public FacultyProfile getReferenceById(Long aLong) {
        return facultyProfileRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends FacultyProfile> Optional<S> findOne(Example<S> example) {
        return facultyProfileRepository.findOne(example);
    }

    @Override
    public <S extends FacultyProfile> List<S> findAll(Example<S> example) {
        return facultyProfileRepository.findAll(example);
    }

    @Override
    public <S extends FacultyProfile> List<S> findAll(Example<S> example, Sort sort) {
        return facultyProfileRepository.findAll(example, sort);
    }

    @Override
    public <S extends FacultyProfile> Page<S> findAll(Example<S> example, Pageable pageable) {
        return facultyProfileRepository.findAll(example, pageable);
    }

    @Override
    public <S extends FacultyProfile> long count(Example<S> example) {
        return facultyProfileRepository.count(example);
    }

    @Override
    public <S extends FacultyProfile> boolean exists(Example<S> example) {
        return facultyProfileRepository.exists(example);
    }

    @Override
    public <S extends FacultyProfile, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return facultyProfileRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends FacultyProfile> S save(S entity) {
        return facultyProfileRepository.save(entity);
    }

    @Override
    public <S extends FacultyProfile> List<S> saveAll(Iterable<S> entities) {
        return facultyProfileRepository.saveAll(entities);
    }

    @Override
    public Optional<FacultyProfile> findById(Long aLong) {
        return facultyProfileRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return facultyProfileRepository.existsById(aLong);
    }

    @Override
    public List<FacultyProfile> findAll() {
        return facultyProfileRepository.findAll();
    }

    @Override
    public List<FacultyProfile> findAllById(Iterable<Long> longs) {
        return facultyProfileRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return facultyProfileRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        facultyProfileRepository.deleteById(aLong);
    }

    @Override
    public void delete(FacultyProfile entity) {
        facultyProfileRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        facultyProfileRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends FacultyProfile> entities) {
        facultyProfileRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        facultyProfileRepository.deleteAll();
    }

    @Override
    public List<FacultyProfile> findAll(Sort sort) {
        return facultyProfileRepository.findAll(sort);
    }

    @Override
    public Page<FacultyProfile> findAll(Pageable pageable) {
        return facultyProfileRepository.findAll(pageable);
    }
}