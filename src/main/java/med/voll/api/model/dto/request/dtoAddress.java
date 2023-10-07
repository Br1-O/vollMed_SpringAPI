package med.voll.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record dtoAddress(

        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are valid!")
        String street,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are valid!")
        String district,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are valid!")
        String city,
        @NotBlank(message = "This field cannot be empty!")
        @Pattern(regexp = "^[0-9]+$", message = "Only numbers are valid!")
        String numeration,
        @NotBlank(message = "This field cannot be empty!")
        String complement) {
}
