package model;

public class Mentor {
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String city;
    private int favouriteNumber;

    private Mentor() {}

    public static class MentorBuilder {
        private Mentor mentorToBuild;

        public MentorBuilder() {
            this.mentorToBuild = new Mentor();
        }

        public Mentor build() {
            Mentor buildMentor = this.mentorToBuild;
            this.mentorToBuild = new Mentor();
            return buildMentor;
        }

        public MentorBuilder setFirstName(String firstName) {
            if (firstName != null) {
                this.mentorToBuild.firstName = firstName;
            }
            return this;
        }

        public MentorBuilder setLastName(String lastName) {
            this.mentorToBuild.lastName = lastName;
            return this;
        }

        public MentorBuilder setNickName(String nickName) {
            if (nickName != null) {
                this.mentorToBuild.nickName = nickName;
            }
            return this;
        }

        public MentorBuilder setPhoneNumber(String phoneNumber) {
            this.mentorToBuild.phoneNumber = phoneNumber;
            return this;
        }

        public MentorBuilder setEmail(String email) {
            this.mentorToBuild.email = email;
            return this;
        }

        public MentorBuilder setCity(String city) {
            this.mentorToBuild.city = city;
            return this;
        }

        public MentorBuilder setFavouriteNumber(int favouriteNumber) {
            this.mentorToBuild.favouriteNumber = favouriteNumber;
            return this;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    @Override
    public String toString() {

        return "Mentor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", favouriteNumber=" + favouriteNumber +
                '}';
    }
}
