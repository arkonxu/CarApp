package app.resources;

import java.net.URISyntaxException;
import java.text.ParseException;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import app.DTO.MotoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface MotoResource {

	@Operation(summary = "Get all motos", description = "Retrieves all the motos from the DB", responses = {
			@ApiResponse(description = "Motos", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotoDTO.class)))), })
	public Response getAll(@QueryParam("country") String country);

	@Operation(summary = "Add new moto", description = "Add a new moto to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotoDTO.class)))), })
	public Response addMoto(@Valid MotoDTO moto, @Context UriInfo uriInfo) throws URISyntaxException, ParseException;

	@Operation(summary = "Get moto filtering by ID", description = "Retrieves a moto filtered by ID from the DB", responses = {
			@ApiResponse(description = "Moto", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotoDTO.class)))), })
	public Response getMotoById(@PathParam("motoId") long id);

	@Operation(summary = "Update a moto with new information", description = "Update a moto to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotoDTO.class)))), })
	public Response putMoto(@PathParam("motoId") long id, @Valid MotoDTO moto, @Context UriInfo uriInfo)
			throws URISyntaxException;

	@Operation(summary = "Delete a moto filtering by ID", description = "Delete a moto filtered by ID from the DB", responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MotoDTO.class)))), })
	public Response deleteMoto(@PathParam("motoId") long id);

}
