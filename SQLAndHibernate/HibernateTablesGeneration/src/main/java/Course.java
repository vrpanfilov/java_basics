import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count")
    private Integer studentCount;
    private Integer price;
    @Column(name = "price_per_hour")
    private Float pricePerHour;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;
}
