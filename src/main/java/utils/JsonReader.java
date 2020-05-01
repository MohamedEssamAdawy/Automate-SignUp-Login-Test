package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    private JSONParser jsonParser;
    private JSONArray userList;

    public JsonReader(String filePath) {
        jsonParser= new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            userList = new JSONArray();
            userList.add(obj);
//            System.out.println(userList); // for Debugging purpose
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            System.out.println("The file is not correctly formatted");
            e.printStackTrace();
        }
    }

    public User[] usersData(){
        userList = (JSONArray)userList.get(0);
        User[] usersInputs = new User[userList.size()];
        for (int i = 0; i<usersInputs.length;i++){
            usersInputs[i] = parseUserObject((JSONObject) userList.get(i));
        }
        return usersInputs;
    }

    private User parseUserObject(JSONObject user)
    {
        //Get user object within list
        JSONObject userObject = (JSONObject) user.get("user");
        //Get user first name
        String firstName = (String) userObject.get("firstName");

        //Get user last name
        String lastName = (String) userObject.get("lastName");

        //Get user mobile
        String mobile = (String) userObject.get("mobile");

       //Get user email
        String email = (String) userObject.get("email");

        //Get user password
        String password = (String) userObject.get("password");

        //Get user confirmedPassword
        String confirmedPassword = (String) userObject.get("confirmedPassword");
        return new User(firstName,lastName,mobile,email,password,confirmedPassword);
    }

    public class User{
        public String firstName;
        public String lastName;
        public String mobile;
        public String email;
        public String password;
        public String confirmed;

        public User(String firstName, String lastName,String mobile,String email,String password,String confirmed) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobile = mobile;
            this.email = email;
            this.password = password;
            this.confirmed = confirmed;
        }
    }

}

