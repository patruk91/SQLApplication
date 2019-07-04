package dao;

import java.util.List;

public interface ApplicantDao {
    List<String[]> getApplicantCarol();
    List<String[]> getApplicantByIndicatedEmail();

}
