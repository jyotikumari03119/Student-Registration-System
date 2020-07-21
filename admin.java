import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class admin extends JFrame implements ActionListener
{
Connection conn;
PreparedStatement pst,pst1;
ResultSet rs;

JFrame fr;
Label l1, l2, l3, l4, l5, l6, l7, l8, l9;
JLabel h, head, logo, side;

TextField t1, t2, t3, t4, t5, t6, t7;
JComboBox c;
JRadioButton r1, r2;
Button m1, m2, m3, m4, b1, b2, b3, b4, b5, b6;
Panel p, p1, p2, p3;
int count=0;
admin()
{
fr=new JFrame();
fr.setContentPane(new JLabel(new ImageIcon("Login.jpg")));
fr.setBounds(0,0,2000,2000);

h=new JLabel("<html><body><center><font color=red size=6><u>WELCOME TO ADMIN PORTAL</u></font></center></body></html>");
h.setBounds(525,10,700,50);
fr.add(h);

logo=new JLabel();
logo.setBounds(20,20,500,200);
logo.setIcon(new ImageIcon("Logo.png"));
fr.add(logo);

head=new JLabel();
head.setBounds(1070,10,800,300);
head.setIcon(new ImageIcon("Admin.png"));
fr.add(head);

p=new Panel();
p.setBounds(20,350,200,100);
p.setLayout(new GridLayout(4,1));
fr.add(p);

p1=new Panel();
p1.setBounds(525,300,300,100);
p1.setLayout(new GridLayout(4,2));
fr.add(p1);
p1.setVisible(false);

p2=new Panel();
p2.setBounds(525,300,300,150);
p2.setLayout(new GridLayout(5,2));
fr.add(p2);
p2.setVisible(false);

p3=new Panel();
p3.setBounds(525,300,300,100);
p3.setLayout(new GridLayout(4,2));
fr.add(p3);
p3.setVisible(false);

l1=new Label("Course Name");
l2=new Label("Duration");
l3=new Label("Fee");
l4=new Label("Student Name");
l5=new Label("Registration No");
l6=new Label("Certification");
l7=new Label("Select Course");
l8=new Label("Current Fee");
l9=new Label("New Fee");

t1=new TextField();
t2=new TextField();
t3=new TextField();
t4=new TextField();
t5=new TextField();
t6=new TextField();
t7=new TextField();
c=new JComboBox();
c.addItem("Select Course");
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from course");
rs=pst.executeQuery();
while(rs.next())
c.addItem(rs.getString(1));
conn.close();
pst.close();
}
catch(Exception k)
{
JOptionPane.showMessageDialog(null,"failed="+k);
}

r1=new JRadioButton("YES");
r2=new JRadioButton("NO");
r1.setBounds(650,220,100,30);
r2.setBounds(750,220,100,30);
b1=new Button ("Save");
b2=new Button ("Reset");
b3=new Button ("Save");
b4=new Button ("Reset");
b5=new Button ("Save");
b6=new Button ("Reset");

m1=new Button ("Add Course");
m2=new Button ("List of Students");
m3=new Button ("Update Fee");
m4=new Button ("Certification");

p.add(m1);
p.add(m2);
p.add(m3);
p.add(m4);

p1.add(l1); 
p1.add(t1);
p1.add(l2);
p1.add(t2);
p1.add(l3);
p1.add(t3);
p1.add(b1);
p1.add(b2);

p2.add(l4);
p2.add(t4);
p2.add(l5);
p2.add(t5);
p2.add(l6);
p2.add(r1);
p2.add(r2);
p2.add(b3);
p2.add(b4);

p3.add(l7); 
p3.add(c);
p3.add(l8);
p3.add(t6);
p3.add(l9);
p3.add(t7);
p3.add(b5);
p3.add(b6);

m1.addActionListener(this);
m2.addActionListener(this);
m3.addActionListener(this);
m4.addActionListener(this);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
r1.addActionListener(this);
r2.addActionListener(this);

c.addActionListener(this);
t4.addActionListener(this);

fr.setLayout(null);
fr.setVisible(true);

}

public void actionPerformed(ActionEvent k)
{

if(k.getSource()==m1)
{
p1.setVisible(true);
p2.setVisible(false);
p3.setVisible(true);
}

if(k.getSource()==m2)
{
new jtable();
}

if(k.getSource()==m3)
{
p1.setVisible(false);
p2.setVisible(false);
p3.setVisible(true);
}

if(k.getSource()==m4)
{
p1.setVisible(false);
p2.setVisible(true);
p3.setVisible(false);
}

if(k.getSource()==c)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from course where name=?");
pst.setString(1,String.valueOf(c.getSelectedItem()));
rs=pst.executeQuery();
if(rs.next())
t6.setText(rs.getString(3));
conn.close();
pst.close();
}
catch(Exception k1)
{
}
}

if(k.getSource()==t4)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info where name=?");
pst.setString(1,t4.getText());
rs=pst.executeQuery();
if(rs.next())
t5.setText(rs.getString(6));
conn.close();
pst.close();
}
catch(Exception k1)
{
}
}

if(k.getSource()==b1)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("insert into course values(?,?,?)");
pst.setString(1,t1.getText());
pst.setString(2,t2.getText());
pst.setString(3,t3.getText());
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Course Details Saved Successfully!");
conn.close();
pst.close();
}
catch(Exception e1)
{	
JOptionPane.showMessageDialog(null,"Failed="+e1);
}
}

if(k.getSource()==b2)
{
JOptionPane.showMessageDialog(null, "Cleared");
t1.setText("");
t2.setText("");
t3.setText("");
}

if(k.getSource()==b3)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("insert into certification values(?,?,?)");
pst.setString(1,t4.getText());
pst.setString(2,t5.getText());
if(r1.isSelected())
pst.setString(3,"Yes");
if(r2.isSelected())
pst.setString(3,"No");
pst1=conn.prepareStatement("select * from certification");
ResultSet rs23=pst1.executeQuery();
while(rs23.next())
{
if(rs23.getString(2).equals(t5.getText()))
count++;
}
pst1.close();
if(count==0)
{
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Certification Status Updated!");
}
else
JOptionPane.showMessageDialog(null,"Duplication ");
conn.close();
pst.close();
}
catch(Exception e2)
{	
JOptionPane.showMessageDialog(null,"Failed="+e2);
}
}

if(k.getSource()==b4)
{
JOptionPane.showMessageDialog(null, "Cleared");
t4.setText("");
t5.setText("");
r1.setSelected(false);
r2.setSelected(false);
}

if(k.getSource()==b5)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("update course set fee=? where name=?");
pst.setString(1,t7.getText());
pst.setString(2,String.valueOf(c.getSelectedItem()));
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Course Fee Updated Successfully!");
conn.close();
pst.close();
}
catch(Exception e1)
{	
JOptionPane.showMessageDialog(null,"Failed="+e1);
}
}

if(k.getSource()==b6)
{
JOptionPane.showMessageDialog(null, "Cleared");
t6.setText("");
t7.setText("");
}

if(k.getSource()==r1)
{
r2.setSelected(false);
}

if(k.getSource()==r2)
{
r1.setSelected(false);
}

}

public static void main(String args[])
{
new admin();
}
