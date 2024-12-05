package uz.travel.abuusmon.dto;

import java.time.LocalDateTime;

public record AppErrorDto(String errorMessage, int errorCode, String errorPath, LocalDateTime timestamp) {
}
