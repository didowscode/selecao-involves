package br.com.rsacacio.localizacao.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Auditoria{

    @Id
    @GeneratedValue(generator = "funcionario_generator")
    @SequenceGenerator(
            name = "funcionario_generator",
            sequenceName = "funcionario_sequence"
    )
    @CsvBindByName
    private Long id;

    @NotBlank
    @Size(max = 1000)
    @CsvBindByName
    private String name;

    @NotNull
    @CsvBindByName
    private Double latitude;

    @NotNull
    @CsvBindByName
    private Double longitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " nome: " + this.name + " latitude: " + this.latitude + " longitude: " + this.longitude;
    }
}
