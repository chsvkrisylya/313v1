package ru.alexeykedr.springbootbootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexeykedr.springbootbootstrap.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Role findByName(String name);
}
