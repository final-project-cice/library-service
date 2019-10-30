package com.trl.libraryservice.temporary;

import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.repository.entity.GenreBookEntity;
import com.trl.libraryservice.service.converter.GenreBookConverter;

public class Test {
    public static void main(String[] args) {
        GenreBookEntity genreBookEntity = null;

        try {
            GenreBookConverter.mapEntityToDTO(genreBookEntity);
        } catch (IllegalMethodParameterException e) {
            e.printStackTrace();
        }
    }
}
