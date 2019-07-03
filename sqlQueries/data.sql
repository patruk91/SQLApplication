COPY mentors(first_name, last_name, nick_name, phone_number, email, city, favourite_number)
    FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/mentors.csv'
    NULL AS 'NULL' DELIMITER ',' QUOTE '''' CSV HEADER;

COPY applicants(first_name, last_name, phone_number, email, application_code)
    FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/applicants.csv'
    DELIMITER ',' QUOTE '''' CSV HEADER;

