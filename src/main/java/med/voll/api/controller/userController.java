package med.voll.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.domain.User;
import med.voll.api.model.dto.request.dtoUser_createData;
import med.voll.api.model.dto.response.dtoUser_dataDisplay;
import med.voll.api.model.repositories.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class userController {

    //dependency injection via constructor
    private final IUserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public userController(IUserRepository userRepository, AuthenticationManager authenticationManager ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    //LOGIN Auth method

    @PostMapping("/login")
    public ResponseEntity loginAuth(@RequestBody @Valid dtoUser_createData dtoUser_createData){
        Authentication token = new UsernamePasswordAuthenticationToken(dtoUser_createData.username(), dtoUser_createData.password());
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }



    //CREATE method
        @PostMapping("/registrar")
        public ResponseEntity<dtoUser_dataDisplay> register(@RequestBody @Valid dtoUser_createData user, UriComponentsBuilder UriComponentsBuilder) {
            //dtoDoctor picks up the data the user inputs and puts it inside its attributes
            //Doctor class takes those attributes and puts them inside its attributes parsing them correctly
            //DoctorRepository saves the attributes of Doctor class into table doctors inside database as a new register
            User newUser = userRepository.save(new User(user));
            dtoUser_dataDisplay dtoUser_dataDisplay = new dtoUser_dataDisplay(newUser);

            URI url = UriComponentsBuilder.path("/usuario/mostrar/{id}").buildAndExpand(newUser.getId()).toUri();
            return ResponseEntity.created(url).body(dtoUser_dataDisplay);
        }

    //READ ONE method
        @GetMapping("/mostrar/{id}")
        @Transactional
        public ResponseEntity<dtoUser_dataDisplay> showOne(@PathVariable long id){
            User user = userRepository.getReferenceById(id);
            dtoUser_dataDisplay dtoUser_dataDisplay = new dtoUser_dataDisplay(user);

            return ResponseEntity.ok(dtoUser_dataDisplay);
        }

}