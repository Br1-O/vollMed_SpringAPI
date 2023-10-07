package med.voll.api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.domain.Address;

public record dtoDoctor_updateData(@NotNull Long id, String name, String lastName, String phone, String email, Address address) {
}
