package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Outil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull
    @Temporal(TemporalType.DATE)
    Date date;
    @NonNull
    String source;
}
