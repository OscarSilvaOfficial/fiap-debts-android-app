package com.fiap.twinttler;

import android.content.Intent;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;

import com.fiap.twinttler.Adapters.DebtAdapter;
import com.fiap.twinttler.Entities.Debt;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DebtsActivityUnitTest {

    @Mock
    FirebaseFirestore db;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadDebts() {
        ActivityScenario<DebtsActivity> scenario = ActivityScenario.launch(DebtsActivity.class);
        scenario.onActivity(activity -> {
            List<Debt> debts = new ArrayList<>();
            debts.add(new Debt("1", "Debt 1", 10.0));
            debts.add(new Debt("2", "Debt 2", 20.0));

            when(db.collection(any(String.class)).get()).thenReturn(Mockito.mockTask(new QuerySnapshot() {
                @Override
                public List<DocumentSnapshot> getDocuments() {
                    List<DocumentSnapshot> documents = new ArrayList<>();
                    documents.add(Mockito.mock(DocumentSnapshot.class));
                    documents.add(Mockito.mock(DocumentSnapshot.class));
                    return documents;
                }
            }));

            activity.loadDebts();

            RecyclerView recyclerViewDebits = activity.findViewById(R.id.debtsRecyclerView);
            DebtAdapter adapter = (DebtAdapter) recyclerViewDebits.getAdapter();
            List<Debt> loadedDebts = adapter.getDebts();

            Assert.assertEquals(debts.size(), loadedDebts.size());
            Assert.assertEquals(debts.get(0).getTitle(), loadedDebts.get(0).getTitle());
            Assert.assertEquals(debts.get(0).getAmount(), loadedDebts.get(0).getAmount(), 0);
            Assert.assertEquals(debts.get(1).getTitle(), loadedDebts.get(1).getTitle());
            Assert.assertEquals(debts.get(1).getAmount(), loadedDebts.get(1).getAmount(), 0);
        });
    }

    @Test
    public void testAddDebitButton() {
        ActivityScenario<DebtsActivity> scenario = ActivityScenario.launch(DebtsActivity.class);
        scenario.onActivity(activity -> {
            RecyclerView recyclerViewDebits = activity.findViewById(R.id.debtsRecyclerView);
            int itemCountBefore = recyclerViewDebits.getAdapter().getItemCount();

            Button addDebitButton = activity.findViewById(R.id.button_add_debit);
            addDebitButton.performClick();

            Intent actualIntent = activity.getNextStartedActivity();
            Intent expectedIntent = new Intent(activity, AddDebtActivity.class);
            Assert.assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());

            int itemCountAfter = recyclerViewDebits.getAdapter().getItemCount();
            Assert.assertEquals(itemCountBefore, itemCountAfter);
        });
    }
}
