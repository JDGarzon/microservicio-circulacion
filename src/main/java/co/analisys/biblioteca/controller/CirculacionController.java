package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.LibroId;
import co.analisys.biblioteca.model.Prestamo;
import co.analisys.biblioteca.model.PrestamoId;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.CirculacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circulacion")
public class CirculacionController {
    @Autowired
    private CirculacionService circulacionService;


    @Operation(
        summary = "Prestar un libro",
        description = "Registra el préstamo de un libro a un usuario identificado por su ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Préstamo registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Usuario o libro no encontrado")
    })
    @PostMapping("/prestar")
    @PreAuthorize("hasRole('ROLE_EDITAR')")
    public void prestarLibro(@RequestParam String usuarioId, @RequestParam String libroId) {
        circulacionService.prestarLibro(new UsuarioId(usuarioId), new LibroId(libroId));
    }

    @Operation(
        summary = "Devolver un libro",
        description = "Registra la devolución de un libro previamente prestado. Se actualiza el estado del préstamo."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro devuelto exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @PostMapping("/devolver")
    @PreAuthorize("hasRole('ROLE_EDITAR')")
    public void devolverLibro(@RequestParam String prestamoId) {
        circulacionService.devolverLibro(new PrestamoId(prestamoId));
    }

    @Operation(
        summary = "Consultar todos los préstamos",
        description = "Obtiene una lista de todos los préstamos registrados en el sistema."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de préstamos obtenida exitosamente"),
        @ApiResponse(responseCode = "403", description = "Acceso no autorizado")
    })
    @GetMapping("/prestamos")
    @PreAuthorize("hasRole('ROLE_CONSULTAR')")
    public List<Prestamo> obtenerTodosPrestamos() {
        return circulacionService.obtenerTodosPrestamos();
    }
}
