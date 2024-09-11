package ru.panic.springsteamauthexample.dto;

import java.util.Date;

public record UserDto(String id, String username, Date registeredAt) {
}
