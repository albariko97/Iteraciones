package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Restaurante;
import vos.Usuario;
import vos.UsuarioClientePref;

@Path("servicios")
public class PersonasResource extends RotondAndesServices{
	
	@GET
	@Path("/usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Usuario> usuarios;
		try
		{
			usuarios = tm.darUsuarios();
			return Response.status( 200 ).entity( usuarios ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path("/usuariosPref")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuariosPreferencias(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<UsuarioClientePref> usuarios;
		try
		{
			usuarios = tm.darUsuarioPreferencias();
			return Response.status( 200 ).entity( usuarios ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@POST
	@Path("/usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsuario(Usuario usuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarUsuario(usuario);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@GET
	@Path("/usuarios/administradores")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdministradores(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Usuario> usuarios;
		try
		{
			usuarios = tm.darAdministradores();
			return Response.status( 200 ).entity( usuarios ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path("/usuarios/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioPorID(@PathParam("id") Long id){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		Usuario usuario;
		try
		{
			usuario = tm.buscarUsuarioPorID(id);
			return Response.status( 200 ).entity( usuario ).build( );	
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Path("/usuarios/{id: \\d+}/usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCliente(@PathParam("id") Long id, Usuario usuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarCliente(id, usuario);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@Path("/usuarios/administradores/{id: \\d+}/restaurantes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurante(@PathParam("id") Long id, Restaurante rest){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarRestaurante(id,rest);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rest).build();
	}

	@GET
	@Path("/restaurantes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRestaurantes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Restaurante> restaurantes;
		try
		{
			restaurantes = tm.darRestaurantes();
			return Response.status( 200 ).entity( restaurantes ).build( );			
		}catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}