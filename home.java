import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class home extends JFrame implements ActionListener
{

Connection conn;
PreparedStatement pst;
ResultSet rs;

JRadioButton r1, r2;
JFrame fr;
Label l1, l2, l3;
JLabel h1, head, logo, side;
static String name, address, course, email, mobile, regno, img;
TextField t1;
JPasswordField t2;
Button b1, b2;
Panel p;
Timer t;
int count=1;

home()
{
fr=new JFrame();
fr.setContentPane(new JLabel(new ImageIcon("Login.jpg")));
fr.setBounds(0,0,2000,2000);

h1=new JLabel("<html><body><center><font color=red size=6><u>Welcome To Student Inforamtion System</u></font></center></body></html>");
h1.setBounds(435,10,700,50);
fr.add(h1);


r1=new JRadioButton("ADMIN");
r2=new JRadioButton("USER");
r1.setBounds(650,220,100,30);
r2.setBounds(750,220,100,30);
fr.add(r1);
fr.add(r2);

logo=new JLabel();
logo.setBounds(20,20,500,200);
logo.setIcon(new ImageIcon("Logo.png"));
fr.add(logo);

head=new JLabel();
head.setBounds(425,20,800,200);
head.setIcon(new ImageIcon("Login1.jpg"));
fr.add(head);

side=new JLabel();
side.setBounds(1000,190,300,300);
fr.add(side);
t=new Timer(2000, new ActionListener()
{
public void actionPerformed(ActionEvent k)
{
if (count==5)
count=1;
side.setIcon(new ImageIcon("A"+count+".jpg"));
count++;
}
});
t.start();

p=new Panel();
p.setBounds(525,300,300,100);
p.setLayout(new GridLayout(3,2));
l1=new Label("Username");
l2=new Label("Password");
l3=new Label("SELECT YOUR LOGIN TYPE:");
l3.setBounds(490,220,160,30);
t1=new TextField();
t2=new JPasswordField();
b1=new Button ("Login");
b2=new Button ("Register");

fr.add(l3);
p.add(l1);
p.add(t1);
p.add(l2);
p.add(t2);
p.add(b1);
p.add(b2);
fr.add(p);

b1.addActionListener(this);
b2.addActionListener(this);
r1.addActionListener(this);
r2.addActionListener(this);

fr.setLayout(null);
fr.setVisible(true);

}

public void actionPerformed(ActionEvent k)
{

if(k.getSource()==r1)
{
r2.setSelected(false);
}

if(k.getSource()==r2)
{
r1.setSelected(false);
}

if(k.getSource()==b1)
{
if(r2.isSelected())
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info where name=? and regno=?");
pst.setString(1, t1.getText());
pst.setString(2, t2.getText());
rs=pst.executeQuery();
if(rs.next())
{
name=rs.getString(1);
address=rs.getString(2);
course=rs.getString(3);
email=rs.getString(4);
mobile=rs.getString(5);
regno=rs.getString(6);
img=rs.getString(7);
fr.dispose();
new profile();
}
else
{
JOptionPane.showMessageDialog(null, "User Not Registered!");
}
conn.close();
pst.close();
}
catch (Exception e1)
{
JOptionPane.showMessageDialog(null, "Failed="+e1);
}
}
else
{
String u=t1.getText();
String p=t2.getText();
if((u.equals("kunal"))&&(p.equals("cdac")))
{
fr.dispose();
new admin();
}
else
{
JOptionPane.showMessageDialog(null, "Select Proper Login Type");
}
}
}


if(k.getSource()==b2)
{
fr.dispose();
new reg();
}

}

public static void main(String args[])
{
new home();
}

}
