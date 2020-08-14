package com.datacity.lightgroup.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ImageService {
    void saveHeaderImages(MultipartFile file, int whichImage);

    void imageRenderer(HttpServletResponse response, Byte[] leftImg);

    Byte[] getBytesFromSavedImage(MultipartFile file);
}
