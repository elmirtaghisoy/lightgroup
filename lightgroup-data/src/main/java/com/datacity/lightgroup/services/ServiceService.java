package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.ServiceCommand;
import com.datacity.lightgroup.model.Service;
import javassist.NotFoundException;

import java.util.List;

public interface ServiceService {
    List<Service> getAllServices();

    ServiceCommand saveService(ServiceCommand serviceCommand);

    ServiceCommand findServiceById(Long id) throws NotFoundException;

    void deleteBusinessType(Long id);

    Service findById(Long id) throws NotFoundException;
}
