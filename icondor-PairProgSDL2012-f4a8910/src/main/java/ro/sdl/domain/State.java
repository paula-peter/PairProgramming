package ro.sdl.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rbordea
 * Date: 24.10.2011
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public enum State {
    SENIOR,
    MID,
    JUNIOR;
    private final String value;

    private State() {
        this.value = this.name().toLowerCase();
    }

    public String getValue() {
        return this.value;
    }
}
