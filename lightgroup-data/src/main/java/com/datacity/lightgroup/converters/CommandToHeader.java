package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.HeaderCommand;
import com.datacity.lightgroup.model.Header;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToHeader implements Converter<HeaderCommand, Header> {

    @Synchronized
    @Nullable
    @Override
    public Header convert(HeaderCommand source) {
        if (source == null) {
            return null;
        }

        final Header header = new Header();
        header.setId( source.getId() );
        header.setLeftImg( source.getLeftImg() );
        header.setRightImg( source.getRightImg() );
        header.setTextHeader( source.getTextHeader() );
        header.setAboutUs( source.getAboutUs() );
        return header;
    }

}
