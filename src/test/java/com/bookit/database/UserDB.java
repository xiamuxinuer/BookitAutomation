package com.bookit.database;

import com.bookit.utilities.DBUtility;

public class UserDB {

    /**
     * If count equals to 1 - users exist
     * @param email of user to look up
     * @return true, if user exist
     */
    public boolean checkIfUserExistInDB(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = '" + email + "'";
        long countOfUsers = (Long) DBUtility.getCellValue(query);
        return countOfUsers == 1;
    }
}











