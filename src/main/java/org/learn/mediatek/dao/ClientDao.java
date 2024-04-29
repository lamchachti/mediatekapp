package org.learn.mediatek.dao;

import org.learn.mediatek.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity,Integer> {
    List<ClientEntity> findByNom(String nom);
}
