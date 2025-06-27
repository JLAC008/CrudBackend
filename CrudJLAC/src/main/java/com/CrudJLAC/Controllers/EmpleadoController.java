package com.CrudJLAC.Controllers;

import com.CrudJLAC.Entities.Empleado;
import com.CrudJLAC.Services.EmpleadoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    /**
     * Obtiene todos los empleados
     * @return Lista de empleados
     */
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    /**
     * Obtiene un empleado por su ID
     * @param id ID del empleado
     * @return Empleado encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        try {
            Empleado empleado = empleadoService.getEmpleadoById(id);
            return ResponseEntity.ok(empleado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea un nuevo empleado
     * @param empleado Empleado a crear
     * @return Empleado creado
     */
    @PostMapping
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        Empleado savedEmpleado = empleadoService.saveEmpleado(empleado);
        return new ResponseEntity<>(savedEmpleado, HttpStatus.CREATED);
    }

    /**
     * Actualiza un empleado existente
     * @param id ID del empleado a actualizar
     * @param empleadoDetails Detalles actualizados del empleado
     * @return Empleado actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleadoDetails);
            return ResponseEntity.ok(updatedEmpleado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un empleado por su ID
     * @param id ID del empleado a eliminar
     * @return Respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        try {
            empleadoService.deleteEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
