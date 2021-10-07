package com.handey.web.weekly;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaWeeklyRepository implements WeeklyRepository {

    private final EntityManager em;

    public JpaWeeklyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public WeeklyBox save(WeeklyBox weeklyBox) {
        em.persist(weeklyBox);
        return weeklyBox;
    }

    @Override
    public Optional<WeeklyBox> findById(Long id) {
        WeeklyBox weeklyBox = em.find(WeeklyBox.class, id);
        return Optional.ofNullable(weeklyBox);
    }

    @Override
    public Optional<WeeklyBox> findByTitle(String title) {
        List<WeeklyBox> result = em.createQuery("select m from WeeklyBox m where m.title = :title", WeeklyBox.class)
                .setParameter("title", title)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<WeeklyBox> findByUserId(Long userId) {
        return em.createQuery("select m from WeeklyBox m where m.member.id = :userId", WeeklyBox.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<WeeklyBox> findAll() {
        //객체 자체를 조회
        return em.createQuery("select m from WeeklyBox as m", WeeklyBox.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        WeeklyBox weeklyBox = em.find(WeeklyBox.class, id);
        Assert.notNull(weeklyBox,"WeeklyBox must not be null!");
        em.remove(weeklyBox);
    }
}
