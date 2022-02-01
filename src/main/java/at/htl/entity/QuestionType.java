package at.htl.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "QuestionType")
public class QuestionType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id")
    Long id;

    @Column(name = "qt_name")
    String name;

    //TODO JPA map
/*
    @ElementCollection
    @CollectionTable(name = "questionType")
    @MapKeyColumn(name = "user_type_col")
    @Column(name = "qt_map")
    Map<Long, String> map = new HashMap<>();

    public Map<Long, String> getMap() {
        return map;
    }

    public void setMap(Long id, String name) {
        map.put(id, name);
    }
*/

    public QuestionType() {
    }

    public QuestionType(Long id, String name) {
        this.id = id;
        this.name = name;
        //setMap(id, name);
    }
}
