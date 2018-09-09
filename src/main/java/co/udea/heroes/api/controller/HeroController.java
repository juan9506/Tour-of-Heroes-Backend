package co.udea.heroes.api.controller;

import java.util.List;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tourofheroes")
public class HeroController {
	
	private static Logger log = LoggerFactory.getLogger(HeroController.class);
	
	private HeroService heroService;
	
	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}
	
	@GetMapping("listar")
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Hero>> getHeros(){
		log.debug("REST request listar todos los heroes");
		return ResponseEntity.ok(heroService.getHeroes());		
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> getHero( @PathVariable("id") int id){
		 log.debug("REST request getHero id : {}", id);
		return ResponseEntity.ok(heroService.getHero(id));
	}
	
	@GetMapping(value = "consultar404/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> getHeroNo404( @PathVariable("id") int id){
		 log.debug("REST request getHeroNo404 id : {}", id);
		return ResponseEntity.ok(heroService.getHeroNo404(id));
	}
	
	
	@GetMapping(value = "buscar/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por nombre", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Hero>> searchHeroes( @PathVariable("nombre") String nombre){
		 log.debug("REST request getHero nombre : {}", nombre);
		return ResponseEntity.ok(heroService.searchHeroes(nombre));
	}
	
	@RequestMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method= {RequestMethod.DELETE, RequestMethod.GET})
    @ApiOperation(value = "Eliminar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe borrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<?> deleteHero( @PathVariable("id") int id){
		 log.debug("REST request deleteHero id : {}", id);
		 heroService.deleteHero(id);
		 return ResponseEntity.ok().build();
	}


	@RequestMapping(value = "/actualizar/{id}/{nuevoNombre}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.GET})
    @ApiOperation(value = "Actualizar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe actualizado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> updateHero( @PathVariable("id") int id, @PathVariable("nuevoNombre") String nuevoNombre){
		 log.debug("REST request updateHero id : {}", id);
		 return ResponseEntity.ok(heroService.updateHero(id, nuevoNombre));
	}

	@RequestMapping(value = "/crear/{id}/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.GET})
    @ApiOperation(value = "Crear heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe creado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> crearHero( @PathVariable("id") int id, @PathVariable("nombre") String nombre){
		 log.debug("REST request crearHero id : {}", id);
		 return ResponseEntity.ok(heroService.crearHero(id, nombre));
	}

}
