package org.learn.mediatek.Services;

import org.learn.mediatek.dao.ClientDao;
import org.learn.mediatek.dto.ClientRequestDto;
import org.learn.mediatek.dto.ClientResponseDto;
import org.learn.mediatek.exceptions.EntityAlreadyExistsException;
import org.learn.mediatek.exceptions.EntityNotFoundException;
import org.learn.mediatek.models.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("Impl1")
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;
    private ModelMapper modelMapper;

    public ClientServiceImpl(ClientDao clientDao, ModelMapper modelMapper) {
        this.clientDao = clientDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(ClientRequestDto clientRequestDto) {
        if(this.findByNom(clientRequestDto.getNom()).isEmpty()){
            ClientEntity clientEntity=modelMapper.map(clientRequestDto,ClientEntity.class);
            clientDao.save(clientEntity);
            return false;
        }else{
            throw  new EntityAlreadyExistsException("Client Alraedy exits");
        }

    }

    @Override
    public ClientResponseDto findById(Integer id) {
        ClientEntity clientEntity=clientDao.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Client Not Found"));
        return modelMapper.map(clientEntity,ClientResponseDto.class);
    }

    @Override
    public List<ClientResponseDto> findByNom(String nom) {
        return clientDao.findByNom(nom).stream()
                .map(el->modelMapper.map(el,ClientResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        clientDao.deleteById(id);
    }

    @Override
    public ClientResponseDto update(ClientRequestDto clientRequestDto, Integer id) {
        Optional<ClientEntity> clientEntityOptional=clientDao.findById(id);
        if(clientEntityOptional.isPresent()){
            ClientEntity clientEntity=modelMapper.map(clientRequestDto,ClientEntity.class);
            clientEntity.setId(id);
            ClientEntity updated= clientDao.save(clientEntity);
            return modelMapper.map(updated,ClientResponseDto.class);
        }else{
            throw new EntityNotFoundException("Client Not Found");

        }
    }
    @Override
    public List<ClientResponseDto> findAll(){
        return clientDao.findAll().stream()
                            .map(el->modelMapper.map(el,ClientResponseDto.class))
                            .collect(Collectors.toList());

    }
}
