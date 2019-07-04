package dao;

import model.Mentor;

import java.util.List;

public interface MentorDao {
    List<String[]> getMentorsFirstAndLastName();
    List<String[]> getMentorsNickNames();
    List<String[]> getAllMentors();
    void addNewMentor(Mentor newMentor);
}
