package loadingdata.ashish.sqlitetutorial;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaseHelper mydb;
    EditText name,surname,marks,id;
    Button insertData,updateData;
    Button ViewData,DeleteRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DataBaseHelper(this);
        id=(EditText)findViewById(R.id.userid);
        name=(EditText)findViewById(R.id.username);
        surname=(EditText)findViewById(R.id.surname);
        marks=(EditText)findViewById(R.id.marks);
        insertData=(Button)findViewById(R.id.button);
        DeleteRecord=(Button)findViewById(R.id.delete);
        updateData=(Button)findViewById(R.id.update);
        ViewData=(Button)findViewById(R.id.viewdata);
        ViewData.setOnClickListener(this);
        insertData.setOnClickListener(this);
        DeleteRecord.setOnClickListener(this);
        updateData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button:
                    Adddata();
                break;
            case R.id.viewdata:
                ShowRecords();
                break;
            case R.id.update:
                UpdateRecords();
                break;
            case R.id.delete:
                DeleteRecords();
                break;
        };
    }

    private void DeleteRecords() {
        Integer deletedRecords=mydb.deleteRecords(id.getText().toString());
        if(deletedRecords>0)
        {
            ShowMessage("Deleted","Records Deleted");
        }
        else
        {
            ShowMessage("Error","Not Deleted");
        }
    }

    private void UpdateRecords() {
   boolean isUpdated=mydb.UpdateData(id.getText().toString(),name.getText().toString(),
           surname.getText().toString(), Integer.parseInt(marks.getText().toString()));
        if(isUpdated==true)
        {
            Toast.makeText(this,"Data is Updated",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Data is not Updated",Toast.LENGTH_SHORT).show();
        }

    }


    private void ShowRecords() {

        Cursor res = mydb.getRecords();
        if (res.getCount() == 0) {
            ShowMessage("Error","No Records Founds");

        } else {
            StringBuilder stringBuilder = new StringBuilder();
            while (res.moveToNext()) {

                stringBuilder.append(res.getString(0) + "\n");
                stringBuilder.append(res.getString(1) + "\n");
                stringBuilder.append(res.getString(2) + "\n");
                stringBuilder.append(res.getString(3) + "\n\n");
            }
            ShowMessage("Records",stringBuilder.toString());

        }
    }
    public void ShowMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.show();
    }

    private void Adddata() {
       boolean is_Inserted= mydb.insertData(name.getText().toString(),surname.getText().toString(), Integer.parseInt(marks.getText().toString()));
        if(is_Inserted==true)
        {
            Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        }
    }
}
