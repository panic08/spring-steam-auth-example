package ru.panic.springsteamauthexample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.panic.springsteamauthexample.model.enums.UserLinkedSocialType;

@Table("user_linked_socials_table")
@Data
public class UserLinkedSocial {
    @Id
    private String id;

    @Column("user_id")
    private String userId;

    @Column("social_type")
    private UserLinkedSocialType socialType;

    @Column("verification_data")
    private String verificationData;
}
