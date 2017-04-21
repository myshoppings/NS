package com.example.sultan.ShoppingList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sultan.myshoppings.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  
    //==============- declaration -=================

    boolean TotalShopping = true;

    ListView listView;
    EditText editText;
    Button addBtn, editBtn, deleteBtn;
    String selectedPosition;

    ArrayList<String> Total_Groceries = new ArrayList<>();
    ArrayList<String> Favorite_Groceries = new ArrayList<>();

    ArrayAdapter<String> Total_Adapter;
    ArrayAdapter<String> Favorite_Adapter;

   //==========- declaring thr DB HANDLER -=========
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      
             super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this, null, null, 1);

        declareMain();
    }
  
    //==============- declareMain -=================
    private void declareMain()
    {
        //getting listView object from XML
        listView = (ListView) findViewById(R.id.listView);

        //getting button from the XML
        addBtn = (Button) findViewById(R.id.addBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        editBtn = (Button) findViewById(R.id.editBtn);

        //getting edit text from the XML
        editText = (EditText) findViewById(R.id.editText);

        // adding values to Array to show in ListView
        Total_Groceries = dbHandler.databaseToString();
      
        //defining a new adapter
        Total_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Total_Groceries);
        Favorite_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Favorite_Groceries);

        //assign adapter to list view
        listView.setAdapter(Total_Adapter);
        registerForContextMenu(listView);
      
      //list view items click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(listView.getAdapter().getItem(position).toString());
                selectedPosition = listView.getAdapter().getItem(position).toString();

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "You have selected : " + itemValue, Toast.LENGTH_LONG).show();
            }
        });
      
      //==============- Add Button Listener -=================

        addBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                add();
            }
        });//addListener

      //==============- Edit Button -=================

        editBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // call created method
                edit();
            }
        });//editListener

      
        //==============- delete Button -=================
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // call created method
                delete();
            }
        });

       }//end declare main method
  
  
     //==============- add Function -=================
    private void add()
    {
        Grocery grocery = new Grocery(editText.getText().toString());

        String name = editText.getText().toString();
        if(!name.isEmpty() && name.length() >=0)
        {
            ((ArrayAdapter)listView.getAdapter()).add(name);
            ((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();

            editText.setText("");

            Toast.makeText(getApplicationContext(), "Item Added: " + name, Toast.LENGTH_SHORT).show();
            dbHandler.addGrocery(grocery);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Nothing to add", Toast.LENGTH_SHORT).show();
        }
    }// end add function
  
  
     //==============- Edit Function -=================
    private void edit()
    {
        String name = editText.getText().toString();
        int pos = listView.getCheckedItemPosition();

        if(!name.isEmpty() && name.length() >=0)
        {
            if(TotalShopping)
                ((ArrayAdapter)listView.getAdapter()).remove(Total_Groceries.get(pos));
            else
                ((ArrayAdapter)listView.getAdapter()).remove(Favorite_Groceries.get(pos));

            ((ArrayAdapter)listView.getAdapter()).insert(name, pos);
            ((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();

            editText.setText("");

            Toast.makeText(getApplicationContext(), "Item Updated: " + name, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Nothing to update", Toast.LENGTH_SHORT).show();
        }
    }// end of edit function
  
  
      //==============- Delete Function -=================
    private void delete()
    {
        Grocery grocery = new Grocery(editText.getText().toString());
        //grocery.set_groceryname(grocery.get_groceryname().replace("\n", ""));

        int pos = listView.getCheckedItemPosition();

        if(pos > -1 && listView.getCount() > 0)
        {
            if (TotalShopping)
                ((ArrayAdapter)listView.getAdapter()).remove(Total_Groceries.get(pos));
            else
                ((ArrayAdapter)listView.getAdapter()).remove(Favorite_Groceries.get(pos));

            Toast.makeText(getApplicationContext(), "Item Deleted.", Toast.LENGTH_SHORT).show();
            dbHandler.deleteGrocery(grocery);

            // Toast.makeText(getApplicationContext(), "are you here." + grocery.get_groceryname(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Nothing to Delete", Toast.LENGTH_SHORT).show();
        }
    }// end of delete function
  
   //==============================================================================================
    //=====================- MENU Functions-===============================
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_main_menu, menu);
        return true;
    }

