package com.cv.cv_c_r_u_d.dataaccessopject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cv.cv_c_r_u_d.models.Users;

public interface UsersAccOp extends JpaRepository<Users, Long> {

}
