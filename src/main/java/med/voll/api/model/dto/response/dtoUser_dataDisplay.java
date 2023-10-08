package med.voll.api.model.dto.response;

import med.voll.api.model.domain.User;

public record dtoUser_dataDisplay(
        Long id,
        String username,
        String password
){
    public dtoUser_dataDisplay(User user) {
        this(user.getId(), user.getUsername(), user.getPassword());
    }
}
