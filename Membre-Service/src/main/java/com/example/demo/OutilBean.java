package com.example.demo;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
public class OutilBean {
    Long id;
    @Temporal(TemporalType.DATE)
    Date date;
    String source;
}
