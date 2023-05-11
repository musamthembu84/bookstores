package co.za.bmw.books.resources;

import co.za.bmw.books.exception.RecordNotFoundException;
import co.za.bmw.books.model.BookRequestDTO;
import co.za.bmw.books.service.BookServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Collection;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    BookServiceImpl bookService = new BookServiceImpl();

    @GET
    @Path("/singleBook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleBook(@PathParam("id") Long id) throws RecordNotFoundException {
        return bookService.getSingleBook(id);
    }

    @GET
    @Path("/allBooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Response> getBooks() {
        return bookService.getAllBooks();
    }

    @POST
    @Path("/createBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewBooks(BookRequestDTO requestDTO, @Context UriInfo uriInfo) {
        return bookService.addNewBook(requestDTO,uriInfo);
    }

    @PUT
    @Path("/updateBook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBooks(@PathParam("id") Long id, BookRequestDTO book) throws RecordNotFoundException {
        return bookService.updateBook(id,book);
    }

    @DELETE
    @Path("/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeBook(@PathParam("id") Long id) throws RecordNotFoundException {
        return bookService.removeBook(id);
    }
}
