package uz.travel.abuusmon.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.travel.abuusmon.dto.AppErrorDto;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDto> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        AppErrorDto appErrorDto = new AppErrorDto(e.getMessage(),404,request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(appErrorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        AppErrorDto appErrorDto = new AppErrorDto(ex.getMessage(),400,request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(appErrorDto);
    }

}
