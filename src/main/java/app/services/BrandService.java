package app.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.entities.Brand;
import app.jpa.BrandJPA;

@Stateless
public class BrandService {

	@EJB
	private BrandJPA jpa;

	public BrandService() {
	}

	public List<Brand> getAll() {
		return jpa.getAll();
	}

	public Brand addBrand(Brand brand) {
		return jpa.addEntity(brand);
	}

	public List<Brand> getBrandByCountry(String country) {
		List<Brand> brandList = jpa.getAll();
		for (Brand brand : brandList) {
			if (brand.getCountry().equalsIgnoreCase(country)) {
				brandList.add(brand);
			}
		}
		return brandList;
	}

	public Brand getBrandById(long id) {
		Brand brand;
		brand = jpa.getEntityById(id, Brand.class);
		return brand;
	}

	public Brand putBrand(long id, Brand brand) {
		if (id <= 0) {
			return null;
		}
		return jpa.putEntity(brand);
	}

	public void deleteBrand(long id) {
		jpa.deleteEntity(id);
	}

}
