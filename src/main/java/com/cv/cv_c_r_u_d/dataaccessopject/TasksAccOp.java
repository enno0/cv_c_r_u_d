package com.cv.cv_c_r_u_d.dataaccessopject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cv.cv_c_r_u_d.models.Tasks;

public interface TasksAccOp extends JpaRepository<Tasks, Long>, JpaSpecificationExecutor<Tasks> {

}
