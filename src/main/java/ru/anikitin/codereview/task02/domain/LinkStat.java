package ru.anikitin.codereview.task02.domain;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class LinkStat {

    @Id
    private Long id;

    @Column
    private Integer rank;

    @Column
    private Integer requestCount;

    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime updated;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Link link;

    public void incrementCount() {
        requestCount++;
    }
}
