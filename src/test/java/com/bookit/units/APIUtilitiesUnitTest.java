package com.bookit.units;
import static io.restassured.RestAssured.*;

import com.bookit.pojos.Room;
import com.bookit.pojos.Student;
import com.bookit.utilities.APIUtilities;
import com.bookit.utilities.EndPoints;
import com.bookit.utilities.Environment;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.bookit.utilities.APIUtilities.*;
import static io.restassured.RestAssured.*;

public class APIUtilitiesUnitTest {
    @Before
    public  void setUp(){
        baseURI= Environment.BASE_URI;
    }

  @Test
  public void getTokenTest  (){
      String token = getToken();
      String tokenForStudent = getToken("student");
      String tokenForTeacher = getToken("teacher");

      Assert.assertNotNull(token);
      Assert.assertNotNull(tokenForStudent);
      Assert.assertNotNull(tokenForTeacher);
  }

    @Test
    public void testIfUserExists(){
        int actual = getUserID("thereisnoemaillikethis@email.com", "123123");
        System.out.println(actual);
        Assert.assertEquals(-1, actual);
    }


@Test
    public void test(){
    Response response=  given().accept(ContentType.JSON).
                        auth().oauth2(APIUtilities.getToken()).
                        when().get("/api/rooms").prettyPeek();
             response.then().assertThat().statusCode(200);
    //System.out.println(response.getHeaders());
    List<Map<String,Object>> countOfObject=response.jsonPath().getList("");
   // System.out.println(countOfObject);
              List<Room> allRooms=response.jsonPath().getList("",Room.class);
          System.out.println(allRooms.get(0).getName());
}

@Test
    public void test2(){
       // baseURI=Environment.BASE_URI;
    Response response = given().accept(ContentType.JSON).
            auth().oauth2(getToken()).
            when().get(EndPoints.Get_Me).prettyPeek();

    Student student= response.as(Student.class);
    System.out.println("name:" + student.getFirstName()+  ", student id :" +student.getId());
}




}
