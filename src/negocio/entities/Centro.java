package negocio.entities;

import java.util.Collection;
import java.util.Objects;

public class Centro {

	Collection<CursoPropio> cursoPropios;
	Collection<ProfesorUCLM> plantilla;
	private int idCentro;
	private String nombre;
	private String localizacion;

	public Centro() {

	}

	public Centro(Collection<CursoPropio> cursoPropios, Collection<ProfesorUCLM> plantilla, int idCentro, String nombre,
			String localizacion) {
		super();
		this.cursoPropios = cursoPropios;
		this.plantilla = plantilla;
		this.idCentro = idCentro;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}

	public Centro(int idCentro, String nombre, String localizacion) {
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

	@Override
	public int hashCode() {
		return Objects.hash(cursoPropios, idCentro, localizacion, nombre, plantilla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Centro other = (Centro) obj;
		return Objects.equals(cursoPropios, other.cursoPropios) && idCentro == other.idCentro
				&& Objects.equals(localizacion, other.localizacion) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(plantilla, other.plantilla);
	}

}