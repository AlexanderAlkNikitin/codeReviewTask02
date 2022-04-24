package ru.anikitin.codereview.task02.domain;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = "linkStat")
@AllArgsConstructor
@RequiredArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String shortLink;
    @Column
    private String originalLink;
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime created;
    @OneToOne(mappedBy = "link",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private LinkStat linkStat;
}
