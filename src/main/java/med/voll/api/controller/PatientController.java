package med.voll.api.controller;

import med.voll.api.model.dto.request.dtoPatient_dataReception;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    @PostMapping("/registrar")
    public void register(@RequestBody dtoPatient_dataReception patient){
        System.out.println(patient);
        System.out.println(patient.getName());
    }
}
