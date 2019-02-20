package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JPAMain2 {

    public static void main(String[] args) {

        // Create Entity manager factory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // start transaction
            tx.begin();

            // business logic
            logic(em);

            // commit transaction
            tx.commit();

        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void logic(EntityManager em) {

        String id = "id1";
        Member2 member = new Member2();
        member.setId(id);
        member.setUsername("yjkim");
        member.setAge(2);

        // insert
        em.persist(member);
        // update
        member.setAge(20);
        // select one
        Member2 findMember = em.find(Member2.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        // select list
        List<Member2> members = em.createQuery("select m from Member2 m", Member2.class).getResultList();
        System.out.println("members.size=" + members.size());
    }
}
