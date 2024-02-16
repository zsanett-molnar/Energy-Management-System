package com.example.monitoringmicroservice.repositories;

import com.example.monitoringmicroservice.entities.Measurement;
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
public class MeasurementRepoImplement implements MeasurementRepository{

    @PersistenceContext
    private EntityManager entityManager;

    // other methods from MeasurementRepo interface

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public <S extends Measurement> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Measurement> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Measurement> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Measurement getOne(Integer integer) {
        return null;
    }

    @Override
    public Measurement getById(Integer integer) {
        return null;
    }

    @Override
    public Measurement getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Measurement> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Measurement> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Measurement> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Measurement> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Measurement> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Measurement> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Measurement, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Measurement> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Measurement> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Measurement> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Measurement> findAll() {
        return null;
    }

    @Override
    public List<Measurement> findAllById(Iterable<Integer> integers) {
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
    public void delete(Measurement entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Measurement> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Measurement> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Measurement> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Measurement> getAllByDeviceID_Id(Integer deviceID) {
        return null;
    }
}
