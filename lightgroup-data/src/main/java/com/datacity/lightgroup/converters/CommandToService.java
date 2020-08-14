package com.datacity.lightgroup.converters;


import com.datacity.lightgroup.commands.ServiceCommand;
import com.datacity.lightgroup.model.Service;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToService implements Converter<ServiceCommand, Service> {

    @Synchronized
    @Nullable
    @Override
    public Service convert(ServiceCommand source) {
        if (source == null) {
            return null;
        }

        final Service service = new Service();
        service.setId( source.getId() );
        service.setImg( source.getImg() );
        service.setTitle( source.getTitle() );
        service.setActive( source.isActive() );
        return service;
    }

}