package com.wirecard.assignment.repo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wirecard.assignment.model.User;

import liquibase.util.StringUtils;

@Service
public class UserRepositoryImpl implements UserRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Override
	public List<User> search(String name, Date date, Integer pageNumber, Integer pageSize, String sortBy,
			String sortType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<User> cr = cb.createQuery(User.class);
		final Root<User> root = cr.from(User.class);

		List<Predicate> predicateList = new ArrayList<>();
		if (StringUtils.isNotEmpty(name)) {
			Predicate predicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
			predicateList.add(predicate);
		}
		if (date != null) {
			Predicate predicate = cb.equal(cb.function(getFunction(activeProfile), Date.class, root.get("date"),
					cb.literal(getLiteral(activeProfile))), new SimpleDateFormat("yyyy-MM-dd").format(date));
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

	private String getFunction(String activeProfile) {
		if (activeProfile.equals("local")) {
			return "FORMATDATETIME";
		}
		return "DATE_FORMAT";
	}

	private String getLiteral(String activeProfile) {
		if (activeProfile.equals("local")) {
			return "YYYY-MM-DD";
		}
		return "%Y-%m-%d";
	}

}
