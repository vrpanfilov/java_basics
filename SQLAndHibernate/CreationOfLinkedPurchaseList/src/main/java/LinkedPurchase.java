import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "linkedPurchaseList")
@Data
public class LinkedPurchase {
    @EmbeddedId
    private PrimaryKey primaryKey = new PrimaryKey();

    public int getStudentId() {
        return primaryKey.student.getId();
    }

    public void setStudentId(int studentId) {
        primaryKey.student.setId(studentId);
    }

    public int getCourseId() {
        return primaryKey.course.getId();
    }

    public void setCourseId(int courseId) {
        primaryKey.course.setId(courseId);
    }

    @Embeddable
    @Data
    public static class PrimaryKey implements Serializable {
        @ManyToOne(cascade = CascadeType.ALL)
        private Student student;
        @ManyToOne(cascade = CascadeType.ALL)
        private Course course;
        public PrimaryKey() {
            student = new Student();
            course = new Course();
        }
    }
}
