import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    @EmbeddedId
    private PK id;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    @EqualsAndHashCode
    @ToString
    @Data
    @AllArgsConstructor
    public static class PK implements Serializable {
        @Column(name = "student_id")
        private int studentId;
        @Column(name = "course_id")
        private int courseId;
    }
}
