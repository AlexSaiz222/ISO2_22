package negocio.entities;

import java.util.*;

public class Centro {

	Collection<CursoPropio> cursoPropios;
	Collection<ProfesorUCLM> plantilla;
	private int idCentro;
	private String nombre;
	private String localizacion;
	
	public Centro(Collection<CursoPropio> cursoPropios, Collection<ProfesorUCLM> plantilla, int idCentro, String nombre,
			String localizacion) {
		super();
		this.cursoPropios = cursoPropios;
		this.plantilla = plantilla;
		this.idCentro = idCentro;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}
	public Collection<CursoPropio> getCursoPropios() {
		return cursoPropios;
	}
	public void setCursoPropios(Collection<CursoPropio> cursoPropios) {
		this.cursoPropios = cursoPropios;
	}
	public Collection<ProfesorUCLM> getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(Collection<ProfesorUCLM> plantilla) {
		this.plantilla = plantilla;
	}
	
	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	
}