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
