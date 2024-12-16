package di.fa.kacommon.common;

import lombok.Getter;

public interface Type {

    @Getter
    enum Email implements Type {
        REGISTER_001("REG-001"),
        WELCOME_001("WEL-001"),
        ;
        private final String type;

        Email(final String type) {
            this.type = type;
        }

    }
}
