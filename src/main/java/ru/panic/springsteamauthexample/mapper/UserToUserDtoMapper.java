package ru.panic.springsteamauthexample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.panic.springsteamauthexample.dto.UserDto;
import ru.panic.springsteamauthexample.model.User;

@Mapper(componentModel = "spring")
public interface UserToUserDtoMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "registeredAt", source = "registeredAt")
    })
    UserDto userToUserDto(User user);
}
