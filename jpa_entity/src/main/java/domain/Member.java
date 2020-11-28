package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
//@SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        sequenceName = "BOARD_SEQ",
//        initialValue = 1, allocationSize = 1
//)
//@TableGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        table = "MY_SEQUENCE",
//        pkColumnValue = "BOARD_SEQ", allocationSize = 1
//)
public class Member {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//                    generator = "BOARD_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE,
//                    generator = "BOARD_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public enum RoleType {
        ADMIN, USER
    }
}
