package app.services;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.DTO.MotoDTO;
import app.entities.Moto;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.jpa.MotoJPA;
import app.mapping.MapDTOToEntity;
import app.mapping.MapEntityToDTO;

@Stateless
public class MotoService {

	@EJB
	private MotoJPA jpa;

	public MotoService() {
	}

	public List<MotoDTO> getAll() {
		List<Moto> motoList = jpa.getAll();
		List<MotoDTO> MotoDTOList = MapEntityToDTO.mapMotoToResponseList(motoList);
		if (MotoDTOList == null | MotoDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return MotoDTOList;
	}

	public Moto addMoto(MotoDTO motoDTO) throws ParseException {
		if (motoDTO == null || motoDTO.getBrand() == null || motoDTO.getCountry() == null) {
			throw new EmptyBodyException("Body was empty.");
		}
		Moto moto = MapDTOToEntity.mapMotoToEntity(motoDTO);
		return jpa.addEntity(moto);
	}

	public List<MotoDTO> getMotoByCountry(String country) {
		List<MotoDTO> motoDTOList = MapEntityToDTO.mapMotoToResponseList(jpa.getEntityByCountry(country));
		if (motoDTOList == null | motoDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return motoDTOList;
	}

	public MotoDTO getMotoById(long id) {
		Moto moto = jpa.getEntityById(id);
		if (moto == null || moto.getBrand() == null || moto.getCountry() == null) {
			throw new DataNotFoundException("Not found");
		}
		MotoDTO motoDTO = MapEntityToDTO.mapMotoToResponse(moto);
		return motoDTO;
	}

	public MotoDTO putMoto(long id, MotoDTO motoDTO) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			Moto oldMoto = jpa.getEntityById(id);
			oldMoto.setBrand(motoDTO.getBrand());
			oldMoto.setCountry(motoDTO.getCountry());
			if (motoDTO == null || motoDTO.getBrand() == null || motoDTO.getCountry() == null) {
				throw new EmptyBodyException("Body was empty.");
			}
			MotoDTO newMoto = MapEntityToDTO.mapMotoToResponse(jpa.putEntity(oldMoto));
			return newMoto;
		}
	}

	public Moto deleteMoto(long id) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			return jpa.deleteEntity(id);
		}
	}
}