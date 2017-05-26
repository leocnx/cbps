package top.auok.cbps.web.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import top.auok.cbps.service.rate.InvalidRateException;
import top.auok.cbps.web.service.model.JSONPagedResults;
import top.auok.cbps.web.service.model.JSONRate;

@Path("rate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RateManagerWebResource {

	@POST
	JSONRate createRate(JSONRate newRate) throws InvalidRateException;

	@GET
	@Path("{id}")
	JSONRate rate(@PathParam("id") Long id);

	@GET
	JSONPagedResults<JSONRate> list(
			@QueryParam("id") String id, 
			@QueryParam("rateCode") String rateCode,
			@DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
			@DefaultValue("100") @QueryParam("pageSize") int pageSize) throws InvalidRateException;
}
