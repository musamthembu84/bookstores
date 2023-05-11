package co.za.bmw.books.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RecordNotFoundException extends Exception {
    public Response entityNotFound(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(String.format("Record not found because: %s",e.getMessage())).build();
    }
}
