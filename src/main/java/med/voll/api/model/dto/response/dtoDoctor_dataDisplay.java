package med.voll.api.model.dto.response;

import med.voll.api.model.domain.Speciality;
import med.voll.api.model.domain.Doctor;

public record dtoDoctor_dataDisplay(
     Long id,
     String name,
     String lastName,
     Speciality speciality,
     String phone,
     String email
){
    public dtoDoctor_dataDisplay(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getLastName(), doctor.getSpeciality(), doctor.getPhone(), doctor.getEmail());
    }
}
