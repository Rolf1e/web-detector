package com.rolfie.webdetector.ui.db;

import com.rolfie.webdetector.ui.entities.item.company.Company;
import com.rolfie.webdetector.ui.entities.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CompanyRepository repository;

    @Autowired
    public DatabaseLoader(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Company("CUD", "https://www.ville-dunkerque.fr/"));
    }
}