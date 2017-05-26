package top.auok.cbps.web.service.configuration;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import top.auok.cbps.service.rate.InvalidRateException;

@Provider
public class InvalidRateExceptionMapper implements ExceptionMapper<InvalidRateException> {

	@Override
	public Response toResponse(InvalidRateException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}

}
