package com.hcodestudio.primenummultiplicationtable;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    static String[] spaceProbeHeaders= DisplayPrime(10);
   // static String[] spaceProbeHeaders= {"1","2","3","4","5","6","7","8","9","10"};

     TableView<String[]> tableView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.tableView);

         tableView.setHeaderBackgroundColor(Color.parseColor("#2ecc71"));
         tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, spaceProbeHeaders));
         tableView.setDataAdapter(new SimpleTableDataAdapter(this, returnTable(DisplayPrime(10))));
         tableView.canScrollHorizontally(10);
         tableView.setColumnCount(8);
    }

    public static Boolean isPrime(int num)
    {
        Boolean prime = true;
        for (int i = 2; i < num / 2; i++)
        {
            if (num % i == 0)
            {
                prime = false;
                break;
            }
        }
        return prime;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String[] DisplayPrime(int noOfPrime){

        List<String> primeList = new ArrayList<>();
        int countprime = 0;
        for (int numb = 2; countprime <  noOfPrime; numb++)
        {
            if (isPrime(numb))
            {
                primeList.add(String.valueOf(numb));
                countprime++;
            }
        }
        Log.d("MainActivity", "Prime Array = " + String.valueOf(primeList));

        String[] primeArray = primeList.stream().toArray(String[]::new);

        return primeArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String[][] returnTable (String[] primes)
    {
        final List<List<String>> table = new ArrayList<>();
        int index = 0;
        for (String p : primes)
        {
            table.add(new ArrayList<>());
            for (String j : primes)
            {
                table.get(index).add(String.valueOf(Integer.parseInt(p)*Integer.parseInt(j)));
            }
            index++;
        }

        String[][] tableArray = table
                .stream()
                .map((l) -> l.toArray(new String[table.size()]))
                .collect(Collectors.toList())
                .toArray(new String[table.size()][]);

        return tableArray;
    }
}

