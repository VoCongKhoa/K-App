package di.fa.kacommon.common;

import lombok.Getter;

public interface Name {

    @Getter
    enum MainLanguage implements Name {
        JAVA("java"),
        WELCOME_001("WEL-001"),
        ;
        private final String type;

        MainLanguage(final String type) {
            this.type = type;
        }

    }

    @Getter
    enum SubLanguage implements Name {
        REGISTER_001("REG-001"),
        WELCOME_001("WEL-001"),
        ;
        private final String type;

        SubLanguage(final String type) {
            this.type = type;
        }

    }

}
