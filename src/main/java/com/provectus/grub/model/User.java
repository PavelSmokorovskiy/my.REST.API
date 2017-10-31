package com.provectus.grub.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.*;

@Entity
@Document(collection = "users")
public class User implements UserDetails {

    //    @Id
    private int id;
    //    @Indexed(unique = true)
    private String username;
    private String password;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private int companyId;
    private String companyName;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean emailConfirmation;
    private String activationCode;

    private List<Food> food;


    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(int id) {
            User.this.id = id;
            return this;
        }

        public Builder username(String username) {
            User.this.username = username;
            return this;
        }

        public Builder roles(Role role) {
            if (User.this.roles == null) {
                User.this.roles = new HashSet<>();
            }

            User.this.roles.add(role);
            return this;
        }

        public Builder roles(Collection<Role> roles) {
            if (User.this.roles == null) {
                User.this.roles = new HashSet<>();
            }

            User.this.roles.addAll(roles);
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder companyId(int companyId) {
            User.this.companyId = companyId;
            return this;
        }

        public Builder companyName(String companyName) {
            User.this.companyName = companyName;
            return this;
        }

        public Builder accountNonExpired(boolean accountNonExpired) {
            User.this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder accountNonLocked(boolean accountNonLocked) {
            User.this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder credentialsNonExpired(boolean credentialsNonExpired) {
            User.this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder enabled(boolean enabled) {
            User.this.enabled = enabled;
            return this;
        }

        public Builder emailConfirmation(boolean emailConfirmation) {
            User.this.emailConfirmation = emailConfirmation;
            return this;
        }

        public Builder activationCode(String activationCode) {
            User.this.activationCode = activationCode;
            return this;
        }

        public Builder food(Food food) {
            if (User.this.food == null) {
                User.this.food = new ArrayList<>();
            }

            User.this.food.add(food);
            return this;
        }

        public User build() {
            return User.this;
        }

    }

    void addFood(Food foodName){

        User.this.food = new ArrayList<>();
        food.add(foodName);
    }






    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(boolean emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
