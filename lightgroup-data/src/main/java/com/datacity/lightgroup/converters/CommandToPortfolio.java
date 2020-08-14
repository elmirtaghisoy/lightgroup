package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.model.Portfolio;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToPortfolio implements Converter<PortfolioCommand, Portfolio> {

    @Synchronized
    @Nullable
    @Override
    public Portfolio convert(PortfolioCommand source) {
        if (source == null) {
            return null;
        }

        final Portfolio portfolio = new Portfolio();
        portfolio.setId( source.getId() );
        portfolio.setImg( source.getImg() );
        portfolio.setTitle( source.getTitle() );
        portfolio.setDescription( source.getDescription() );
        portfolio.setActive( source.isActive() );
        return portfolio;
    }

}