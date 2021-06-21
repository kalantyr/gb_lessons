package ru.kalantyr.lesson11.entitites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraph(
        name = "University.with-groups",
        attributeNodes = {
                @NamedAttributeNode("groups")
        }
)
@NamedEntityGraph(
        name = "University.with-groups-and-students",
        attributeNodes = {
                @NamedAttributeNode(value = "groups", subgraph = "groups-students")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "groups-students",
                        attributeNodes = {
                                @NamedAttributeNode("students")
                        }
                )
        }
)
@Entity
@NoArgsConstructor
@Data
@Table(name = "universities")
public class University {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "university")
    private Set<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id.equals(that.id) && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
