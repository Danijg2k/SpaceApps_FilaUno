package com.example.demo;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.File;
import com.example.demo.DTOs.FileAdd;

@RestController
@RequestMapping("/file")
public class FileController {

	@PostMapping("Transfer")
	public String TrasnferirArchivo(@RequestBody File fileBody) {
		// Cogemos el archivo desde este directorio local (los archivos en
		// NodoArchivosNodo)
		// Creamos un String con la información del archivo
		// Leer archivo como string y devolverlo.

		return null;
	}

}
