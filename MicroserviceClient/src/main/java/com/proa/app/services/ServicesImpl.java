package com.proa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proa.app.dao.IClientDAO;
import com.proa.app.entities.Client;
import com.proa.app.exceptions.ClientNotFoundException;




//Anotacion que indica que esta clase es un servicio gestionado por Spring.
//Permite que el contenedor de Spring la detecte y registre como un bean.
@Service
public class ServicesImpl implements IService{
	
	// Inyeccion automatica del bean que implementa la interfaz IClientDAO.
    // El contenedor de Spring se encarga de crear y administrar la instancia.
	@Autowired
	private IClientDAO dao;
	
	
	// Implementación del método insert para guardar un cliente en la base de datos.
    // Utiliza el metodo save() del DAO para realizar la operación.
    // Retorna true si el objeto fue guardado exitosamente (response no es null).
	@Override
	public boolean insert(Client c) {
		var response= dao.save(c);
		return response!=null;
	}

	@Override
	public List<Client> selectAll() {
		
		return (List<Client>)dao.findAll();
	}
	
   
	
	//	throws ClientNotFoundException: indica que el método puede lanzar una excepción de tipo ClientNotFoundException 
	//     			si ocurre un problema durante la actualización (como que el cliente no exista).
	//     			Obliga a quien llame al método a manejar la excepción con un bloque try-catch o declararla con throws
	//              en la firma del método que lo invoca.
	@Override
	public boolean update(Client c) throws ClientNotFoundException{
		
		if(dao.existsById(c.getId())) {
			var response=dao.save(c);
			return response != null;
		}

		// Si el cliente no existe, lanza una excepción personalizada
		throw new ClientNotFoundException("Client "+c.getId()+" not found");
	}
	
	
	
	// Sobrescribe el método findById definido en la interfaz (usualmente IService).
	// Recibe un ID de tipo long y puede lanzar una excepción ClientNotFoundException.
	@Override
	public Client findById(long id) throws ClientNotFoundException {
		
		 // Utiliza el método findById del DAO para buscar el cliente por su ID.
	    // Si el cliente no se encuentra, lanza una excepción personalizada con el mensaje correspondiente.
		return dao. findById(id)
				.orElseThrow(
				()->new ClientNotFoundException("Client "+id+" not found")
				);
	}
	
	// Sobrescribe el método delete definido en la interfaz (usualmente IService).
	// Recibe un ID de tipo long y puede lanzar una excepción ClientNotFoundException.
	@Override
	public boolean delete(long id) throws ClientNotFoundException  {
        
		// Verifica si el cliente existe en la base de datos.
		if(dao.existsById(id)) {
		    
			// Si el cliente existe, lo elimina utilizando el método deleteById del DAO.
			dao.deleteById(id);
		
			// Retorna true para indicar que la eliminación fue exitosa.
			return true;
		}
		
		  // Si el cliente no existe, lanza una excepción personalizada indicando el error.
			throw new ClientNotFoundException("Client "+id+" not found !! ");
	}
	

}
