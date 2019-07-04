package dao;

import java.util.List;

public interface ApplicantDao {
    List<String[]> getApplicantCarol();
    List<String[]> getApplicantByIndicatedEmail();
    void insertApplicantMarkus();
    List<String[]> displayApplicantMarkus();
    void updatePhoneNumberForJemimaForeman();
    List<String[]> displayApplicantJemima();

}
