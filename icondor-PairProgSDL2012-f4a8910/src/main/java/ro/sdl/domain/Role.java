package ro.sdl.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rbordea
 * Date: 24.10.2011
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public enum Role {
    QA,
    DEV;

    private final String value;

    private Role() {
        this.value = this.name().toLowerCase();
    }

    public String getValue() {
        return this.value;
    }
}
