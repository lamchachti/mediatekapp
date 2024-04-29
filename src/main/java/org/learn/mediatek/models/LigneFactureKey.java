package org.learn.mediatek.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class LigneFactureKey implements Serializable {
    private Integer facture_id;
    private Integer produit_id;
}