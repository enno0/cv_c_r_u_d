package com.cv.cv_c_r_u_d.specification;

import org.springframework.data.jpa.domain.Specification;

import com.cv.cv_c_r_u_d.models.Tasks;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TasksSpecification implements Specification<Tasks> {

    private String fieldName;
    private String value;

    public TasksSpecification(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @SuppressWarnings("null")
    @Override
    public Predicate toPredicate(Root<Tasks> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(fieldName), value);
    }
}
