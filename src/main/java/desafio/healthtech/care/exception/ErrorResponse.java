package desafio.healthtech.care.exception;

public record ErrorResponse(
        int status,
        String message
) {}
