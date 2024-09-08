package ru.panic.springsteamauthexample.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.panic.springsteamauthexample.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
