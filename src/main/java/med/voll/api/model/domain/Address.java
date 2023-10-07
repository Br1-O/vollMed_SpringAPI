package med.voll.api.model.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.dto.request.dtoAddress;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Address {

    private String street;
    private String district;
    private String city;
    private String numeration;
    private String complement;

    public Address(dtoAddress dtoAddress) {
        this.street=dtoAddress.street();
        this.district=dtoAddress.district();
        this.city=dtoAddress.city();
        this.numeration=dtoAddress.numeration();
        this.complement=dtoAddress.complement();
    }

}
