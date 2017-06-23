**Java Developer Service Test**

Fork this repository and create four resources/endpoints that: 
#1  create an order
#2  read   an order
#3  update an order
#4  delete an order

An order is something that tracks the sale of an item.  Feel free to add columns/objects as you see fit.  
The service starts up on port 7005 by running the ./startup.sh -b script.  Cygwin can be used if you're using a Windows machine.  
You may need to install maven in order to build the project.  The project was originally created with Eclipse and can be 
imported as an existing maven project.  The service utilizes an embedded H2 database.    

Once your changes are complete, commit your branch and issue a pull request.

**FAQ**
Q:  Does the service need to actually start up.
A:  It doesn't need to start up since we're interested more in the code but it doesn't hurt either.


-Create default constructors in dto's and entities
-Create
	CustomerDto
	CustomerDao
	CustomerEntity
	CustomerRepository
	CustomerResource
	CustomerResourceImpl
	
	OrderLineDto
	OrderLineDao
	OrderLineEntity
	
	OrderDto
	OrderDao
	OrderEntity
	OrderRepository
	OrderResource
	OrderResourceImpl
	