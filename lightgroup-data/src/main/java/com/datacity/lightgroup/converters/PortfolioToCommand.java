package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.model.Portfolio;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PortfolioToCommand implements Converter<Portfolio, PortfolioCommand> {

    @Synchronized
    @Nullable
    @Override
    public PortfolioCommand convert(Portfolio source) {
        if (source == null) {
            return null;
        }

        final PortfolioCommand portfolioCommand = new PortfolioCommand();
        portfolioCommand.setId( source.getId() );
        portfolioCommand.setImg( source.getImg() );
        portfolioCommand.setTitle( source.getTitle() );
        portfolioCommand.setDescription( source.getDescription() );
        portfolioCommand.setActive( source.isActive() );
        return portfolioCommand;
    }

}