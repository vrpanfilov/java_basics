import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int id;
    private String name;
    @Column(columnDefinition = "int unsigned")
    private int salary;
    @Column(columnDefinition = "int unsigned")
    private int age;
}
