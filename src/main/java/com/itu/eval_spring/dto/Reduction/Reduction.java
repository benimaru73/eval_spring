package com.itu.eval_spring.dto.Reduction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reduction {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("taux")
    private double taux;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
