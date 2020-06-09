package tonels.feature.domain;

import java.time.*;
import java.util.Date;

public class Person {
    private Integer id;
    private String name;
    private Date javaDate;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Instant instant;
    private Duration duration;
    private Clock clock;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", javaDate=" + javaDate +
                ", localDate=" + localDate +
                ", localDateTime=" + localDateTime +
                ", instant=" + instant +
                ", duration=" + duration +
                ", clock=" + clock +
                '}';
    }

    public Person(Integer id,
                  String name,
                  Date javaDate,
                  LocalDate localDate,
                  LocalDateTime localDateTime,
                  Instant instant,
                  Duration duration,
                  Clock clock) {
        this.id = id;
        this.name = name;
        this.javaDate = javaDate;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
        this.instant = instant;
        this.duration = duration;
        this.clock = clock;
    }

    public Person() {
        // TODO Auto-generated constructor stub
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJavaDate() {
        return javaDate;
    }

    public void setJavaDate(Date javaDate) {
        this.javaDate = javaDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
