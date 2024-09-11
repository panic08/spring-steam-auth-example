package ru.panic.springsteamauthexample.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.panic.springsteamauthexample.model.enums.UserLinkedSocialType;

import java.util.UUID;

@Table("user_linked_socials_table")
@Data
@Builder
public class UserLinkedSocial {
    @Id
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column("social_type")
    private UserLinkedSocialType socialType;

    @Column("verification_data")
    private String verificationData;
}
