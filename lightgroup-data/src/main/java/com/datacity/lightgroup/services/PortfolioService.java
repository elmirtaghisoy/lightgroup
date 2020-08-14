package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.model.Portfolio;
import javassist.NotFoundException;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();

    PortfolioCommand savePortfolio(PortfolioCommand portfolioCommand);

    Portfolio findById(Long id) throws NotFoundException;

    PortfolioCommand findPortfolioById(Long id) throws NotFoundException;

    void deletePortfolio(Long id);

    List<Portfolio> get3Portfolio();
}
