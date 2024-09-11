package ru.panic.springsteamauthexample.repository.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.panic.springsteamauthexample.model.UserLinkedSocial;
import ru.panic.springsteamauthexample.model.enums.UserLinkedSocialType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserLinkedSocialRepository extends CrudRepository<UserLinkedSocial, UUID> {
    @Query("SELECT uls.* FROM user_linked_socials_table uls WHERE uls.verification_data = :verificationData AND uls.social_type = :socialType")
    UserLinkedSocial findByVerificationDataAndSocialType(@Param("verificationData") String verificationData,
                                                         @Param("socialType") UserLinkedSocialType socialType);
}
