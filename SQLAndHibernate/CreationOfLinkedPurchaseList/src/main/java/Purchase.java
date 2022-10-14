import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Purchase {
    @EmbeddedId
    private PrimaryKey primaryKey = new PrimaryKey();
    private int price;
//    @Column(name = "subscription_date")
//    private Date subscriptionDate;

    @Transient
    public int getStudentId() {
        return primaryKey.studentId;
    }

    @Transient
    public int getCourseId() {
        return primaryKey.courseId;
    }

    @Embeddable
    @EqualsAndHashCode
    @Data
    static class PrimaryKey implements Serializable {
        @Column(name = "student_id")
        private int studentId;
        @Column(name = "course_id")
        private int courseId;
    }
}
