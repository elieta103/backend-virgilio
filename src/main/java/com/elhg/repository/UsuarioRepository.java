package com.elhg.repository;


import com.elhg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  List<Usuario> findByEmail(String email);

  List<Usuario> findByNombre(String nombre);
}
