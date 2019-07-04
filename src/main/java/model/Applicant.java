package model;

public class Applicant {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private long applicationCode;

    private Applicant() {}

    public static class ApplicantBuilder {
        private Applicant applicantToBuild;

        public ApplicantBuilder() {
            this.applicantToBuild = new Applicant();
        }

        public Applicant build() {
            Applicant buildApplicant = this.applicantToBuild;
            this.applicantToBuild = new Applicant();
            return buildApplicant;
        }

        public ApplicantBuilder setFirstName(String firstName) {
            if (firstName != null) {
                this.applicantToBuild.firstName = firstName;
            }
            return this;
        }

        public ApplicantBuilder setLastName(String lastName) {
            this.applicantToBuild.lastName = lastName;
            return this;
        }

        public ApplicantBuilder setPhoneNumber(String phoneNumber) {
            this.applicantToBuild.phoneNumber = phoneNumber;
            return this;
        }

        public ApplicantBuilder setEmail(String email) {
            this.applicantToBuild.email = email;
            return this;
        }

        public ApplicantBuilder setFavouriteNumber(int applicationCode) {
            this.applicantToBuild.applicationCode = applicationCode;
            return this;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public long getApplicationCode() {
        return applicationCode;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", applicationCode=" + applicationCode +
                '}';
    }
}
