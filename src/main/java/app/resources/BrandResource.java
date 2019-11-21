package app.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import app.entities.Brand;
import app.services.BrandService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("brands")
public class BrandResource {

	@EJB
	private BrandService brandService;

	final static Logger logger = Logger.getLogger(BrandResource.class);

	@GET
	@Path("/hola/{param}")
	public String getMsg(@PathParam("param") String message) {
		return brandService.getMsg(message);
	}

	@GET
	public List<Brand> getAll(@QueryParam("country") String country) {
		if (country != null) {
			return brandService.getBrandByCountry(country);
		}
		return brandService.getAll();
	}

	@POST
	public String addBrand(Brand brand) {
		try {
			brandService.addBrand(brand);
			return "Se ha añadido correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al añadir";
		}
	}

	@GET
	@Path("/{brandId}")
	public Brand getBrandById(@PathParam("brandId") long id) {
		try {
			return brandService.getBrandById(id);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@PUT
	@Path("/{brandId}")
	public String putBrand(@PathParam("brandId") long id, Brand brand) {
		try {
			brand.setId(id);
			brandService.putBrand(id, brand);
			return "Se ha actualizado correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al actualizar";
		}
	}

	@DELETE
	@Path("/{brandId}")
	public String deleteBrand(@PathParam("brandId") long id) {
		try {
			brandService.deleteBrand(id);
			return "Se ha borrado correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al eliminar";
		}
	}

	@GET
	@Path("/{brandId}/cars")
	public CarResource getCarByBrand(@PathParam("brandId") long id) {
		try {
			return new CarResource();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

}
