package org.learn.mediatek.Services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.learn.mediatek.dto.ClientRequestDto;
import org.learn.mediatek.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {
    boolean save(ClientRequestDto clientRequestDto);
    ClientResponseDto findById(Integer id);
    List<ClientResponseDto> findByNom(String nom);
    void delete(Integer id);
    ClientResponseDto update(ClientRequestDto clientResponseDto,Integer id);
    List<ClientResponseDto> findAll();
}
