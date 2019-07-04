package dao;

import model.Applicant;

import java.util.List;

public interface ApplicantDao {
    List<String[]> getApplicantCarol();
    List<String[]> getApplicantByIndicatedEmail();
    void insertApplicantMarkus();
    List<String[]> displayApplicantMarkus();
    void updatePhoneNumberForJemimaForeman();
    List<String[]> displayApplicantJemima();
    void deleteApplicantFromDomainMaurisuNet();
    List<String[]> getAllApplicants();
    void addNewApplicant(Applicant applicant);
}
