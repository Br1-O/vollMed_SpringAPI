package med.voll.api.model.dto.request;

        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.Pattern;

public record dtoUser_createData(
        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "¡Sólo caracteres alfanúmericos y los caracteres especiales: - _ son aceptados!")
        String username,

        @NotBlank(message = "¡Este campo no puede estar vacio!")
        @Pattern(regexp = "^[A-Za-z0-9.,\\-_]+$", message = "¡Sólo caracteres alfanúmericos y los caracteres especiales: . , - _ son aceptados!")
        String password
){ }
