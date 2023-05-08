package com.fiap.twinttler;

import androidx.test.core.app.ActivityScenario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddDebtActivityUnitTest {

    private ActivityScenario<AddDebtActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(AddDebtActivity.class);
    }

    @After
    public void tearDown() {
        scenario.close();
    }

    @Test
    public void testAddDebt() {
        String name = "Conta de Luz";
        String value = "100.0";

        onView(withId(R.id.edit_text_name)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.edit_text_value)).perform(typeText(value), closeSoftKeyboard());
        onView(withId(R.id.button_add)).perform(click());

        // Verifica se a mensagem de sucesso é exibida
        onView(withText("Conta adicionada com sucesso"))
                .inRoot(withDecorView(not(is(getActivity(scenario.get())))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testAddDebtError() {
        onView(withId(R.id.button_add)).perform(click());

        // Verifica se as mensagens de erro são exibidas
        onView(withId(R.id.edit_text_name)).check(matches(hasErrorText("Campo obrigatório")));
        onView(withId(R.id.edit_text_value)).check(matches(hasErrorText("Campo obrigatório")));
    }
}

