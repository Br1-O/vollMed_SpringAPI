package med.voll.api.model.repositories;

import med.voll.api.model.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findByIsActiveTrue(Pageable pagination);
}
