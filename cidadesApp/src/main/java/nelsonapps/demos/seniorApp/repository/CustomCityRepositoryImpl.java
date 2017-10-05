package nelsonapps.demos.seniorApp.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.entities.City;

@Repository("cityCustomRepository")
public class CustomCityRepositoryImpl implements CustomCityRepository{

	private final EntityManager em;
	
	@Autowired
	public CustomCityRepositoryImpl(JpaContext jpaContext) {
		this.em = jpaContext.getEntityManagerByManagedType(City.class);
	}
	
	@Override
	public Collection<City> findByAtributteLike(String searchAttr,String searchValue) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<City> query = criteria.createQuery(City.class);
		Root<City> from = query.from(City.class);
		ParameterExpression<String> searchParam = criteria.parameter(String.class);
	    query.select(from).where(criteria.like(from.get(searchAttr), searchParam));
		
	    return em.createQuery(query).
	    		  setParameter(searchParam, "%" + searchValue + "%").
	    		  getResultList();
	}

	@Override
	public Long countByAttribute(String attribute) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteria.createQuery(Long.class);
		Root<City> from = query.from(City.class);
		query.select(criteria.countDistinct(from.get(attribute)));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public StateNumberCities getStateGreatestCitiesNumber() {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<StateNumberCities> query = criteria.createQuery(StateNumberCities.class);
		Root<City> from = query.from(City.class);
		CompoundSelection<StateNumberCities> projection = criteria.construct
				(StateNumberCities.class, from.get("uf"),criteria.count(from));
		query.select(projection).groupBy(from.get("uf"))
		     .orderBy(criteria.desc(criteria.count(from)));
		return em.createQuery(query).getResultList().get(0);
	}

	@Override
	public StateNumberCities getStateLeastCitiesNumber() {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<StateNumberCities> query = criteria.createQuery(StateNumberCities.class);
		Root<City> from = query.from(City.class);
		CompoundSelection<StateNumberCities> projection = criteria.construct
				(StateNumberCities.class, from.get("uf"),criteria.count(from));
		query.select(projection).groupBy(from.get("uf"))
		     .orderBy(criteria.asc(criteria.count(from)));
		return em.createQuery(query).getResultList().get(0);
	}

}
