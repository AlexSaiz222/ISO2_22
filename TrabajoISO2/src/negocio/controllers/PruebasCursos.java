package negocio.controllers;

import java.util.List;
import java.util.Scanner;

import persistencia.*;
import negocio.entities.*;

public class PruebasCursos {

	public static void main(String[] args) {
		
		//GestorBD agente = GestorBD.getAgente();
		// Ejemplo delete
		//System.out.println(agente.delete("delete from cursospropios where idcursopropio=2"));
		
		// Ejemplo update
		//System.out.println(agente.insert("update cursospropios set nombre='prueba2', ects=10 where idcursopropio=2"));
		
		/*
		Scanner sc = new Scanner(System.in);
		int i;
		
		CursoPropioDAO cursoDAO = new CursoPropioDAO();
		CentroDAO centroDAO = new CentroDAO();
		ProfesorUCLMDAO profesorUCLMDAO = new ProfesorUCLMDAO();
		
		CursoPropio curso = new CursoPropio();
		
		List<Centro> centros = centroDAO.listarCentros();
		
		System.out.println("Selecciona el centro:");
		for(i=0; i<centros.size(); i++) {
			System.out.println(i+": "+centros.get(i).getNombre()+" - "+centros.get(i).getLocalizacion());
		}
		int seleccion_centro = Integer.parseInt(sc.next());
		curso.setCentro(centros.get(seleccion_centro));
		
		List<ProfesorUCLM> profesoresUCLM = profesorUCLMDAO.listarProfesoresUCLM();
		
		System.out.println("Selecciona el director:");
		for(i=0; i<profesoresUCLM.size(); i++) {
			System.out.println(i+": "+profesoresUCLM.get(i).getDni()+" - "+profesoresUCLM.get(i).getNombre());
		}
		
		// Probar listado de profesoresUCLM
		
		sc.close();
		*/
	}

}
