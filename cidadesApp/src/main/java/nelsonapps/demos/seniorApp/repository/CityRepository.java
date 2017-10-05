package nelsonapps.demos.seniorApp.repository;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.entities.City;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Long> {

	public Collection<City> findByCapital(Boolean isCapital,Sort sort);	
	public City findByIbgeId(Long ibgeId);
	public Slice<City> findByUf(String uf, Pageable pageable);

	@Query("select new nelsonapps.demos.seniorApp.dto.StateNumberCities(c.uf,count(c)) from City c"
			+" group by c.uf")
	public Slice<StateNumberCities> getMumberCitiesPerState();
}
