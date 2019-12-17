package app.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import app.DTO.MotoDTO;
import app.services.MotoService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("motos")
@LocalBean
public class MotoResourceImpl implements MotoResource {

	@EJB
	private MotoService motoService;

	@GET
	public Response getAll(@QueryParam("country") String country) {
		List<MotoDTO> motoDTOList = new ArrayList<>();
		if (country != null && !country.isEmpty()) {
			motoDTOList = motoService.getMotoByCountry(country);
			return Response.status(Status.OK).entity(motoDTOList).build();
		} else {
			motoDTOList = motoService.getAll();
			return Response.status(Status.OK).entity(motoDTOList).build();
		}
	}

	@POST
	public Response addMoto(@Valid MotoDTO motoDTO, @Context UriInfo uriInfo) throws URISyntaxException, ParseException {
		motoService.addMoto(motoDTO);
		MotoDTO newMotoDTO = motoService.getAll().get(motoService.getAll().size() - 1);
		String uri = uriInfo.getAbsolutePath().toString() + newMotoDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newMotoDTO).build();
	}

	@GET
	@Path("/{motoId}")
	public Response getMotoById(@PathParam("motoId") long id) {
		MotoDTO motoDTO = motoService.getMotoById(id);
		return Response.status(Status.OK).entity(motoDTO).build();
	}

	@PUT
	@Path("/{motoId}")
	public Response putMoto(@PathParam("motoId") long id, @Valid MotoDTO motoDTO, @Context UriInfo uriInfo)
			throws URISyntaxException {
		MotoDTO newMotoDTO = motoService.putMoto(id, motoDTO);
		String uri = uriInfo.getAbsolutePath().toString() + newMotoDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newMotoDTO).build();
	}

	@DELETE
	@Path("/{motoId}")
	public Response deleteMoto(@PathParam("motoId") long id) {
		motoService.deleteMoto(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
