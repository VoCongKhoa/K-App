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

    @Getter
    enum DevTools implements Type {
        CONFIG("config"),
        TOOL("tool"),
        ;
        private final String type;

        DevTools(final String type) {
            this.type = type;
        }

    }

    @Getter
    enum Action implements Type {
        INSERT("insert"),
        UPDATE("update"),
        DELETE("delete"),
        ;
        private final String type;

        Action(final String type) {
            this.type = type;
        }

    }
}
