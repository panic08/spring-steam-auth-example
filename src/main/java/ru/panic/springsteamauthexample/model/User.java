package ru.panic.springsteamauthexample.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("users_table")
@Data
@Builder
public class User {
    @Id
    private UUID id;

    private String username;

    @Column("registered_at")
    private Date registeredAt;
}
