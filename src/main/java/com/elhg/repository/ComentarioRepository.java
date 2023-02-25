package com.elhg.repository;


import com.elhg.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
  List<Comentario> findByUsuarioId(Long usuarioId);
  
  @Transactional
  void deleteByUsuarioId(long usuarioId);

  Optional<Comentario> findByUsuarioIdAndId(Long usuarioId, Long id);

  List<Comentario> findAll();

}
