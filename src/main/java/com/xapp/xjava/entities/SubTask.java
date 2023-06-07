package com.xapp.xjava.entities;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private String subTaskTitle;
    private String subTaskStatus;
}
