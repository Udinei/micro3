package conta.micro.rest;

import conta.sistema.dominio.modelo.NegocioException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class NegocioExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<NegocioException> {

    @Override
    public Response toResponse(NegocioException e) {
        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
    }
}
