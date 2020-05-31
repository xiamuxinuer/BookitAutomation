package com.bookit.utilities;


import io.restassured.response.*;

import static io.restassured.RestAssured.*;

public class APIUtilities {



    static {
        baseURI = Environment.BASE_URI;
    }


    /**
     * This method is used to retrieve token from the server.
     * Token bust be attached to the header of every API call
     * @return token
     */
    public static String getToken() {
        Response response = given().
                queryParam("email", Environment.LEADER_USERNAME).
                queryParam("password", Environment.LEADER_PASSWORD).
                when().
                get("/sign");
        response.then().log().ifError();//if request failed, print response information
        String token = response.jsonPath().getString("accessToken");
        System.out.println("TOKEN :: " + token);
        return token;
    }

    /**
     * This method is used to retrieve authorization token from the server for specific role.
     * Token bust be attached to the header of every API call
     * @return token
     */
    public static String getToken(String role) {
        String email = null;
        String password = null;
        if(role.toLowerCase().contains("teacher")){
            email = Environment.TEACHER_USERNAME;
            password = Environment.TEACHER_PASSWORD;
        }else if (role.toLowerCase().contains("lead")){
            email = Environment.LEADER_USERNAME;
            password = Environment.LEADER_PASSWORD;
        }else {
            email = Environment.MEMBER_USERNAME;
            password = Environment.MEMBER_PASSWORD;
        }

        Response response = given().
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign");
        response.then().log().ifError();//if request failed, print response information
        String token = response.jsonPath().getString("accessToken");
       // System.out.println("TOKEN :: " + token);
        return token;
    }


    public static String getToken(String email, String password) {
        Response response = given().
                queryParam("email",email).
                queryParam("password",password).
                when().
                get("/sign");
        response.then().log().ifError();//if request failed, print response information
        String token = response.jsonPath().getString("accessToken");
        System.out.println("TOKEN :: " + token);
        return token;
    }

    // get me request verify user based on token , no param , just token.
    public static int getUserID(String email, String password) {

    try {
        String token = getToken(email, password);
        Response response = given().auth().oauth2(token).when().get(EndPoints.Get_Me);
        response.then().log().ifError();//print response details in case of error
        response.then().statusCode(200);//ensure that it returns 200 status code
        return response.jsonPath().getInt("id");
    }catch (Exception e){
        System.out.println("USER DOESN'T EXISTS!");
        System.out.println(e.getMessage());
    }
    return -1;
}

    /**
     * This method deletes user based on id
     *
     * @param id of the user to delete
     * @return response object
     */
    public static Response deleteUserByID(int id) {
        String token = getToken("teacher");
        Response response = given().auth().oauth2(token).when().delete(EndPoints.DELETE_STUDENT, id);
        response.then().log().ifError();
        System.out.printf("User with id %s was deleted!", id);
        return response;
    }










}
