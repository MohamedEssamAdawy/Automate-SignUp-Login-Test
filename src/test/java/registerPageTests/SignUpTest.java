package registerPageTests;

import base.AllureListenerReporter;
import base.BaseTest;
import base.TestNGListenerReporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonReader;

import static org.testng.Assert.*;

// Listener to use If TestCase Failed (Report with screenshot) or Succeed (Report with text)
@Listeners({TestNGListenerReporter.class,AllureListenerReporter.class})

public class SignUpTest extends BaseTest {

    @Test(dataProvider = "SignUpValidData",description = "Test full sign up scenario with 2 valid cases")
    public void testSignUpWithValidData(JsonReader.User user){
        signUp(user);
    }

    @Test(dataProvider = "SignUpInvalidData",description = "Test full sign up scenario with all invalid cases")
    public void testSignUpWithInvalidData(JsonReader.User user){
        signUp(user);
    }

    public void signUp(JsonReader.User user) {
        if (user == null){
            fail("Corrupted Inputs");
        }
        // Check and Set the Sign up candidates conditions.
        assertTrue(registerPage.setFirstNameField(user.firstName), "First letter in first name is small");
        assertTrue( registerPage.setLastNameField(user.lastName) && !user.firstName.equals(user.lastName),
                "First letter in last name is small OR lastName equals firstName");
        assertTrue(registerPage.setMobileNumberField(user.mobile), "Mobile number must be Digits and + sign only");
        assertTrue(registerPage.setEmailField(user.email), "Email must contain @");
        assertTrue(registerPage.setPasswordField(user.password), "Wrong password format, password must be less than 8 characters and contain at least one small letter and one capital letter.");
        assertTrue( registerPage.setConfirmPasswordField(user.confirmed) && user.password.equals(user.confirmed),
                "Confirmed password not equal password");

        // Click Get it Button in the cookies bar, because it make sign up button unreachable.
        registerPage.clickCookiesButtonIfExist();

        // Click Sign up button.
        var accountPage = registerPage.clickSignUpButton();

        // Check if the Sign- up completed correctly.
        assertEquals(accountPage.getGreetingMsg(),"Hi, "+user.firstName +" "+user.lastName, "SignUp not complete successfully");

        // Log out - redirect to login page.
        var loginPage = accountPage.selectLogOutFromDropDown();

        // Click Get it Button in the cookies bar because it make sign up button unreachable.
        loginPage.clickCookiesButtonIfExist();

        // Set Login Data
        loginPage.setEmailField(user.email);
        loginPage.setPasswordField(user.password);

        // Click Login button
        accountPage = loginPage.clickLoginButton();

        // Check if the Login completed correctly.
        assertEquals(accountPage.getGreetingMsg(),"Hi, "+user.firstName +" "+user.lastName, "Login not complete successfully");
        accountPage.selectLogOutFromDropDown();
    }

    @DataProvider(name = "SignUpValidData")
    private Object[] signUpValidData() {
        JsonReader jsonFile = new JsonReader("resources/validInputData.json");
        return jsonFile.usersData();
    }

    @DataProvider(name = "SignUpInvalidData")
    private Object[] signUpInvalidData() {
        JsonReader jsonFile = new JsonReader("resources/invalidInputData.json");
        return jsonFile.usersData();
    }
}
