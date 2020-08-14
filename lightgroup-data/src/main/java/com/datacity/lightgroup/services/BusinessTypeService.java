package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.BusinessTypeCommand;
import com.datacity.lightgroup.model.BusinessType;
import javassist.NotFoundException;

import java.util.List;

public interface BusinessTypeService {

    List<BusinessType> getAllBusinessType();

    BusinessType findById(Long id) throws NotFoundException;

    BusinessTypeCommand findBusinessTypeById(Long id) throws NotFoundException;

    BusinessTypeCommand saveBusinessType(BusinessTypeCommand businessType);

    void deleteBusinessType(Long id);

}
