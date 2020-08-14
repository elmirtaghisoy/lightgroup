package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.BusinessTypeCommand;
import com.datacity.lightgroup.converters.BusinessTypeToCommand;
import com.datacity.lightgroup.converters.CommandToBusinessType;
import com.datacity.lightgroup.model.BusinessType;
import com.datacity.lightgroup.repositories.BusinessTypeRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;
    private final BusinessTypeToCommand businessTypeToCommand;
    private final CommandToBusinessType commandToBusinessType;

    public BusinessTypeServiceImpl(BusinessTypeRepository businessTypeRepository, BusinessTypeToCommand businessTypeCommand, CommandToBusinessType commandToBusinessType) {
        this.businessTypeRepository = businessTypeRepository;
        this.businessTypeToCommand = businessTypeCommand;
        this.commandToBusinessType = commandToBusinessType;
    }

    @Override
    public List<BusinessType> getAllBusinessType() {
        return businessTypeRepository.findAll();
    }

    @Override
    public BusinessType findById(Long l) throws NotFoundException {
        Optional<BusinessType> recipeOptional = businessTypeRepository.findById( l );
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException( "Göstərilən İD-li istifadəçi tapılmadı " + l.toString() );
        }
        return recipeOptional.get();
    }

    @Override
    public BusinessTypeCommand findBusinessTypeById(Long id) throws NotFoundException {
        return businessTypeToCommand.convert( findById( id ) );
    }

    @Override
    public BusinessTypeCommand saveBusinessType(BusinessTypeCommand businessTypeCommand) {
        BusinessType businessType1 = commandToBusinessType.convert( businessTypeCommand );
        BusinessType saved = businessTypeRepository.save( businessType1 );
        return businessTypeToCommand.convert( saved );
    }

    @Override
    public void deleteBusinessType(Long id) {
        businessTypeRepository.deleteById( id );
    }

}
