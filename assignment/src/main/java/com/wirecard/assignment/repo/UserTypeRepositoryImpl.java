package com.wirecard.assignment.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.wirecard.assignment.model.UserType;

import liquibase.util.StringUtils;

@Service
public class UserTypeRepositoryImpl implements UserTypeRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UserType> search(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<UserType> cr = cb.createQuery(UserType.class);
		final Root<UserType> root = cr.from(UserType.class);

		List<Predicate> predicateList = new ArrayList<>();

		if (StringUtils.isNotEmpty(name)) {
			Predicate predicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
			predicateList.add(predicate);
		}

		cr.where(predicateList.toArray(new Predicate[0]));

		if (StringUtils.isNotEmpty(sortBy)) {
			Order order = null;
			if (sortType.equalsIgnoreCase("desc")) {
				order = cb.desc(root.get(sortBy.toLowerCase()));
			} else {
				order = cb.asc(root.get(sortBy.toLowerCase()));
			}
			cr.orderBy(order);
		}

		return em.createQuery(cr).setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
	}

}
