package com.events.testservice.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.events.testservice.rest.v1.dto.OrderDto;

/**
 * Resource endpoint to handle Order object requests.
 * @author Robert Kaczor
 *
 */
@Path("/v1/orders")
public interface OrderResource {

    /**
     * Gets an order by its key.
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getOrder(@PathParam("id") Long id);
    
    /**
     * Creates an order record based on the data passed in.  Returns the id of the record.
     * @param orderDto
     * @return The id of the new record
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderDto orderDto);
	
    /**
     * Updates an order record based on the data passed in.  Changing the customer
     * will result in a 422 UNPROCESSABLE_ENTITY.
     * @param orderDto
     * @return the id of the record.
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("id") Long id, OrderDto orderDto);
    
    /**
     * Deletes an order by its key.
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteOrder(@PathParam("id") Long id);
}
