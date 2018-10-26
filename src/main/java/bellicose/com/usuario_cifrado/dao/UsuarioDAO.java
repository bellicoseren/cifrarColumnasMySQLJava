package bellicose.com.usuario_cifrado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bellicose.com.usuario_cifrado.conexion.ConnectionFactory;
import bellicose.com.usuario_cifrado.dto.UsuarioDTO;
import bellicose.com.usuario_cifrado.inteface.IUser;
import bellicose.com.usuario_cifrado.util.Cifrado;

public class UsuarioDAO implements IUser {
	Connection conn;
	String clave = "1111111111111111";
	String vi = "1111111111111111";
	
	@Override
	public List<UsuarioDTO> leer() throws Exception {
		String leer = "select id, nombre, apellido, fecha_nac from usuario";
		conn = ConnectionFactory.getInstance().getConexion();
		if(conn == null) {
			System.out.println("No se estableció la conexion");
		}
		PreparedStatement ps = conn.prepareStatement(leer);
		ResultSet rs = ps.executeQuery();
		List<UsuarioDTO> users = new ArrayList<>();
		UsuarioDTO userDTO = null;
		while(rs.next()) {
			userDTO = new UsuarioDTO();
			userDTO.setId(rs.getInt("id"));
			userDTO.setNombre(Cifrado.decifrar(clave, vi, rs.getString("nombre")));
			userDTO.setApellido(Cifrado.decifrar(clave, vi, rs.getString("apellido")));
			userDTO.setFecha_nac(rs.getString("fecha_nac"));
			
			users.add(userDTO);
		}
		if(rs != null) {
			rs.close();
		}
		if(ps != null) {
			ps.close();
		}
		if(conn != null) {
			conn.close();
		}
		return users;
	}

	@Override
	public UsuarioDTO buscar(int idUsuario) throws Exception {
		String buscar = "select * from usuario where id=?";
		conn = ConnectionFactory.getInstance().getConexion();
		if(conn == null) {
			System.out.println("No se estableció conexion");
		}
		PreparedStatement ps = conn.prepareStatement(buscar);
		ps.setInt(1, idUsuario);
		UsuarioDTO usuarioDTOs = null;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			usuarioDTOs = new UsuarioDTO();
			usuarioDTOs.setId(rs.getInt("id"));
			usuarioDTOs.setNombre(Cifrado.decifrar(clave, vi, rs.getString("nombre")));
			usuarioDTOs.setApellido(rs.getString("apellido"));
			usuarioDTOs.setFecha_nac(rs.getString("fecha_nac"));
		}
		return usuarioDTOs;
	}

	@Override
	public boolean actualizar(UsuarioDTO userDTO) throws Exception {
		String actualizar = "update usuario set nombre=?, apellido=?, fecha_nac=? where id=?";
		conn = ConnectionFactory.getInstance().getConexion();
		if(conn == null) {
			System.out.println("Fallo en la conexión");
		}
		PreparedStatement ps = conn.prepareStatement(actualizar);
		ps.setString(1, Cifrado.cifrar(clave, vi, userDTO.getNombre()));
		ps.setString(2, Cifrado.cifrar(clave, vi, userDTO.getApellido()));
		ps.setString(3, userDTO.getFecha_nac());
		ps.setInt(4, userDTO.getId());
		
		boolean resultado = ps.executeUpdate() > 0;
		if(resultado) {
			System.out.println("Registro actualizado");
		} else {
			System.out.println("No fue posible actualzar");
		}
		
		if(ps != null) {
			ps.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return resultado;
	}

	@Override
	public boolean borrar(int idUsuario) throws Exception {
		String borrar = "delete from usuario where id=?";
		conn = ConnectionFactory.getInstance().getConexion();
		if(conn == null) {
			System.out.println("No se estableció conexion");
		}
		PreparedStatement ps = conn.prepareStatement(borrar);
		ps.setInt(1, idUsuario);
		
		boolean resultado = ps.executeUpdate() > 0;
		if(resultado) {
			System.out.println("Registro borrado");
		} else {
			System.out.println("No fue posible borrar");
		}
		if(ps != null) {
			ps.close();
		}
		if(conn != null) {
			conn.close();
		}
		return resultado;
	}

	@Override
	public boolean agregar(UsuarioDTO dto) throws Exception {
		String agregar = "insert into usuario (id, nombre, apellido, fecha_nac) values (?,?,?,?)";
		conn = ConnectionFactory.getInstance().getConexion();
		if(conn == null) {
			System.out.println("Fallo en la conexion");
		}
		PreparedStatement ps = conn.prepareStatement(agregar);
		ps.setInt(1, dto.getId());
		ps.setString(2, Cifrado.cifrar(clave, vi, dto.getNombre()));
		ps.setString(3, Cifrado.cifrar(clave, vi, dto.getApellido()));
		ps.setString(4, dto.getFecha_nac());
		ps.executeUpdate();
		boolean result = ps.getUpdateCount() > 0;
		if(result) {
			System.out.println("Registro agregado");
		} else {
			System.out.println("No se agregó el registro");
		}
		if(ps != null) {
			ps.close();
		}
		if(conn != null) {
			conn.close();
		}
		return result;
	}

	
}
