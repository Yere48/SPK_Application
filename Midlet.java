/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spk_application;

import javax.microedition.midlet.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 * @author USER
 */
public class Midlet extends MIDlet implements CommandListener{
    private Display display;
    private Form form1,form2,form3,form4;
    private RecordStore rs,rs1;
    private RecordEnumeration re;
    private Alert alert;
    private TextField logUser,logPass,regUser,regPass,regName,regBirth,regEmail,name;
    private StringItem nama,tgl,email,jns;
    private ChoiceGroup cg,k1,k2,k3,k4;
    private List mainList;
    
    private Command cmdExit=new Command("Exit", Command.EXIT, 1);
    private Command cmdLogin=new Command("Login", Command.OK, 1);
    private Command cmdLogout=new Command("Logout", Command.OK, 1);
    private Command cmdRegister=new Command("Register", Command.OK, 1);
    private Command cmdOk=new Command("OK", Command.OK, 1);
    private Command cmdBack=new Command("Back", Command.BACK, 1);
    private Command cmdSelect=new Command("Select", Command.OK, 1);
    private Command cmdYes=new Command("OK", Command.OK, 1);
    
    
    public Midlet(){
        display = Display.getDisplay(this);
        alert=new Alert("INFORMATION");
        
        rs=null;
        try {
            rs=RecordStore.openRecordStore("Account", true);
        } catch (RecordStoreException e) {
            alert.setString("Record Store Can Not Open");
            alert.setType(AlertType.ERROR);
            display.setCurrent(alert,null);
            System.exit(1);
        }
        
        rs1=null;
        try {
            rs1=RecordStore.openRecordStore("Data", true);
        } catch (RecordStoreException e) {
            alert.setString("Record Store Can Not Open");
            alert.setType(AlertType.ERROR);
            display.setCurrent(alert,null);
            System.exit(1);
        }
    }
    
    public void startApp() {
        form1 = new Form("Login");
        logUser = new TextField("Username ","",20,TextField.ANY);
        logPass = new TextField("Password ","",20,TextField.PASSWORD);
        cmdExit = new Command("Exit",Command.EXIT,1);
        cmdLogin = new Command("Login",Command.OK,1);
        cmdRegister = new Command("Register",Command.OK,1);
        form1.append(logUser);
        form1.append(logPass);
        form1.addCommand(cmdExit);
        form1.addCommand(cmdLogin);
        form1.addCommand(cmdRegister);
        form1.setCommandListener(this);
        display.setCurrent(form1);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmdExit){
            destroyApp(false);
            notifyDestroyed();
        }
        else if(c==cmdBack)
        {
            if(d==form2){
                display.setCurrent(form1);
                logUser.setString("");
                logPass.setString("");
            }
            else if(d==form4){
                display.setCurrent(mainList);
            }
            else if(d==form3){
                display.setCurrent(mainList);
            }
        }
        else if(c==cmdLogout)
        {
            display.setCurrent(form1);
            logUser.setString("");
            logPass.setString("");
        }
        else if(c==cmdRegister)
        {
            Form2();
        }
        else if(c==cmdYes)
        {
            if((name != null))
            {
                //rendah
                if(k1.isSelected(0)&&k2.isSelected(0)&&k3.isSelected(0)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Tidak Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Tidak Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(0)&&k2.isSelected(0)&&k3.isSelected(0)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Tidak Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Tidak Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(0)&&k2.isSelected(0)&&k3.isSelected(1)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Tidak Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Tidak Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(0)&&k2.isSelected(1)&&k3.isSelected(0)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Tidak Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Tidak Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(0)&&k3.isSelected(0)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Tidak Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Tidak Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                //cukup
                else if(k1.isSelected(0)&&k2.isSelected(0)&&k3.isSelected(1)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(0)&&k2.isSelected(1)&&k3.isSelected(1)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(0)&&k2.isSelected(1)&&k3.isSelected(0)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(0)&&k3.isSelected(0)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(0)&&k3.isSelected(1)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(1)&&k3.isSelected(0)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Cukup Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Cukup Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                //tinggi
                else if(k1.isSelected(0)&&k2.isSelected(1)&&k3.isSelected(1)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Sangat Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Sangat Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(0)&&k3.isSelected(1)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Sangat Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Sangat Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(1)&&k3.isSelected(1)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Sangat Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Sangat Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(1)&&k3.isSelected(0)&&k4.isSelected(1))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Sangat Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Sangat Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
                else if(k1.isSelected(1)&&k2.isSelected(1)&&k3.isSelected(1)&&k4.isSelected(0))
                {
                    alert.setType(AlertType.INFO);
                    alert.setString("Name "+name.getString()+" Sangat Mampu"+"\nData Has Been Saved");
                    Input(name.getString(), "Sangat Mampu");
                    name.setString("");
                    display.setCurrent(alert, form3);
                }
            }
            else
            {
                alert.setType(AlertType.INFO);
                alert.setString("This Form Can Not Blank");
                display.setCurrent(alert, form3); 
            }
        }
        
        else if(c==cmdOk)
        {
                if(cg.isSelected(0)){
                    if((regUser.getString().equals(""))&&(regPass.getString().equals(""))&&(regName.getString().equals(""))&&(regBirth.getString().equals(""))&&(regEmail.getString().equals(""))){ 
                        alert.setType(AlertType.INFO);
                        alert.setString("This Form Can Not Blank");
                        display.setCurrent(alert, form2);
                        regUser.setString("");
                        regPass.setString("");
                        regName.setString("");
                        regBirth.setString("");
                        regEmail.setString("");
                        logUser.setString("");
                        logPass.setString("");
                    }
                    else{
                        alert.setType(AlertType.INFO);
                        Register(regUser.getString(),regPass.getString(),regName.getString(),regBirth.getString(),regEmail.getString(),cg.getString(0));
                        alert.setString("Data Has Been Saved");
                        display.setCurrent(alert, form1);
                        logUser.setString("");
                        logPass.setString("");
                    }
                }
                else if(cg.isSelected(1)){
                    if((regUser.getString().equals(""))&&(regPass.getString().equals(""))&&(regName.getString().equals(""))&&(regBirth.getString().equals(""))&&(regEmail.getString().equals("")))
                    {
                        alert.setType(AlertType.INFO);
                        alert.setString("This Form Can Not Blank");
                        display.setCurrent(alert, form2);
                        regUser.setString("");
                        regPass.setString("");
                        regName.setString("");
                        regBirth.setString("");
                        regEmail.setString("");
                        logUser.setString("");
                        logPass.setString("");
                    }
                    else{
                        alert.setType(AlertType.INFO);
                        Register(regUser.getString(),regPass.getString(),regName.getString(),regBirth.getString(),regEmail.getString(),cg.getString(1));
                        alert.setString("Data Has Been Saved");
                        display.setCurrent(alert, form1);
                        logUser.setString("");
                        logPass.setString("");
                    }
                }
            }
        else if(c==cmdLogin)
        {
            byte[] temp=null;
            try {
                re=rs.enumerateRecords(null, null, false);
                while(re.hasNextElement()){
                    int i=re.nextRecordId();
                    temp=rs.getRecord(i);
                    ByteArrayInputStream bais=new ByteArrayInputStream(temp);
                    DataInputStream dis=new DataInputStream(bais);
                    try {
                        String aa=dis.readUTF();
                        String bb=dis.readUTF();
                        if(aa.equalsIgnoreCase(logUser.getString())&& bb.equalsIgnoreCase(logPass.getString())){
                            alert.setType(AlertType.INFO);
                            mainList = new List("Main Menu Welcome, "+aa.toString(), List.IMPLICIT);
                            mainList.append("SPK Data Collection", null);
                            mainList.append("View Data", null);
                            mainList.addCommand(cmdLogout);
                            mainList.addCommand(cmdSelect);
                            alert.setString("Login Successfully");
                            mainList.setCommandListener(this);
                            display.setCurrent(alert,mainList);
                         }
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InvalidRecordIDException e) {
                e.printStackTrace();
            } catch (RecordStoreNotOpenException e){
                e.printStackTrace();
            } catch (RecordStoreException e){
                e.printStackTrace();
            }
            
        }
        else if(c==cmdSelect){
            if(mainList.getSelectedIndex()==0){
                form3 =new Form("Pendataan SPK");
                name = new TextField("Name ","",20,TextField.ANY);
                k1=new ChoiceGroup("Jenis Bangunan", ChoiceGroup.EXCLUSIVE);
                k2=new ChoiceGroup("Jumlah Kendaraan", ChoiceGroup.EXCLUSIVE);
                k3=new ChoiceGroup("Penghasilan", ChoiceGroup.EXCLUSIVE);
                k4=new ChoiceGroup("Jumlah Tanah", ChoiceGroup.EXCLUSIVE);
                form3.append(name);
                k1.append("Kayu", null);
                k1.append("Batako", null);
                k2.append("Tidak Punya", null);
                k2.append(">= 1", null);
                k3.append("<= 1 juta", null);
                k3.append(">= 1 juta", null);
                k4.append("1", null);
                k4.append("> 1", null);
                form3.addCommand(cmdBack);
                form3.addCommand(cmdYes);
                form3.append(k1);
                form3.append(k2);
                form3.append(k3);
                form3.append(k4);
                form3.setCommandListener(this);
                display.setCurrent(form3);
            }
            else{
                byte[] temp=null;
                form4 =new Form("Data Penduduk");
                try {
                    re=rs1.enumerateRecords(null, null, false);
                    while(re.hasNextElement()){
                        int i=re.nextRecordId();
                        temp=rs1.getRecord(i);
                        ByteArrayInputStream bais=new ByteArrayInputStream(temp);
                        DataInputStream dis=new DataInputStream(bais);
                        try {
                            String a1=dis.readUTF();
                            String b1=dis.readUTF();
                            form4.append("Name  : "+a1+" \nStatus : "+b1+" \n----------------------------------------------\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                form4.addCommand(cmdBack);
                form4.setCommandListener(this);
                display.setCurrent(form4);    
                
                } catch (InvalidRecordIDException e) {
                    e.printStackTrace();
                } catch (RecordStoreNotOpenException e){
                    e.printStackTrace();
                } catch (RecordStoreException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void Form2(){
        form2 =new Form("Register");
        regUser=new TextField("Username", "", 20, TextField.ANY);
        regPass=new TextField("Password", "", 20, TextField.ANY);
        regName=new TextField("Name", "", 30, TextField.ANY);
        regBirth=new TextField("Birthday", "", 30, TextField.ANY);
        regEmail=new TextField("Email", "", 50, TextField.ANY);
        cg=new ChoiceGroup("Sex", ChoiceGroup.POPUP);
        cg.append("Male", null);
        cg.append("Female", null);
        form2.append(regUser);
        form2.append(regPass);
        form2.append(regName);
        form2.append(regBirth);
        form2.append(regEmail);
        form2.append(cg);
        form2.addCommand(cmdOk);
        form2.addCommand(cmdBack);
        form2.setCommandListener(this);
        display.setCurrent(form2);
    }
    
    public void Register(String aa,String bb,String cc,String dd,String ee,String ff){
        byte[] temp=null;
        try {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            DataOutputStream dos=new DataOutputStream(baos);
            dos.writeUTF(aa);
            dos.writeUTF(bb);
            dos.writeUTF(cc);
            dos.writeUTF(dd);
            dos.writeUTF(ee);
            dos.writeUTF(ff);
            temp=baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rs.addRecord(temp, 0, temp.length);
        } catch (RecordStoreNotOpenException e) {
            e.printStackTrace();
        } catch (RecordStoreException e){
            e.printStackTrace();
        }
    }
    
    public void Input(String a1,String b1){
        byte[] temp=null;
        try {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            DataOutputStream dos=new DataOutputStream(baos);
            dos.writeUTF(a1);
            dos.writeUTF(b1);
            temp=baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rs1.addRecord(temp, 0, temp.length);
        } catch (RecordStoreNotOpenException e) {
            e.printStackTrace();
        } catch (RecordStoreException e){
            e.printStackTrace();
        }
    }
}
