package negocio.entities;

import java.util.Objects;

public class ProfesorExterno extends Profesor {

	private String titulacion;
	
	public ProfesorExterno() {
		
	}

	public ProfesorExterno(String dni, String nombre, String apellidos, String password, boolean esDoctor, String titulacion) {
		super(dni, nombre, apellidos, password, esDoctor);
		this.titulacion = titulacion;
	}

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(titulacion);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfesorExterno other = (ProfesorExterno) obj;
		return Objects.equals(titulacion, other.titulacion);
	}
	
	

}