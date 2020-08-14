package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.HeaderCommand;
import com.datacity.lightgroup.converters.CommandToHeader;
import com.datacity.lightgroup.converters.HeaderToCommand;
import com.datacity.lightgroup.model.Header;
import com.datacity.lightgroup.repositories.HeaderRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeaderServiceImpl implements HeaderService {

    private final HeaderToCommand headerToCommand;
    private final CommandToHeader commandToHeader;
    private final HeaderRepository headerRepository;

    public HeaderServiceImpl(HeaderToCommand headerToCommand, CommandToHeader commandToHeader, HeaderRepository headerRepository) {
        this.headerToCommand = headerToCommand;
        this.commandToHeader = commandToHeader;
        this.headerRepository = headerRepository;
    }

    public Header getData() {
        return headerRepository.getOne( 1L );
    }

    @Override
    public void updateHeader(String title) {
        Header headerFromDb = headerRepository.getOne( 1L );
        headerFromDb.setTextHeader( title );
        headerRepository.save( headerFromDb );
    }

    @Override
    public void updateAboutUs(String aboutUs) {
        Header headerFromDb = headerRepository.getOne( 1L );
        headerFromDb.setAboutUs( aboutUs );
        headerRepository.save( headerFromDb );
    }

    public Header findById(Long l) {

        Optional<Header> recipeOptional = headerRepository.findById( l );

        return recipeOptional.get();
    }

    @Override
    public HeaderCommand findCommandById(Long valueOf) {
        return headerToCommand.convert( findById( valueOf ) );
    }


}
