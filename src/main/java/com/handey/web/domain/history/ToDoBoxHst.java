package com.handey.web.domain.history;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.handey.web.domain.home.ToDoElm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todo_box_hst")
@DynamicInsert
@DynamicUpdate
public class ToDoBoxHst {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 ++해서 id 생성하는 것 = identity 전략
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "savedt")
    private String saveDt;

    @OneToMany(mappedBy = "toDoBoxHst")
    @JsonManagedReference
    private List<ToDoElmHst> toDoElmHstList = new ArrayList<ToDoElmHst>();
}