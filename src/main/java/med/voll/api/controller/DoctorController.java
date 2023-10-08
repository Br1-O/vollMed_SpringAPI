package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.dto.request.dtoDoctor_updateData;
import med.voll.api.model.dto.response.dtoDoctor_dataDisplay;
import med.voll.api.model.dto.request.dtoDoctor_createData;
import med.voll.api.model.domain.Doctor;
import med.voll.api.model.repositories.IDoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medico")
public class DoctorController {

    //dependency injection via constructor

        private final IDoctorRepository doctorRepository;

        public DoctorController(IDoctorRepository doctorRepository) {
            this.doctorRepository = doctorRepository;
        }

    //CREATE method
        @PostMapping("/registrar")
        public ResponseEntity<dtoDoctor_dataDisplay> register(@RequestBody @Valid dtoDoctor_createData doctor, UriComponentsBuilder UriComponentsBuilder){
            //dtoDoctor picks up the data the user inputs and puts it inside its attributes
            //Doctor class takes those attributes and puts them inside its attributes parsing them correctly
            //DoctorRepository saves the attributes of Doctor class into table doctors inside database as a new register
            Doctor newDoctor = doctorRepository.save(new Doctor(doctor));
            dtoDoctor_dataDisplay dtoDoctor_dataDisplay = new dtoDoctor_dataDisplay(newDoctor);

            URI url = UriComponentsBuilder.path("/medico/mostrar/{id}").buildAndExpand(newDoctor.getId()).toUri();
            return ResponseEntity.created(url).body(dtoDoctor_dataDisplay);
        }

    //READ ALL method
        @GetMapping("/mostrar")
        public ResponseEntity<Page<dtoDoctor_dataDisplay>> show(@PageableDefault(size = 4, sort="name") Pageable pagination){

            //if done without using pagination, should return List<dtoDoctor_dataDisplay>
                //return DoctorRepository.findAll().stream().map(dtoDoctor_dataDisplay::new).toList();

            return ResponseEntity.ok(doctorRepository.findByIsActiveTrue(pagination).map(dtoDoctor_dataDisplay::new));
        }

    //READ ONE method
        @GetMapping("/mostrar/{id}")
        @Transactional
        public ResponseEntity<dtoDoctor_dataDisplay> showOne(@PathVariable long id){
            Doctor doctor = doctorRepository.getReferenceById(id);
            dtoDoctor_dataDisplay dtoDoctor_dataDisplay = new dtoDoctor_dataDisplay(doctor);

            return ResponseEntity.ok(dtoDoctor_dataDisplay);
        }


    //DELETE method
        //        @DeleteMapping("/borrar")
        //        @Transactional
        //        public String delete(@RequestParam long i){
        //            Doctor doctor = doctorRepository.getReferenceById(i);
        //            String lastName = doctor.getLastName();
        //            doctorRepository.delete(doctor);
        //
        //            return lastName + " ¡Registro borrado con exito!";
        //        }

    //LOGICAL DELETE method (active - inactive switch)
        @DeleteMapping("/borrar")
        @Transactional
        public ResponseEntity delete(@RequestParam long i){
            Doctor doctor = doctorRepository.getReferenceById(i);
            doctor.setIsActive(false);

            return ResponseEntity.noContent().build();
        }

    //UPDATE method
        @PutMapping("/editar")
        @Transactional
        public ResponseEntity<dtoDoctor_dataDisplay> edit(@RequestParam Long i, @RequestBody dtoDoctor_updateData doctorUpdateData){

            //get reference of doctor by id from db
            Doctor doctorOldData= doctorRepository.getReferenceById(i);
            //use update method from Doctor class passing the dtoDoctor_updateData as parameter
            doctorOldData.updateData(doctorUpdateData);
            dtoDoctor_dataDisplay dtoDoctor_dataDisplay = new dtoDoctor_dataDisplay(doctorOldData);

            return ResponseEntity.ok(dtoDoctor_dataDisplay);
        }



}
