package med.voll.api.model.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.model.dto.request.dtoDoctor_createData;
import med.voll.api.model.dto.request.dtoDoctor_updateData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="doctors")
@Entity(name="Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String dni;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    private String licenseNumber;
    @Embedded
    private Address Address;
    private Boolean isActive;

    public Doctor(dtoDoctor_createData newDoctor) {
        this.name=newDoctor.name();
        this.lastName=newDoctor.lastName();
        this.phone=newDoctor.phone();
        this.email=newDoctor.email();
        this.dni=newDoctor.dni();
        this.speciality=newDoctor.speciality();
        this.licenseNumber=newDoctor.licenseNumber();
        this.Address= new Address(newDoctor.address());
        this.isActive= true;
    }

    public void updateData(dtoDoctor_updateData doctorUpdateData){

        if(doctorUpdateData.name()!=null){
            this.setName(doctorUpdateData.name());
        }
        if(doctorUpdateData.lastName()!=null){
            this.setLastName(doctorUpdateData.lastName());
        }
        if(doctorUpdateData.phone()!=null){
            this.setPhone(doctorUpdateData.phone());
        }
        if(doctorUpdateData.email()!=null){
            this.setEmail(doctorUpdateData.email());
        }
        if(doctorUpdateData.address()!=null){
            this.setAddress(doctorUpdateData.address());
        }

    }

}
