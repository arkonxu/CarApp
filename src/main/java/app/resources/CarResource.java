package app.resources;

import java.net.URISyntaxException;
import java.text.ParseException;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import app.entities.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CarResource {

	@Operation(summary = "Get all cars", description = "Retrieves all the cars from the DB", responses = {
			@ApiResponse(description = "Cars", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))), })
	public Response getAll(@QueryParam("country") String country);
	
	@Operation(summary = "Add new car", description = "Add a new car to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))), })
	public Response addCar(@Valid Car car, @Context UriInfo uriInfo) throws URISyntaxException, ParseException;
	
	@Operation(summary = "Get car filtering by ID", description = "Retrieves a car filtered by ID from the DB", responses = {
			@ApiResponse(description = "Car", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))), })
	public Response getCarById(@PathParam("carId") long id);

	@Operation(summary = "Update a car with new information", description = "Update a car to the DB", responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))), })
	public Response putCar(@PathParam("carId") long id, @Valid Car car, @Context UriInfo uriInfo)
			throws URISyntaxException;

	@Operation(summary = "Delete a car filtering by ID", description = "Delete a car filtered by ID from the DB", responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Car.class)))), })
	public Response deleteCar(@PathParam("carId") long id);

}
