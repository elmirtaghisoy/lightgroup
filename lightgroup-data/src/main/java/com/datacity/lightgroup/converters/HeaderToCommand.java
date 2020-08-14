package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.HeaderCommand;
import com.datacity.lightgroup.model.Header;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class HeaderToCommand implements Converter<Header, HeaderCommand> {

    @Synchronized
    @Nullable
    @Override
    public HeaderCommand convert(Header source) {
        if (source == null) {
            return null;
        }

        final HeaderCommand headerCommand = new HeaderCommand();
        headerCommand.setId( source.getId() );
        headerCommand.setLeftImg( source.getLeftImg() );
        headerCommand.setRightImg( source.getRightImg() );
        headerCommand.setTextHeader( source.getTextHeader() );
        headerCommand.setAboutUs( source.getAboutUs() );
        return headerCommand;
    }

}
