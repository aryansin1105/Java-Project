import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
public class App implements ActionListener{
    JFrame frame;
    JLabel title,nameLabel,addressLabel,contactLabel,emailLabel,dobLabel,gitLabel,linkLabel,skillLabel,eduLabel,hscLabel,sscLabel,gradLabel,perLabel,proLabel,projectLabel,proName,proDurLabel,proDescLabel,proTecLabel,cocoLabel;
    JTextField nameTxt,addressTxt,contactTxt,emailTxt,dobTxt,gitTxt,linkTxt,hscSchool,hscGrade,sscSchool,sscGrade,gradUni,gradGrade,gradStream,proNameTxt,proDurTxt,proDescTxt,proTecTxt,coco1,coco2,coco3;
    JCheckBox ch1,ch2,ch3,ch4,ch5,ch6;
    JPanel panel,panel1,panel2;
    // JButton createBtn,searchBtn;
    JMenuBar menubar;
    JMenu menu1,menu2,menu3;
    JMenuItem save,search,edit,exit,about,delete;
    
    App(){
        createFields();
        setField();
        addFields();
        frame.setVisible(true);
        save.addActionListener(this);
        search.addActionListener(this);
        edit.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        delete.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == save){
            String name = nameTxt.getText();
            String address = addressTxt.getText();
            String contact = contactTxt.getText();
            String email = emailTxt.getText();
            String dob = dobTxt.getText();
            String git = gitTxt.getText();
            String link = linkTxt.getText();
            String hsGrade = hscGrade.getText();
            String hsSchool = hscSchool.getText();
            String ssGrade = sscGrade.getText();
            String ssSchool = sscSchool.getText();
            String uni = gradUni.getText();
            String stream = gradStream.getText();
            String grade = gradGrade.getText();
            String project = proNameTxt.getText();
            String duration = proDurTxt.getText();
            String description = proDescTxt.getText();
            String technology = proTecTxt.getText();
            String activity1 = coco1.getText();
            String activity2 = coco2.getText();
            String activity3 = coco3.getText();
            String skill = "";

            if(ch1.isSelected()){
                skill += "C++ ";
            }
            if(ch2.isSelected()){
                skill += "Java ";
            }
            if(ch3.isSelected()){
                skill += "Python ";
            }
            if(ch4.isSelected()){
                skill += "Js ";
            }
            if(ch5.isSelected()){
                skill += "C# ";
            }
            if(ch6.isSelected()){
                skill += "R ";
            }
            
            String format = "\n"+
            ""+name+"\n"+
            ""+address+"\n"+
            "Contact No : "+contact+"\n"+
            "Dob : "+dob+"\n"+
            "Email Id : "+email+"\n"+
            "Github : "+git+"\n"+
            "Linked : "+link+"\n"+
            "\n"+
            "                            Career Objective\n"+
            "\n"+
            "Insightful computer science student who excels at Python and Java. Seeking an internship in software engineering.\n"+
            "May marks the end of my junior year. Maintained a 4.0-grade point average in Java and Python fundamentals.\n"+
            "Won the Coderbyte programming competition in March 2020 and the TopCoder Java coding competition in February 2020.\n"+
            "\n"+
            "                            Technical Skill\n"+
            "\n"+
            "Operating System : Window, Linux\n"+
            "Database : MySQL\n"+
            "Language : "+skill+"\n"+
            "Web Technologies : HTML,CSS,JS,PHP\n"+
            "\n"+
            "                            Academic Qualification\n"+
            "\n"+
            ""+stream+" From "+uni+" with "+grade+"\n"+
            "HSC from "+hsSchool+ " with "+hsGrade+"\n"+
            "SSC from "+ssSchool+ " with "+ssGrade+"\n"+
            "\n"+
            "                            Academic Project\n"+
        
            ""+project+"\n"+
            "\n"+
            "Project Duration : "+duration+"\n"+
            "Project Description : "+description+"\n"+
            "Technologies : "+technology+"\n"+
            "\n"+
            "                            Co-curricular Activities\n"+
            "\n"+
            ""+activity1+"\n"+
            ""+activity2+"\n"+
            ""+activity3+"\n"+
            "\n"+           
            "";

            String filename = JOptionPane.showInputDialog("File Name");
            try{
                FileWriter fileWriter = new FileWriter(filename+".doc");
                fileWriter.write(format);
                JOptionPane.showMessageDialog(frame, "Successfully Created Resume");
                fileWriter.close();
            }
            catch(IOException f){
                JOptionPane.showMessageDialog(frame, "Unable to Create Resume");
                f.printStackTrace();
            }
            insertPersonalData(name,address,contact,email,dob,git,link);
            insertAcademicData(email,ssGrade,ssSchool,hsGrade,hsSchool,stream,grade,uni);
            insertTechnicalData(email,skill,project,duration,description,technology,activity1,activity2,activity3);
        }
        if(e.getSource() == search){
            String mail = JOptionPane.showInputDialog("Email");
            searchTable(mail);
        }
        if(e.getSource() == exit){
            System.exit(0);
        }
        if(e.getSource() == edit){
            String skill = "";

            if(ch1.isSelected()){
                skill += "C++";
            }
            if(ch2.isSelected()){
                skill += "Java";
            }
            if(ch3.isSelected()){
                skill += "Python";
            }
            if(ch4.isSelected()){
                skill += "Js";
            }
            if(ch5.isSelected()){
                skill += "C#";
            }
            if(ch6.isSelected()){
                skill += "R";
            }
            PreparedStatement ps1,ps2,ps3;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");  
                String url = "jdbc:mysql://localhost:3306/cv";
                String usrName = "root";
                String pass = "DBZ@123/goku";
                Connection conn = DriverManager.getConnection(url, usrName, pass);
                ps1 = conn.prepareStatement("update personalDetail set name = '"+nameTxt.getText()+"', address = '"+addressTxt.getText()+"', contact = '"+contactTxt.getText()+"', dob = '"+dobTxt.getText()+"', github = '"+gitTxt.getText()+"', linkedin = '"+linkTxt.getText()+"' where email = '"+emailTxt.getText()+"'");
                ps1.executeUpdate();
                ps2 = conn.prepareStatement("update academicDetail set ssGrade = '"+sscGrade.getText()+"', ssSchool = '"+sscSchool.getText()+"', hsGrade = '"+hscGrade.getText()+"', hsSchool = '"+hscSchool.getText()+"', stream = '"+gradStream.getText()+"', grad_grade = '"+gradGrade.getText()+"', University = '"+gradUni.getText()+"' where email = '"+emailTxt.getText()+"'");
                ps2.executeUpdate();
                ps3 = conn.prepareStatement("update technicalDetail set skill = '"+skill+"', project = '"+proNameTxt.getText()+"', duration = '"+proDurTxt.getText()+"', description = '"+proDescTxt.getText()+"', technology = '"+proTecTxt.getText()+"', activity1 = '"+coco1.getText()+"', activity2 = '"+coco2.getText()+"', activity3 = '"+coco3.getText()+"' where email = '"+emailTxt.getText()+"'");
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Data is Updated Succesfully");
                ps1.close();
                ps2.close();
                ps3.close();
                conn.close();
            }catch(Exception g){
                g.printStackTrace();
            }
        }
        if(e.getSource() == about){
            JOptionPane.showMessageDialog(frame, "This is a personal CV which is created by Aryan Singh\nHere you can create, edit, update your cv resume");
        }
        if(e.getSource() == delete){
            PreparedStatement ps1,ps2,ps3;
            String email = JOptionPane.showInputDialog("Email");
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");  
                String url = "jdbc:mysql://localhost:3306/cv";
                String usrName = "root";
                String pass = "DBZ@123/goku";
                Connection conn = DriverManager.getConnection(url, usrName, pass);
                ps3 = conn.prepareStatement("delete from technicalDetail where email = '"+email+"'");
                ps3.executeUpdate();
                ps2 = conn.prepareStatement("delete from academicDetail where email = '"+email+"'");
                ps2.executeUpdate();
                ps1 = conn.prepareStatement("delete from personalDetail where email = '"+email+"'");
                ps1.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Successfully Deleted");
                ps1.close();
                ps2.close();
                ps3.close();
            }catch(Exception h){
                h.printStackTrace();
            }
        }
    }

    public void searchTable(String mail){
        Connection conn = null;
        // PreparedStatement pst = null;
        Statement s1,s2,s3;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cv";
            String usrName = "root";
            String pass = "DBZ@123/goku";
            conn = DriverManager.getConnection(url, usrName, pass);
            
            s1 = conn.createStatement();
            s2 = conn.createStatement();
            s3 = conn.createStatement();
            String query1 = "select * from personalDetail where email = '"+mail+"' limit 1";
            String query2 = "select * from academicDetail where email = '"+mail+"' limit 1"; 
            String query3 = "select * from technicalDetail where email = '"+mail+"' limit 1"; 

            rs1 = s1.executeQuery(query1);
            rs2 = s2.executeQuery(query2);
            rs3 = s3.executeQuery(query3);

            // pst = conn.prepareStatement(query1);
            // pst = conn.prepareStatement(query2);
            // pst = conn.prepareStatement(query3);

            // ResultSet rs1 = pst.executeQuery();
            // ResultSet rs2 = pst.executeQuery();
            // ResultSet rs3 = pst.executeQuery();

            String name = "";
            String address = "";
            String contact = "";
            // String email = "";
            String dob = "";
            String git = "";
            String link = "";
            String hsGrade = "";
            String hsSchool = "";
            String ssGrade = "";
            String ssSchool = "";
            String uni = "";
            String stream = "";
            String grade = "";
            String project = "";
            String duration = "";
            String description = "";
            String technology = "";
            String activity1 = "";
            String activity2 = "";
            String activity3 = "";
            String skill = "";

            while(rs1.next()){
                name = rs1.getString(1);
                address = rs1.getString(2);
                contact = rs1.getString(3);
                // email = rs1.getString(4);
                dob = rs1.getString(5);
                git = rs1.getString(6);
                link = rs1.getString(7);
            }
            while(rs2.next()){
                // email = rs2.getString(1);
                ssGrade = rs2.getString(2);
                ssSchool = rs2.getString(3);
                hsGrade = rs2.getString(4);
                hsSchool = rs2.getString(5);
                stream = rs2.getString(6);
                grade = rs2.getString(7);
                uni = rs2.getString(8);
            }
            while(rs3.next()){
                // email = rs2.getString(1);
                skill = rs3.getString(2);
                project = rs3.getString(3);
                duration = rs3.getString(4);
                description = rs3.getString(5);
                technology = rs3.getString(6);
                activity1 = rs3.getString(7);
                activity2 = rs3.getString(8);
                activity3 = rs3.getString(9);
            }
            emailTxt.setText(mail);
            nameTxt.setText(name);
            addressTxt.setText(address);
            contactTxt.setText(contact);
            dobTxt.setText(dob);
            gitTxt.setText(git);
            linkTxt.setText(link);
            sscGrade.setText(ssGrade);
            sscSchool.setText(ssSchool);
            hscGrade.setText(hsGrade);
            hscSchool.setText(hsSchool);
            gradStream.setText(stream);
            gradGrade.setText(grade);
            gradUni.setText(uni);
            proNameTxt.setText(project);
            proDurTxt.setText(duration);
            proDescTxt.setText(description);
            proTecTxt.setText(technology);
            coco1.setText(activity1);
            coco2.setText(activity2);
            coco3.setText(activity3);

            String sk1 = "C++";
            String sk2 = "Java";
            String sk3 = "Python";
            String sk4 = "JS";
            String sk5 = "C#";
            String sk6 = "R";

            if(skill.contains(sk1)){
                ch1.setSelected(true);
            }
            if(skill.contains(sk2)){
                ch2.setSelected(true);
            }
            if(skill.contains(sk3)){
                ch3.setSelected(true);
            }
            if(skill.contains(sk4)){
                ch4.setSelected(true);
            }
            if(skill.contains(sk5)){
                ch5.setSelected(true);
            }
            if(skill.contains(sk6)){
                ch6.setSelected(true);
            }

            System.out.println("Data is Searched");
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void insertTechnicalData(String email,String skill,String project,String duration,String description,String technology,String activity1,String activity2,String activity3){
        PreparedStatement pst;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cv";
            String usrName = "root";
            String pass = "DBZ@123/goku";
            Connection conn = DriverManager.getConnection(url, usrName, pass);
    
            pst = conn.prepareStatement("insert into technicalDetail(email,skill,project,duration,description,technology,activity1,activity2,activity3) values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, email);
            pst.setString(2, skill);
            pst.setString(3, project);
            pst.setString(4, duration);
            pst.setString(5, description);
            pst.setString(6, technology);
            pst.setString(7, activity1);
            pst.setString(8, activity2);
            pst.setString(9, activity3);
            pst.executeUpdate();
            System.out.println("Technical Data is inserted in table");
            pst.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println("Technical Data is not inserted");
            e.printStackTrace();
        }  
    }

    public void insertAcademicData(String email,String ssGrade,String ssSchool,String hsGrade,String hsSchool,String stream,String grade,String uni){
        PreparedStatement pst;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            String url = "jdbc:mysql://localhost:3306/cv";
            String usrName = "root";
            String pass = "DBZ@123/goku";
            Connection conn = DriverManager.getConnection(url, usrName, pass);
            
            pst = conn.prepareStatement("insert into academicDetail (email,ssGrade,ssSchool,hsGrade,hsSchool,stream,grad_grade,university) values (?,?,?,?,?,?,?,?)");
            pst.setString(1, email);
            pst.setString(2, ssGrade);
            pst.setString(3, ssSchool);
            pst.setString(4, hsGrade);
            pst.setString(5, hsSchool);
            pst.setString(6, stream);
            pst.setString(7, grade);
            pst.setString(8, uni);

            pst.executeUpdate();
            System.out.println("Academic Data is inserted in table");
            conn.close();
            pst.close();
        }
        catch(Exception e){
            System.out.println("Academic Data is not inserted");
            e.printStackTrace();
        }     
    }
    /**
     * @param name
     * @param address
     * @param contact
     * @param email
     * @param dob
     * @param git
     * @param link
     */
    public void insertPersonalData(String name,String address,String contact,String email,String dob,String git,String link){
        PreparedStatement pst;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            String url = "jdbc:mysql://localhost:3306/cv";
            String usrName = "root";
            String pass = "DBZ@123/goku";
            Connection conn = DriverManager.getConnection(url, usrName, pass);
            
            pst = conn.prepareStatement("insert into personalDetail (name,address,contact,email,dob,github,linkedin) values (?,?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, contact);
            pst.setString(4, email);
            pst.setString(5, dob);
            pst.setString(6, git);
            pst.setString(7, link);

            pst.executeUpdate();
            System.out.println("Personal Data is inserted in table");
            conn.close();
            pst.close();
        }
        catch(Exception e){
            System.out.println("Personal Data is not inserted");
            e.printStackTrace();
        }     
    }
    public void createFields(){
        frame = new JFrame("CV Builder");
        menubar = new JMenuBar();
        menu1 = new JMenu("File");
        menu2 = new JMenu("Edit");
        menu3 = new JMenu("Help");
        
        save = new JMenuItem("Save");
        search = new JMenuItem("Search");
        edit = new JMenuItem("Edit");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");
        delete = new JMenuItem("Delete");
        // title = new JLabel("CV BUILDER");
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        nameLabel = new JLabel("Name");
        addressLabel = new JLabel("Address");
        contactLabel = new JLabel("Contact");
        emailLabel = new JLabel("Email");
        dobLabel = new JLabel("DOB");
        gitLabel = new JLabel("Github");
        linkLabel = new JLabel("Linkedin");
        skillLabel = new JLabel("Skills");
        eduLabel = new JLabel("Education");
        hscLabel = new JLabel("High School");
        sscLabel = new JLabel("Secondary");
        gradLabel = new JLabel("Graduation");
        perLabel = new JLabel("Personal Information");
        projectLabel = new JLabel("Academic Project");
        proName = new JLabel("Name");
        proDurLabel = new JLabel("Duration");
        proDescLabel = new JLabel("Description");
        proTecLabel = new JLabel("Technologies");
        cocoLabel = new JLabel("Co-curricular Activities");

        nameTxt = new JTextField();
        addressTxt = new JTextField();
        contactTxt = new JTextField();
        emailTxt = new JTextField();
        dobTxt = new JTextField();
        gitTxt = new JTextField();
        linkTxt = new JTextField();
        
        ch1 = new JCheckBox("C++");
        ch2 = new JCheckBox("Java");
        ch3 = new JCheckBox("Python");
        ch4 = new JCheckBox("JS");
        ch5 = new JCheckBox("C#");
        ch6 = new JCheckBox("R");
        
        hscSchool = new JTextField("School Name");
        sscSchool = new JTextField("School Name");
        hscGrade = new JTextField("Grade");
        sscGrade = new JTextField("Grade");
        gradUni = new JTextField("University");
        gradGrade = new JTextField("Grade");
        gradStream = new JTextField("Stream");

        proNameTxt = new JTextField();
        proDurTxt = new JTextField();
        proDescTxt = new JTextField();
        proTecTxt = new JTextField();
    
        coco1 = new JTextField();
        coco2 = new JTextField();
        coco3 = new JTextField();
        
        // createBtn = new JButton("Create");
        // searchBtn = new JButton("Search");
    }
    
    public void addFields(){

        menu1.add(save);
        menu1.add(exit);
        menu2.add(search);
        menu2.add(edit);
        menu2.add(delete);
        menu3.add(about);

        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);


        panel1.add(perLabel);
        panel1.add(nameLabel);
        panel1.add(nameTxt);
        panel1.add(contactLabel);
        panel1.add(contactTxt);
        panel1.add(addressLabel);
        panel1.add(addressTxt);
        panel1.add(emailLabel);
        panel1.add(emailTxt);
        panel1.add(dobLabel);
        panel1.add(dobTxt);
        panel1.add(gitLabel);
        panel1.add(gitTxt);
        panel1.add(linkLabel);
        panel1.add(linkTxt);

        panel1.add(cocoLabel);
        panel1.add(coco1);
        panel1.add(coco2);
        panel1.add(coco3);

        // panel1.add(createBtn);

        panel2.add(skillLabel);
        panel2.add(ch1);
        panel2.add(ch2);
        panel2.add(ch3);
        panel2.add(ch4);
        panel2.add(ch5);
        panel2.add(ch6);
        panel2.add(eduLabel);

        panel2.add(hscLabel);
        panel2.add(hscSchool);
        panel2.add(hscGrade);
        panel2.add(sscLabel);
        panel2.add(sscSchool);
        panel2.add(sscGrade);
        panel2.add(gradLabel);
        panel2.add(gradStream);
        panel2.add(gradUni);
        panel2.add(gradGrade);
        panel2.add(projectLabel);
        panel2.add(proName);
        panel2.add(proNameTxt);
        panel2.add(proDurLabel);
        panel2.add(proDurTxt);
        panel2.add(proDescLabel);
        panel2.add(proDescTxt);
        panel2.add(proTecLabel);
        panel2.add(proTecTxt);

        // panel2.add(searchBtn);

        panel.add(panel1);
        panel.add(panel2);
        // frame.add(title,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
    }
    
    public void setField(){
        frame.setSize(1024,720);
        frame.setLocation(100, 20);
        frame.setLayout(new BorderLayout());
        
        frame.setJMenuBar(menubar);
        // title.setHorizontalAlignment(0);
        // title.setFont(new Font("Verdana", Font.PLAIN, 25));
        // title.setPreferredSize(new Dimension(250, 100));
        
        panel.setLayout(new GridLayout(1,2));
        panel1.setLayout(null);
        panel2.setLayout(null);


        perLabel.setFont(new Font("Verdana",Font.BOLD,15));
        perLabel.setBounds(50,50,180,30);

        nameLabel.setBounds(50,100,100,30);
        nameTxt.setBounds(110,100,120,30);
        contactLabel.setBounds(50,140,100,30);
        contactTxt.setBounds(110,140,120,30);
        addressLabel.setBounds(50,180,100,30);
        addressTxt.setBounds(110,180,120,30);
        emailLabel.setBounds(50,220,100,30);
        emailTxt.setBounds(110,220,120,30);
        dobLabel.setBounds(50,260,120,30);
        dobTxt.setBounds(110,260,120,30);
        gitLabel.setBounds(50,300,120,30);
        gitTxt.setBounds(110,300,120,30);
        linkLabel.setBounds(50,340,120,30);
        linkTxt.setBounds(110,340,120,30);

        cocoLabel.setFont(new Font("Verdana",Font.BOLD,15));
        cocoLabel.setBounds(50,380,300,30);
        coco1.setBounds(50,420,150,30);
        coco2.setBounds(50,460,150,30);
        coco3.setBounds(50,500,150,30);


        skillLabel.setBounds(10,50,80,30);
        skillLabel.setFont(new Font("Verdana",Font.BOLD,15));
        ch1.setBounds(60,50,60,30);
        ch2.setBounds(120,50,60,30);
        ch3.setBounds(180,50,80,30);
        ch4.setBounds(260,50,60,30);
        ch5.setBounds(320,50,60,30);
        ch6.setBounds(380,50,80,30);
        
        eduLabel.setFont(new Font("Verdana",Font.BOLD,15));
        eduLabel.setBounds(10,70,300,100);

        hscLabel.setBounds(10,150,100,30);
        hscGrade.setBounds(10,180,200,30);
        hscSchool.setBounds(230,180,200,30);
        sscLabel.setBounds(10,210,100,30);
        sscGrade.setBounds(10,240,200,30);
        sscSchool.setBounds(230,240,200,30);
        gradLabel.setBounds(10,270,100,30);
        gradStream.setBounds(10,300,100,30);
        gradGrade.setBounds(120,300,100,30);
        gradUni.setBounds(230,300,200,30);

        projectLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        projectLabel.setBounds(10,330,300,100);

        proName.setBounds(10,400,100,30);
        proNameTxt.setBounds(90,400,200,30);
        proDurLabel.setBounds(10,440,100,30);
        proDurTxt.setBounds(90,440,200,30);
        proDescLabel.setBounds(10,480,100,30);
        proDescTxt.setBounds(90,480,200,30);
        proTecLabel.setBounds(10,520,100,30);
        proTecTxt.setBounds(90,520,200,30);
        
        // createBtn.setBounds(50,510,200,40);
        // createBtn.setFont(new Font("Verdana",Font.BOLD,15));

        // searchBtn.setBounds(10,510,200,40);
        // searchBtn.setFont(new Font("Verdana",Font.BOLD,15));

        
    }
    public static void main(String[] args) throws Exception {
        new App();   
    }
}