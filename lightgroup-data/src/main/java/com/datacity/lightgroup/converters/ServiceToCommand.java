package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.ServiceCommand;
import com.datacity.lightgroup.model.Service;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ServiceToCommand implements Converter<Service, ServiceCommand> {

    @Synchronized
    @Nullable
    @Override
    public ServiceCommand convert(Service source) {
        if (source == null) {
            return null;
        }

        final ServiceCommand serviceCommand = new ServiceCommand();
        serviceCommand.setId( source.getId() );
        serviceCommand.setImg( source.getImg() );
        serviceCommand.setTitle( source.getTitle() );
        serviceCommand.setActive( source.isActive() );
        return serviceCommand;
    }

}