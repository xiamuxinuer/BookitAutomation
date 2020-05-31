package com.bookit.units;

import com.bookit.utilities.APIUtilities;
import org.junit.Assert;
import org.junit.Test;

public class APIUtilitiesUnitTest {

  @Test
  public void getTokenTest  (){
      String token = APIUtilities.getToken();
      String tokenForStudent = APIUtilities.getToken("student");
      String tokenForTeacher = APIUtilities.getToken("teacher");

      Assert.assertNotNull(token);
      Assert.assertNotNull(tokenForStudent);
      Assert.assertNotNull(tokenForTeacher);
  }

    @Test
    public void testIfUserExists(){
        int actual = APIUtilities.getUserID("thereisnoemaillikethis@email.com", "123123");
        Assert.assertEquals(-1, actual);
    }









}
