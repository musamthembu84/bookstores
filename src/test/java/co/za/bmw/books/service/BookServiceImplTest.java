package co.za.bmw.books.service;

import co.za.bmw.books.exception.RecordNotFoundException;
import co.za.bmw.books.model.Book;
import co.za.bmw.books.model.BookRequestDTO;
import co.za.bmw.books.resources.BookResource;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceImplTest {


    private BookResource mockBookResource;

    @BeforeEach
    public void start(){
        mockBookResource = new BookResource();
    }

    @Test
    public void createBook(){
        Response createBook = mockBookResource.addNewBooks(buildRequest(), null);
        assertNotNull(createBook);
        assertEquals(Response.Status.OK,createBook.getStatus());
    }

    @Test
    public void testIdIsReturned() throws RecordNotFoundException {
        Response createBook = mockBookResource.addNewBooks(buildRequest(), null);
        Book book = mockBookResource.getSingleBook(buildRequest().getId()).readEntity(Book.class);

        assertNotNull(createBook);
        assertNotNull(book);
        assertEquals(Response.Status.OK,createBook.getStatus());
        assertEquals("BMW 325iS",book.getAuthor());
        assertEquals("Best books on cars",buildRequest().getBookName());
        assertEquals(500000,buildRequest().getPrice());

    }


    @Test
    public void testUpdateDoneSuccessfully() throws RecordNotFoundException {
        Response createBook = mockBookResource.addNewBooks(buildRequest(), null);
        mockBookResource.updateBooks(1L,new BookRequestDTO("mybook","cars",1L));

        Book getUpdatedBook = mockBookResource.getSingleBook(1L).readEntity(Book.class);

        assertNotNull(createBook);
        assertNotNull(getUpdatedBook);
        assertEquals(Response.Status.OK,createBook.getStatus());
        assertEquals("cars",getUpdatedBook.getAuthor());
        assertEquals("mybook",buildRequest().getBookName());

    }

    @Test
    public void testDeleteBook() throws RecordNotFoundException {
        Response createBook = mockBookResource.addNewBooks(buildRequest(), null);
        Book deleteBook = mockBookResource.removeBook(1L).readEntity(Book.class);
        assertNotNull(createBook);
        assertNull(deleteBook);
    }


    private BookRequestDTO buildRequest(){
        BookRequestDTO requestDTO = new BookRequestDTO();
        requestDTO.setAuthor("BMW 325iS");
        requestDTO.setPrice(50000);
        requestDTO.setBookName("Best books on cars");
        return requestDTO;
    }

}