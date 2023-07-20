package com.project.blogapplication.Repository;

import com.project.blogapplication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRole(String name);
}
