package com.rolfie.webdetector.ui.entities.repo;

import com.rolfie.webdetector.ui.entities.item.company.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
