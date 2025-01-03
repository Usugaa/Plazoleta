package com.restaurant.plazoleta.domain.usecase;

import com.restaurant.plazoleta.domain.api.IUserServicePort;
import com.restaurant.plazoleta.domain.constants.RoleConstants;
import com.restaurant.plazoleta.domain.model.Role;
import com.restaurant.plazoleta.domain.model.User;
import com.restaurant.plazoleta.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        if (userPersistencePort.existsByDocumentNumber(user.getDocumentNumber())) {
            throw new IllegalArgumentException("El documento ya está registrado.");
        }

        // Validar el rol
        if (user.getIdRole() == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo.");
        }
        Role role = validateRole(user.getIdRole());
        user.setIdRole(role.getId());

        // Encriptar la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardar el usuario
        userPersistencePort.saveUser(user);

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userPersistencePort.getUserById(id);

        return user;
    }

    private Role validateRole(Long roleId) {
        if (roleId == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo.");
        }
        if (roleId.equals(RoleConstants.ADMINISTRADOR)) {
            return new Role(RoleConstants.ADMINISTRADOR, RoleConstants.ADMINISTRADOR_NAME, RoleConstants.ADMINISTRADOR_DESCRIPTION);
        } else if (roleId.equals(RoleConstants.PROPIETARIO)) {
            return new Role(RoleConstants.PROPIETARIO, RoleConstants.PROPIETARIO_NAME, RoleConstants.PROPIETARIO_DESCRIPTION);
        } else if (roleId.equals(RoleConstants.EMPLEADO)) {
            return new Role(RoleConstants.EMPLEADO, RoleConstants.EMPLEADO_NAME, RoleConstants.EMPLEADO_DESCRIPTION);
        } else if (roleId.equals(RoleConstants.CLIENTE)) {
            return new Role(RoleConstants.CLIENTE, RoleConstants.CLIENTE_NAME, RoleConstants.CLIENTE_DESCRIPTION);
        } else {
            throw new IllegalArgumentException("El rol ingresado no es válido: " + roleId);
        }
    }
}