package eai.devass.sinistreat.web.action.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/link")
public class Link {
	@GET
	   @Produces(MediaType.TEXT_PLAIN)
    @Path("salut/{username}")
	   public String hi(@PathParam("username") String username) {
	       return "Hello " +username;
	   }

}
