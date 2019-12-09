package net.ukr.tigor.service;

import net.ukr.tigor.domain.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public final class EventsSpecifications {

    public static Specification<Event> getSpecifications(Map<String, Object> selected) {
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                for (Map.Entry<String, Object> pair : selected.entrySet()) {
                    String field = pair.getKey();
                    Object value = pair.getValue();
                    if (value == null) {
                        continue;
                    }
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get(field), value));
                }
                return predicate;
            }
        };

    }
}
