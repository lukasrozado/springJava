package com.senac.springapi.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Adress {
    private String publicPlace;
    private String neighbourhood;
    private String zipCode;
    private String number;
    private String complement;
    private String city;

    public Adress(){}
    public Adress(DataAdress data ){
        String uf = data.uf();
            this.city = data.city();
            this.complement = data.complement();
            this.zipCode = data.zipCode();
            this.publicPlace = data.publicPlace();
            this.number = data.number();
            this.neighbourhood = data.neighbourhood();

        }


}
