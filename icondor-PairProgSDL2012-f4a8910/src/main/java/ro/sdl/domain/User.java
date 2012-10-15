package ro.sdl.domain;

public class User {

    private Integer id;
    private String name;
    private Role role;     // dev, qa
    private State state;  // jr, mid , senior
    private Project project;

    public User(Integer id, String name, Role role, State state) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.state = state;
    }

    public User(){
    
    }

    public User(Integer id, String name, Role role, State state, Project project) {
        this(id, name, role, state);
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (state != null ? !state.equals(user.state) : user.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
