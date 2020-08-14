package com.datacity.lightgroup.services;

import com.datacity.lightgroup.model.Header;
import com.datacity.lightgroup.repositories.HeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final HeaderRepository headerRepository;

    public ImageServiceImpl(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @Override
    public void saveHeaderImages(MultipartFile file, int whichImage) {
        Header header = headerRepository.getOne( 1L );

        Byte[] byteObjects = getBytesFromSavedImage( file );

        if (whichImage == 1)
            header.setLeftImg( byteObjects );
        else
            header.setRightImg( byteObjects );

        headerRepository.save( header );
    }

    @Override
    public Byte[] getBytesFromSavedImage(MultipartFile file) {
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            return byteObjects;
        } catch (IOException e) {
            log.error( "Error occurred", e );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void imageRenderer(HttpServletResponse response, Byte[] imageBytes) {
        if (imageBytes != null) {
            byte[] byteArray = new byte[imageBytes.length];
            int i = 0;

            for (Byte wrappedByte : imageBytes) {
                byteArray[i++] = wrappedByte;
            }
            response.setContentType( "image/jpeg" );
            InputStream is = new ByteArrayInputStream( byteArray );
            try {
                IOUtils.copy( is, response.getOutputStream() );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
