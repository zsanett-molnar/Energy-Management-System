package com.example.monitoringmicroservice.repositories;

import com.example.monitoringmicroservice.entities.Device;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class DeviceRepoImpl implements DeviceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // other methods from MeasurementRepo interface

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public <S extends Device> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Device> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Device> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Device getOne(Integer integer) {
        return null;
    }

    @Override
    public Device getById(Integer integer) {
        return null;
    }

    @Override
    public Device getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Device> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Device> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Device> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Device> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Device, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Device> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Device> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Device> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Device> findAll() {
        return null;
    }

    @Override
    public List<Device> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Device entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Device> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Device> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Device> findAllByUserID(Integer userid) {
        return null;
    }
}
