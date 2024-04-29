package org.learn.mediatek.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Entity
@Table(name = "ligne_facture")
@Data
@ToString
public class LigneFactureEntity implements Serializable {
    @EmbeddedId
    private LigneFactureKey id;

    @ManyToOne
    @MapsId("facture_id")
    @JoinColumn(name="facture_id")
    private  FactureEntity facture;
    @ManyToOne
    @MapsId("produit_id")
    @JoinColumn(name="produit_id")
    private ProduitEntity produit;
    private Double quantite;
}
