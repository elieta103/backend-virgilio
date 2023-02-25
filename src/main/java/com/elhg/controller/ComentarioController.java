package com.elhg.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.elhg.dto.ComentarioDto;
import com.elhg.model.Comentario;
import com.elhg.repository.ComentarioRepository;
import com.elhg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elhg.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ComentarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ComentarioRepository comentarioRepository;


  @GetMapping("/usuario/{usuarioId}/comentarios")
  public ResponseEntity<List<Comentario>> getAllComentariosByUsuarioId(@PathVariable(value = "usuarioId") Long usuarioId) {
    if (!usuarioRepository.existsById(usuarioId)) {
      throw new ResourceNotFoundException("Not found Usuario with id = " + usuarioId);
    }

    List<Comentario> comentarios = comentarioRepository.findByUsuarioId(usuarioId);
    return new ResponseEntity<>(comentarios, HttpStatus.OK);
  }

  @GetMapping("/comentarios/{idUsuario}/{idComentario}")
  public ResponseEntity<Comentario> getComentarioByUsuarioIdAndComentarioId(@PathVariable(value = "idUsuario") Long idUsuario,
                                                                            @PathVariable(value = "idComentario") Long idComentario) {
    Comentario comentario = comentarioRepository.findByUsuarioIdAndId(idUsuario, idComentario)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Comentario with id = " + idUsuario));

    return new ResponseEntity<>(comentario, HttpStatus.OK);
  }

  @GetMapping("/comentarios/{id}")
  public ResponseEntity<Comentario> getComentariosByUsuarioId(@PathVariable(value = "id") Long id) {
    Comentario comentario = comentarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Comentario with id = " + id));

    return new ResponseEntity<>(comentario, HttpStatus.OK);
  }

  @GetMapping("/comentarios")
  public ResponseEntity<List<ComentarioDto>> getAllComentarios() {
    List<Comentario> listAll = comentarioRepository.findAll();
    List<ComentarioDto> listAllDto = listAll.stream().map(item-> {
      ComentarioDto dto = new ComentarioDto(item.getId(), item.getUsuario().getNombre(), item.getContenido());
      return dto;
      }).collect(Collectors.toList());

    return new ResponseEntity<>(listAllDto, HttpStatus.OK);
  }

  @PostMapping("/usuario/{usuarioId}/comentarios")
  public ResponseEntity<Comentario> createComentario(@PathVariable(value = "usuarioId") Long usuarioId,
      @RequestBody Comentario comentarioRequest) {
    Comentario comentario = usuarioRepository.findById(usuarioId).map(usuario -> {
      comentarioRequest.setUsuario(usuario);
      return comentarioRepository.save(comentarioRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Usuario with id = " + usuarioId));

    return new ResponseEntity<>(comentario, HttpStatus.CREATED);
  }

  @PutMapping("/comentarios/{id}")
  public ResponseEntity<Comentario> updateComment(@PathVariable("id") long id, @RequestBody Comentario comentarioRequest) {
    Comentario comentario = comentarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ComentarioId " + id + "not found"));

    comentario.setContenido(comentarioRequest.getContenido());

    return new ResponseEntity<>(comentarioRepository.save(comentario), HttpStatus.OK);
  }

  @DeleteMapping("/comentarios/{id}")
  public ResponseEntity<HttpStatus> deleteComentario(@PathVariable("id") long id) {
    comentarioRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/usuario/{usuarioId}/comentarios")
  public ResponseEntity<List<Comentario>> deleteAllComentariosOfUsuario(@PathVariable(value = "usuarioId") Long usuarioId) {
    if (!usuarioRepository.existsById(usuarioId)) {
      throw new ResourceNotFoundException("Not found Usuario with id = " + usuarioId);
    }

    comentarioRepository.deleteByUsuarioId(usuarioId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
