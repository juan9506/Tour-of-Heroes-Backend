package co.udea.heroes.api.service;

import java.util.List;

import co.udea.heroes.api.model.Hero;

public interface HeroService {
	
	public List<Hero> getHeroes();
	public Hero getHero(int id) ;
	public List<Hero> searchHeroes(String nombre) ;
	public void deleteHero(int id);
	public Hero updateHero(int id, String nuevoNombre);
	public Hero crearHero(int id, String nombre);
	public Hero getHeroNo404(int id);
}
