package med.voll.api.model.dto.request;

public record dtoPatient_dataReception(String name, String email, String phone, String dni, dtoAddress DTOAddress) {

    public String getName(){
        return name;
    }

}
