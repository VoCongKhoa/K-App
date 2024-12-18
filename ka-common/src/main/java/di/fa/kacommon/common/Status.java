package di.fa.kacommon.common;

import lombok.Getter;

public interface Status {

    @Getter
    enum Module implements Status {
        ACTIVE("active"),
        DE_ACTIVE("de-active"),
        BLOCK("block"),
        NEWBIE("newbie"),
        ;
        private final String status;

        Module(final String status) {
            this.status = status;
        }
    }

    @Getter
    enum User implements Status {
        ACTIVE("active"),
        DE_ACTIVE("de-active"),
        BLOCK("block"),
        NEWBIE("newbie"),
        ;
        private final String status;

        User(final String status) {
            this.status = status;
        }

    }

    @Getter
    enum Role implements Status {
        ACTIVE("active"),
        DE_ACTIVE("de-active"),
        DELETED("deleted"),
        ;
        private final String status;

        Role(final String status) {
            this.status = status;
        }

    }

    @Getter
    enum Permission implements Status {
        ACTIVE("active"),
        DE_ACTIVE("de-active"),
        DELETED("deleted"),
        ;
        private final String status;

        Permission(final String status) {
            this.status = status;
        }

    }

    @Getter
    enum Login implements Status {
        SUCCESS("success"),
        FAILED("failed"),
        ;
        private final String status;

        Login(final String status) {
            this.status = status;
        }
    }
}
