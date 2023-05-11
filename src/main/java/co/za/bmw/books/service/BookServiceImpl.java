package co.za.bmw.books.service;



import co.za.bmw.books.exception.RecordNotFoundException;
import co.za.bmw.books.model.Book;
import co.za.bmw.books.model.BookRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.Collection;

public class BookServiceImpl implements BookService{
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SUCCESSFULLY_DELETED_BOOK = "Successfully deleted book";
    private static final String BOOK_COULD_NOT_BE_FOUND = "Book could not be found";

    private static final String CANNOT_CREATE_NEW_BOOK = "Cannot create or update book as it does not exist";

    @Override
    public Response getSingleBook(Long id) throws RecordNotFoundException {

        final Book book = queryBook(id);

        if(book == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    public Collection<Response> getAllBooks() {
        return (Collection<Response>) Response.ok(entityManager.createNamedQuery("Book.findAll",Book.class).getResultList()).build();
    }

    @Override
    public Response updateBook(Long id, BookRequestDTO requestDTO) throws RecordNotFoundException {
        final Book findBook = queryBook(id);

        if(findBook == null){
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(),CANNOT_CREATE_NEW_BOOK).build();
        }
        findBook.setBookName(requestDTO.getBookName());
        findBook.setAuthor(requestDTO.getAuthor());
        findBook.setPrice(requestDTO.getPrice());

        return Response.ok().build();
    }

    @Override
    public Response addNewBook(BookRequestDTO bookRequestDTO, UriInfo uriInfo) {
        if(entityManager.find(Book.class, bookRequestDTO.getId()) !=null){
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), CANNOT_CREATE_NEW_BOOK).build();
        }

        Book book = new Book(bookRequestDTO);
        entityManager.persist(book);

        URI uri = buildUriPath(uriInfo, book);
        return Response.created(uri).build();
    }

    private  URI buildUriPath(UriInfo uriInfo, Book book) {
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(book.getId())).build();
        return uri;
    }

    public Response removeBook(Long id) throws RecordNotFoundException {
        final Book deleteBook = queryBook(id);

        if(entityManager.find(Book.class, id) !=null){
            entityManager.remove(deleteBook);

            return Response.ok(SUCCESSFULLY_DELETED_BOOK).build();
        }


        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), BOOK_COULD_NOT_BE_FOUND).build();

    }

    private Book queryBook(Long id) throws RecordNotFoundException {
        try{
            final Book book = entityManager.find(Book.class, id);
            return book;
        }
        catch (NoResultException e){
           throw new RecordNotFoundException();
        }

    }
}
