package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.converters.CommandToPortfolio;
import com.datacity.lightgroup.converters.PortfolioToCommand;
import com.datacity.lightgroup.model.Portfolio;
import com.datacity.lightgroup.repositories.PortfolioRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioToCommand portfolioToCommand;
    private final CommandToPortfolio commandToPortfolio;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, PortfolioToCommand portfolioToCommand, CommandToPortfolio commandToPortfolio) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioToCommand = portfolioToCommand;
        this.commandToPortfolio = commandToPortfolio;
    }

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public PortfolioCommand savePortfolio(PortfolioCommand portfolioCommand) {
        Portfolio portfolio1 = commandToPortfolio.convert( portfolioCommand );
        Portfolio saved = portfolioRepository.save( portfolio1 );
        return portfolioToCommand.convert( saved );
    }

    @Override
    public Portfolio findById(Long id) throws NotFoundException {
        Optional<Portfolio> recipeOptional = portfolioRepository.findById( id );
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException( "Göstərilən İD-li portfolio tapılmadı " + id );
        }
        return recipeOptional.get();
    }

    @Override
    public PortfolioCommand findPortfolioById(Long id) throws NotFoundException {
        return portfolioToCommand.convert( findById( id ) );
    }

    @Override
    public void deletePortfolio(Long id) {
        portfolioRepository.deleteById( id );
    }

    @Override
    public List<Portfolio> get3Portfolio() {
        return portfolioRepository.findFirst3ByOrderById();
    }

}
