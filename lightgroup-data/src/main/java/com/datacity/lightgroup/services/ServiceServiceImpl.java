package com.datacity.lightgroup.services;


import com.datacity.lightgroup.commands.ServiceCommand;
import com.datacity.lightgroup.converters.CommandToService;
import com.datacity.lightgroup.converters.ServiceToCommand;
import com.datacity.lightgroup.model.Service;
import com.datacity.lightgroup.repositories.ServiceRepository;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceToCommand serviceToCommand;
    private final CommandToService commandToService;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceToCommand serviceToCommand, CommandToService commandToService) {
        this.serviceRepository = serviceRepository;
        this.serviceToCommand = serviceToCommand;
        this.commandToService = commandToService;
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceCommand saveService(ServiceCommand serviceCommand) {
        Service service1 = commandToService.convert( serviceCommand );
        Service saved = serviceRepository.save( service1 );
        return serviceToCommand.convert( saved );
    }

    @Override
    public ServiceCommand findServiceById(Long id) throws NotFoundException {
        return serviceToCommand.convert( findById( id ) );
    }

    @Override
    public void deleteBusinessType(Long id) {
        serviceRepository.deleteById( id );
    }

    @Override
    public Service findById(Long id) throws NotFoundException {
        Optional<Service> portfolioOptional = serviceRepository.findById( id );
        if (!portfolioOptional.isPresent()) {
            throw new NotFoundException( "Göstərilən İD-li xidmət tapılmadı " + id );
        }
        return portfolioOptional.get();
    }
}
