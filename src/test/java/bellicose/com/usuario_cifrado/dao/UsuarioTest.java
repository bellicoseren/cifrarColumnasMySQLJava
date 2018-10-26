package bellicose.com.usuario_cifrado.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bellicose.com.usuario_cifrado.dto.UsuarioDTO;

public class UsuarioTest {

	
	public void leerUsuarioTest() {
		UsuarioDAO dao = new UsuarioDAO();
		List<UsuarioDTO> userDTOs = new ArrayList<>();
		try {
			assertNotNull(dao.leer());
			userDTOs = dao.leer();
			for (UsuarioDTO userDTO : userDTOs) {
				System.out.println(userDTO.getId() + " " + userDTO.getNombre() + " " +
									userDTO.getApellido() + " " + userDTO.getFecha_nac());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void agregarUsuarioTest() {
		UsuarioDTO dto = new UsuarioDTO();
		UsuarioDAO dao = new UsuarioDAO();
		dto.setId(3);
		dto.setNombre("Ratita");
		dto.setApellido("Flores");
		dto.setFecha_nac("1981-06-06");
		
		try {
			dao.agregar(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void buscarUsuarioTest() {
		UsuarioDTO dto = new UsuarioDTO();
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dto = dao.buscar(1);
			System.out.println(dto.getId());
			System.out.println(dto.getNombre());
			System.out.println(dto.getApellido());
			System.out.println(dto.getFecha_nac());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public void actualizarUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(2);
		dto.setNombre("Kevin");
		dto.setApellido("Flores");
		dto.setFecha_nac("1981-05-03");
		try {
			dao.actualizar(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void borrarUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.borrar(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
