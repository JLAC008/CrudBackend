package com.CrudJLAC.Services;

import com.CrudJLAC.Entities.Empleado;
import com.CrudJLAC.Repositories.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    /**
     * Obtiene todos los empleados
     * @return Lista de empleados
     */
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Obtiene un empleado por su ID
     * @param id ID del empleado
     * @return Empleado encontrado
     * @throws EntityNotFoundException Si no se encuentra el empleado
     */
    public Empleado getEmpleadoById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + id));
    }

    /**
     * Guarda un nuevo empleado
     * @param empleado Empleado a guardar
     * @return Empleado guardado
     */
    @Transactional
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /**
     * Actualiza un empleado existente
     * @param id ID del empleado a actualizar
     * @param empleadoDetails Detalles actualizados del empleado
     * @return Empleado actualizado
     * @throws EntityNotFoundException Si no se encuentra el empleado
     */
    @Transactional
    public Empleado updateEmpleado(Long id, Empleado empleadoDetails) {
        Empleado empleado = getEmpleadoById(id);

        // Actualizar propiedades
        empleado.setNombre(empleadoDetails.getNombre());
        empleado.setApellido(empleadoDetails.getApellido());
        empleado.setEmail(empleadoDetails.getEmail());
        empleado.setTelefono(empleadoDetails.getTelefono());
        empleado.setDireccion(empleadoDetails.getDireccion());
        empleado.setPuesto(empleadoDetails.getPuesto());
        empleado.setSalario(empleadoDetails.getSalario());
        empleado.setFecha_nacimiento(empleadoDetails.getFecha_nacimiento());
        empleado.setFecha_ingreso(empleadoDetails.getFecha_ingreso());
        empleado.setFecha_salida(empleadoDetails.getFecha_salida());

        return empleadoRepository.save(empleado);
    }

    /**
     * Elimina un empleado por su ID
     * @param id ID del empleado a eliminar
     * @throws EntityNotFoundException Si no se encuentra el empleado
     */
    @Transactional
    public void deleteEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EntityNotFoundException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }
}
