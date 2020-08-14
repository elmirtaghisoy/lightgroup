package com.datacity.lightgroup.services;


import com.datacity.lightgroup.commands.HeaderCommand;
import com.datacity.lightgroup.model.Header;

public interface HeaderService {
    Header getData();

    void updateHeader(String title);

    void updateAboutUs(String aboutUs);

    HeaderCommand findCommandById(Long valueOf);

}
