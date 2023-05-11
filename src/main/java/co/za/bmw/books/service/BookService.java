package co.za.bmw.books.service;


import co.za.bmw.books.exception.RecordNotFoundException;
import co.za.bmw.books.model.BookRequestDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Collection;

public interface BookService {
    public Response getSingleBook(Long id) throws RecordNotFoundException;
    public Collection<Response> getAllBooks();
    public Response addNewBook(BookRequestDTO bookRequestDTO, UriInfo uriInfo);
    Response updateBook(Long id, BookRequestDTO requestDTO) throws RecordNotFoundException;
    public Response removeBook(Long id) throws RecordNotFoundException;
}
