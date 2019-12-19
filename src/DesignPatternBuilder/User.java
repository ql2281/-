package DesignPatternBuilder;

import java.util.Date;
import java.util.UUID;

class User {  // ORM Object Relationship Mapping
    private final String userId;
    private String userName;  //required
    private String passWord;  //required
    private final String firstName;
    private final String lastName;
    private String phone;
    private String email;
    private Date timeJoined;

    private User(UserBuilder builder) {  // JSONObject object 直接在constructor传入一个完整的object
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.timeJoined = builder.timeJoined;
    }

    @Override
    public String toString() {
        return userId + ", " + userName + ", " + passWord + ", " + lastName + ", " + firstName + ", " + phone + ", " + email + ", " + timeJoined;
    }

    public static class UserBuilder {  //标记为static的variable，method，class可以直接访问
        private String userId;
        private String userName;  //required
        private String passWord;  //required
        private String firstName;  //这里要 private final String firstname;  ？？？
        private String lastName;
        private String phone;
        private String email;
        private Date timeJoined;

        public UserBuilder(String userName, String passWord) {
            this.userName = userName;   // this.userName =builder.userName; ???
            this.passWord = passWord;
        }

        // set all fields
        public UserBuilder userId() {
            UUID uuid = UUID.randomUUID();  // Universally Unique Identifier 全局唯一标识符,在一台机器上生成的数字，保证对在同一时空中的所有机器都是唯一的
            this.userId = uuid.toString();
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder timeJoined(Date timeJoined) {
            this.timeJoined = timeJoined;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
