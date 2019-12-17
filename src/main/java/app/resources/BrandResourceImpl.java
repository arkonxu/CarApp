package app.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
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

import org.apache.log4j.Logger;

import app.DTO.BrandDTO;
import app.services.BrandService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("brands")
@LocalBean
public class BrandResourceImpl implements BrandResource {

	@EJB
	private BrandService brandService;

	final static Logger logger = Logger.getLogger(BrandResourceImpl.class);

	@GET
	public Response getAll(@QueryParam("country") String country) throws ParseException {
		if (country != null) {
			List<BrandDTO> brandDTOList = brandService.getBrandByCountry(country);
			return Response.status(Status.OK).entity(brandDTOList).build();
		}
		List<BrandDTO> brandDTOList = brandService.getAll();
		return Response.status(Status.OK).entity(brandDTOList).build();
	}

	@POST
	public Response addBrand(@Valid BrandDTO brandDTO, @Context UriInfo uriInfo)
			throws URISyntaxException, ParseException {
		brandService.addBrand(brandDTO);
		BrandDTO newBrandDTO = brandService.getAll().get(brandService.getAll().size() - 1);
		String uri = uriInfo.getAbsolutePath().toString() + newBrandDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newBrandDTO).build();
	}

	@GET
	@Path("/{brandId}")
	public Response getBrandById(@PathParam("brandId") long id) throws ParseException {
		BrandDTO brandDTO = brandService.getBrandById(id);
		return Response.status(Status.OK).entity(brandDTO).build();
	}

	@PUT
	@Path("/{brandId}")
	public Response putBrand(@PathParam("brandId") long id, @Valid BrandDTO brandDTO, @Context UriInfo uriInfo)
			throws ParseException, URISyntaxException {
		BrandDTO newBrandDTO = brandService.putBrand(id, brandDTO);
		String uri = uriInfo.getAbsolutePath().toString() + newBrandDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newBrandDTO).build();
	}

	@DELETE
	@Path("/{brandId}")
	public Response deleteBrand(@PathParam("brandId") long id) {
		brandService.deleteBrand(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
