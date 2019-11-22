package app.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmptyBodyExceptionMapper implements ExceptionMapper<EmptyBodyException>{

	@Override
	public Response toResponse(EmptyBodyException exception) {
		ErrorMessage error = new ErrorMessage(exception.getMessage(), 400);
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}

}
