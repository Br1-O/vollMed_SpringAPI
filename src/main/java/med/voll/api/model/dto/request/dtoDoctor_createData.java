package med.voll.api.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.domain.Speciality;

public record dtoDoctor_createData(
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "¡Sólo se aceptan letras!")
        String name,

        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "¡Sólo se aceptan letras!")
        String lastName,
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[0-9]{8,50}+$", message = "¡Sólo se aceptan caracteres numéricos!")
        String phone,
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Email(message = "¡No es un email válido!")
        String email,
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[0-9]{8,10}+$", message = "¡8-10 números son requeridos!")
        String dni,
        @NotNull(message = "¡Este campo no puede estar vacio!")
        Speciality speciality,
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[0-9]{8,50}+$", message = "¡Sólo se aceptan caracteres numéricos!")
        String licenseNumber,
        @NotNull(message = "¡Este campo no puede estar vacio!")
        @Valid
        dtoAddress address) {

}
