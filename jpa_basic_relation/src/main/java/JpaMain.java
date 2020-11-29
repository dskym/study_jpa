import domain.Member;
import domain.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        testSave();
        queryLogicJoin(em);
        updateRelation(em);
        deleteRelation(em);
        biDirection();
        testSaveNonOwner();
    }

    public static void testSave() {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    private static void queryLogicJoin(EntityManager em) {
        String jpq1 = "select m from Member m join m.team t where " + "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpq1, Member.class)
                                    .setParameter("teamName", "팀1")
                                    .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username = " + member.getUsername());
        }
    }

    private static void updateRelation(EntityManager em) {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    public static void biDirection() {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for(Member member : members) {
            System.out.println("member.username = " + member.getUsername());
        }
    }

    public static void testSaveNonOwner() {
        Member member3 = new Member("member3", "회원3");
        em.persist(member3);

        Member member4 = new Member("member4", "회원4");
        em.persist(member4);

        Team team3 = new Team("team3", "팀3");
        team3.getMembers().add(member3);
        team3.getMembers().add(member4);

        em.persist(team3);
    }
}
