package co.udea.heroes.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.udea.heroes.api.exception.DataNotFoundException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import co.udea.heroes.api.service.HeroService;
import co.udea.heroes.api.util.Messages;

@Service
public class HeroServiceImpl implements HeroService {
	
	private final Logger log = LoggerFactory.getLogger(HeroServiceImpl.class);
	
	private Messages messages;	
	private HeroRepository heroRepository;

	public HeroServiceImpl(HeroRepository heroRepository, Messages messages) {
		this.heroRepository = heroRepository;
		this.messages = messages;
	}

	@Override
	public List<Hero> getHeroes() {
		log.debug("Inicio getHeroes");
		List<Hero> heroes= heroRepository.findAll();
		log.debug("Fin getHeroes");
		return heroes;
	}
	
	@Override
	public Hero getHero(int id) {
		log.debug("Inicio getHero: id = {}", id);
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		log.debug("Fin getHero: heroe = {}", hero.get());
		return hero.get();
	}

	@Override
	public List<Hero> searchHeroes(String nombre) {
		log.debug("Inicio searchHeroe: nombre = {}", nombre);
		List<Hero> heroes = new ArrayList<Hero>();
		
		for(Hero heroe : heroRepository.findAll()) {
			if(heroe.getName().contains(nombre)) {
				heroes.add(heroe);
			}
		}
		return heroes;
	}
	
	
	public void deleteHero(int id) {
		log.debug("Inicio deleteHeroe: id = {}", id);
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()) {
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		heroRepository.delete(hero.get());
		return;
	}
	
	
	public Hero updateHero(int id, String nuevoNombre){
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()) {
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero"));
		}
		heroRepository.findById(id).map(heroe -> {
			heroe.setName(nuevoNombre);
			return heroRepository.save(heroe);
		});
		return hero.get();
	}
	
	public Hero crearHero(int id, String nombre) {
		Optional<Hero> hero = heroRepository.findById(id);
		if(hero.isPresent()) {
			throw new DataNotFoundException(messages.get("exception.already_exists.hero"));
		}
		Hero heroe = new Hero(id,nombre);
		heroRepository.save(heroe);
		return heroe;
	}
	
	public Hero getHeroNo404(int id) {
		log.debug("Inicio getHeroNo404: id = {}", id);
		Optional<Hero> hero = heroRepository.findById(id);
		if(!hero.isPresent()){
			throw new DataNotFoundException(messages.get("exception.data_not_found.hero404"));
		}
		log.debug("Fin getHeroNo404: heroe = {}", hero.get());
		return hero.get();
	}
}
