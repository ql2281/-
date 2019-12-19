public class User {
    private String userName;
    private String passWord;
    private String phone;
    private String email;

    //constructor 是 private，不能被直接new出来
    private User(UserBuilder builder) {
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    @Override
    public String toString() {
        return "user name : " + this.userName+ "   password: " + passWord;

    }

    public static class UserBuilder {
        private String userName;
        private String passWord;
        private String phone;
        private String email;

        //constructor
        public UserBuilder(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        //setter
        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        //getter
        public User build() {
            return new User(this);
        }
    }
}

class BuilderPattern {
    public static void main(String[] args) {
        User user = new User.UserBuilder("David", "12345")
                .setPhone("12345678")
                .setEmail("dave@gmail.com")
                .build();
        System.out.println(user); //如何打印出user下面的fields
    }
}
