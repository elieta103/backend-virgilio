package com.elhg.controller;

import java.util.ArrayList;
import java.util.List;

import com.elhg.exception.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elhg.model.Usuario;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

  @Autowired
  UsuarioRepository usuarioRepository;

  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String nombre) {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    if (nombre == null)
      usuarioRepository.findAll().forEach(usuarios::add);
    else
      usuarioRepository.findByNombre(nombre).forEach(usuarios::add);

    if (usuarios.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(usuarios, HttpStatus.OK);
  }

  @GetMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Usuario with id = " + id));

    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }

  @PostMapping("/usuarios")
  public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
    Usuario _usuario = usuarioRepository.save(new Usuario(usuario.getNombre(), usuario.getEmail()));
    return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
  }

  @PutMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
    Usuario _usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Usuario with id = " + id));

    _usuario.setNombre(usuario.getNombre());
    _usuario.setEmail(usuario.getEmail());

    
    return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
  }

  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
    usuarioRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/usuarios")
  public ResponseEntity<HttpStatus> deleteAllUsuarios() {
    usuarioRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
