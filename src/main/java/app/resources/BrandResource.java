package app.resources;

import java.net.URISyntaxException;
import java.text.ParseException;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import app.DTO.BrandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface BrandResource {

	@Operation(summary = "Get all brands", description = "Retrieves all the brands from the DB", responses = {
			@ApiResponse(description = "Brands", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandDTO.class)))), })
	public Response getAll(@QueryParam("country") String country) throws ParseException;

	@Operation(summary = "Add new brand", description = "Add a new brand to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandDTO.class)))), })
	public Response addBrand(@Valid BrandDTO brandDTO, @Context UriInfo uriInfo) throws URISyntaxException, ParseException;

	@Operation(summary = "Get brand filtering by ID", description = "Retrieves a brand filtered by ID from the DB", responses = {
			@ApiResponse(description = "Brand", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandDTO.class)))), })
	public Response getBrandById(@PathParam("brandId") long id) throws ParseException;

	@Operation(summary = "Update a brand with new information", description = "Update a brand to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandDTO.class)))), })
	public Response putBrand(@PathParam("brandId") long id, @Valid BrandDTO brandDTO, @Context UriInfo uriInfo)
			throws URISyntaxException, ParseException;

	@Operation(summary = "Delete a brand filtering by ID", description = "Delete a brand filtered by ID from the DB", responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandDTO.class)))), })
	public Response deleteBrand(@PathParam("brandId") long id);

}
