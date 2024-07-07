package googlecalendarapitest.googlecalendarapitest.domain.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    @Column(name = "todo")
    private String todo;

    @Column(name = "excute_date")
    private LocalDateTime excuteDate;

    @Builder
    public Todo(String todo, LocalDateTime excuteDate) {
        this.todo = todo;
        this.excuteDate = excuteDate;
    }

}
