package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.GenreBookDTO;

/**
 * This interface is designed to support service for {@literal GenreBookDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface GenreBookService {

    GenreBookDTO add(GenreBookDTO genreBook);

}
