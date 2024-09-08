package ru.panic.springsteamauthexample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("users_table")
@Data
public class User {
    @Id
    private String id;

    @Column("registered_at")
    private Date registeredAt;
}
