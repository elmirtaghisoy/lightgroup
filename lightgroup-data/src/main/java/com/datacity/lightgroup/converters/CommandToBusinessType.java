package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.BusinessTypeCommand;
import com.datacity.lightgroup.model.BusinessType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToBusinessType implements Converter<BusinessTypeCommand, BusinessType> {


    @Synchronized
    @Nullable
    @Override
    public BusinessType convert(BusinessTypeCommand source) {
        if (source == null) {
            return null;
        }
        final BusinessType businessType = new BusinessType();
        businessType.setId( source.getId() );
        businessType.setTypeName( source.getTypeName() );
        return businessType;
    }

}
