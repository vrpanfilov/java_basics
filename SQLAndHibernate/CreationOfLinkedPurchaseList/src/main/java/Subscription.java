import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    @EmbeddedId
    private PrimaryKey primaryKey;
    @Column(name = "subscription_date", nullable = false)
    private Date subscriptionDate;

    @Embeddable
    @Data
    public class PrimaryKey implements Serializable {
        @Column(name = "student_id", columnDefinition = "int unsigned")
        private int studentId;
        @Column(name = "course_id", columnDefinition = "int unsigned")
        private int courseId;
    }
}
