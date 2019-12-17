package app.services;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.DTO.BrandDTO;
import app.entities.Brand;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.jpa.BrandJPA;
import app.mapping.MapDTOToEntity;
import app.mapping.MapEntityToDTO;

@Stateless
public class BrandService {

	@EJB
	private BrandJPA jpa;

	public BrandService() {
	}

	public List<BrandDTO> getAll() throws ParseException {
		List<BrandDTO> brandDTOList = jpa.getAll().stream().map(MapEntityToDTO::mapBrandToResponse).collect(Collectors.toList());
		if (brandDTOList == null | brandDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return brandDTOList;
	}

	public Brand addBrand(BrandDTO brandDTO) throws ParseException {
		if (brandDTO == null || brandDTO.getName() == null || brandDTO.getCountry() == null) {
			throw new EmptyBodyException("Body was empty.");
		}
		Brand brand = MapDTOToEntity.mapBrandToEntity(brandDTO);
		return jpa.addEntity(brand);
	}

	public List<BrandDTO> getBrandByCountry(String country) throws ParseException {
		List<BrandDTO> brandDTOList = MapEntityToDTO.mapBrandToResponseList(jpa.getEntityByCountry(country));
		if (brandDTOList == null | brandDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return brandDTOList;
	}

	public BrandDTO getBrandById(long id) throws ParseException {
		Brand brand = jpa.getEntityById(id);
		if (brand == null || brand.getName() == null || brand.getCountry() == null) {
			throw new DataNotFoundException("Not found");
		}
		BrandDTO brandDTO = MapEntityToDTO.mapBrandToResponse(brand);
		return brandDTO;
	}

	public BrandDTO putBrand(long id, BrandDTO brandDTO) throws ParseException {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			Brand oldBrand = jpa.getEntityById(id);
			oldBrand.setName(brandDTO.getName());
			oldBrand.setCountry(brandDTO.getCountry());
			if (brandDTO == null || brandDTO.getName() == null || brandDTO.getCountry() == null) {
				throw new EmptyBodyException("Body was empty.");
			}
			BrandDTO newBrandDTO = MapEntityToDTO.mapBrandToResponse(jpa.putEntity(oldBrand));
			return newBrandDTO;
		}
	}

	public Brand deleteBrand(long id) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			return jpa.deleteEntity(id);
		}
	}

}
