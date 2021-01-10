package orangetalents.com.enums;

import lombok.Getter;

@Getter
public enum ContaError {

    EMAIL_ALREADY_EXISTS("Email já cadastrado!"),

    CPF_ALREADY_EXISTS("CPF já cadastrado!");

    private String error;

    ContaError(String error) {
        this.error = error;
    }
}
