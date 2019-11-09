package com.example.CMP354.bazaar;
import android.widget.Button;
import android.widget.EditText;

import com.example.CMP354.bazaar.Activities.EditProfileActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;


/**
 * Unit tests for the EditProfile logic.
 */
@RunWith(RobolectricTestRunner.class)
public class EditProfileActivtiyTest {


    private EditProfileActivity EditProfileAct;

    @Before
    public void setUp() throws Exception {
        EditProfileAct = Robolectric.setupActivity(EditProfileActivity.class);
    }

    @Test
    public void tearDown() throws Exception {

        EditProfileAct = null;
    }

    @Test
    //Test if object is created successfully
    public void testObjectCreation() {
        assertNotNull(EditProfileAct);
    }

    @Test//Test if TextViews are found and empty
    public void validateTextViews() {
        EditText fname = (EditText) EditProfileAct.findViewById(R.id.edit_FName);
        assertNotNull("TextView could not be found", fname);
        assertEquals("TextView contains incorrect text", "", fname.getText().toString());

        EditText lname = (EditText) EditProfileAct.findViewById(R.id.edit_LName);
        assertNotNull("TextView could not be found", lname);
        assertEquals("TextView contains incorrect text", "", lname.getText().toString());

        EditText phoneNum = (EditText) EditProfileAct.findViewById(R.id.edit_phoneNum);
        assertNotNull("TextView could not be found", phoneNum);
        assertEquals("TextView contains incorrect text", "", phoneNum.getText().toString());

        EditText Pass = (EditText) EditProfileAct.findViewById(R.id.edit_Password);
        assertNotNull("TextView could not be found", Pass);
        assertEquals("TextView contains incorrect text", "", Pass.getText().toString());

    }


@Test
    public void validateCancelButtonContent() {
    Button cancel = (Button) EditProfileAct.findViewById(R.id.cancelBtn);
    assertNotNull("Button could not be found", cancel);
    assertEquals("Button contains incorrect text",
            "Cancel", cancel.getText().toString());

}
   @Test
   public void validateSaveEditButtonContent() {

        Button saveEdit = (Button) EditProfileAct.findViewById(R.id.edit_Profile_button);
        assertNotNull("Button could not be found", saveEdit);
        assertEquals("Button contains incorrect text",
                "Save Edits",saveEdit.getText().toString());

    }

}