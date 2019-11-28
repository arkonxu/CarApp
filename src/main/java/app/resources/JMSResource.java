package app.resources;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.entities.Car;
import app.jms.JMSSender;

@Path("jms")
public class JMSResource {
	
	@EJB
	private JMSSender sender;
	
	@POST
	@Path("/sendObject")
	public Response sendObject(@Valid Car car){
		sender.sendMessage(car);
		return Response.status(Status.CREATED).entity(car).build();
	}
}
