package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.h2.StudentH2Repository;
import com.example.demo.repository.postgres.StudentPostgresRepository;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentH2Repository h2Repo;
    private final StudentPostgresRepository pgRepo;

    @Autowired
    @Qualifier("h2TransactionManager")
    private PlatformTransactionManager h2TxManager;

    @Autowired
    @Qualifier("postgresTransactionManager")
    private PlatformTransactionManager pgTxManager;

    public StudentService(StudentH2Repository h2Repo, StudentPostgresRepository pgRepo) {
        this.h2Repo = h2Repo;
        this.pgRepo = pgRepo;
    }

    // Remove or comment out the old saveToH2/saveToPostgres methods if you like
    // @Transactional("h2TransactionManager")
    // public void saveToH2(Student student) { h2Repo.save(student); }

    // @Transactional("postgresTransactionManager")
    // public void saveToPostgres(Student student) { pgRepo.save(student); }

    public void insertStudent(Student student) {
        // H2 transaction
        TransactionTemplate h2Tx = new TransactionTemplate(h2TxManager);
        h2Tx.execute(status -> h2Repo.save(student));

        // Postgres transaction
        TransactionTemplate pgTx = new TransactionTemplate(pgTxManager);
        pgTx.execute(status -> pgRepo.save(student));
    }
}
