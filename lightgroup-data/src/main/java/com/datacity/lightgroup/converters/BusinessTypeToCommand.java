package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.BusinessTypeCommand;
import com.datacity.lightgroup.model.BusinessType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BusinessTypeToCommand implements Converter<BusinessType, BusinessTypeCommand> {

    @Synchronized
    @Nullable
    @Override
    public BusinessTypeCommand convert(BusinessType source) {
        if (source == null) {
            return null;
        }

        final BusinessTypeCommand businessTypeCommand = new BusinessTypeCommand();
        businessTypeCommand.setId( source.getId() );
        businessTypeCommand.setTypeName( source.getTypeName() );
        return businessTypeCommand;
    }

}
