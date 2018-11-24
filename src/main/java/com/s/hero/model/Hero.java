package com.s.hero.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpa on 11/23/18
 */
@Data
@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(example = "Steve Rodgers")
    private String name;
    @ApiModelProperty(example = "Captain America")
    private String title;
    @ApiModelProperty(example = "Super human")
    private String description;
    @ApiModelProperty(example = "Requested")
    private String status;
    private String authorComment;
    private String arproverComment;
    @ApiModelProperty(hidden = true)
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "heroId")
    List<RequestMessage> messages = new ArrayList<>();

}
