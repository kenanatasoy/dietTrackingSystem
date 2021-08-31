package api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {

    private String errorMessage;

    private String errorCode;

    private LocalDateTime timeStamp;

}
