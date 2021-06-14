package uol.compasso.productms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionMessage {
    private int status_code;
    private String message;
}
