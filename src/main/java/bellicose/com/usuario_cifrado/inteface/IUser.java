package bellicose.com.usuario_cifrado.inteface;

import java.util.List;

import bellicose.com.usuario_cifrado.dto.UsuarioDTO;

public interface IUser {

	List<UsuarioDTO> leer() throws Exception;
	UsuarioDTO buscar(int idUser) throws Exception;
	boolean actualizar(UsuarioDTO userDTO) throws Exception;
	boolean borrar(int idUser) throws Exception;
	boolean agregar(UsuarioDTO dto) throws Exception;
}
