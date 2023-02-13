package ru.youlola.spring.project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youlola.spring.project3.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, String> {
long countByRainingIsTrue();
}

