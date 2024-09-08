package ru.panic.springsteamauthexample.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.panic.springsteamauthexample.model.UserLinkedSocial;

@Repository
public interface UserLinkedSocialRepository extends CrudRepository<UserLinkedSocial, String> {
}
