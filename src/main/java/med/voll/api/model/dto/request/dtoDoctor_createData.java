package med.voll.api.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.domain.Speciality;

public record dtoDoctor_createData(
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are valid!")
        String name,

        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are valid!")
        String lastName,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[0-9]{8,50}+$", message = "NUMBERS are required!")
        String phone,
        @NotBlank(message = "This field cannot be empty!")
        @Email(message = "Not a valid email!")
        String email,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[0-9]{8,10}+$", message = "8-10 NUMBERS are required!")
        String dni,
        @NotNull(message = "This field cannot be empty!")
        Speciality speciality,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[0-9]{8,50}+$", message = "NUMBERS are required!")
        String licenseNumber,
        @NotNull(message = "This field cannot be empty!")
        @Valid
        dtoAddress address) {

}
