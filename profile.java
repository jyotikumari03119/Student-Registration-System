import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class profile extends JFrame implements ActionListener
{
Connection conn;
PreparedStatement pst;
ResultSet rs;

String d;
JMenuBar mb;
JMenu m1, m2, m3;
JMenuItem i1, i2, i3, i4, i5;
Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
JFrame fr;
Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
TextField ta1, ta2, ta3;
JLabel h, head, logo, side, lpic;
JLabel name, address, course, email, mobile, regno, img;
Panel p, p1, p2, p3, p4;
Timer t;
int count=1;

profile()
{
fr=new JFrame();
fr.setContentPane(new JLabel(new ImageIcon("Login.jpg")));
fr.setBounds(0,0,2000,2000);

mb=new JMenuBar();
mb.setBounds(0,0,1600,30);
m1=new JMenu("Settings");
m2=new JMenu("Fee");
m3=new JMenu("Certification");
i1=new JMenuItem("Update Mobile Number");
i2=new JMenuItem("Update Address");
i3=new JMenuItem("Check Fee Status");
i4=new JMenuItem("Pay Fee");
i5=new JMenuItem("Check Certification Status");
fr.add(mb);
m1.add(i1);
m1.add(i2);
m2.add(i3);
m2.add(i4);
m3.add(i5);
mb.add(m1);
mb.add(m2);
mb.add(m3);

h=new JLabel("<html><body><font color=Red size=8><u>Welcome To Your Profile</u></font></body></html>");
h.setBounds(460,30,700,50);
fr.add(h);

logo=new JLabel();
logo.setBounds(20,40,500,200);
logo.setIcon(new ImageIcon("Logo.png"));
fr.add(logo);

head=new JLabel();
head.setBounds(1090,20,800,300);
head.setIcon(new ImageIcon("Stu_Pro.png"));
fr.add(head);

p=new Panel();
p.setBounds(525,325,300,200);
p.setLayout(new GridLayout(7,2));

p1=new Panel();
p1.setBounds(525,325,300,100);
p1.setLayout(new GridLayout(3,2));
fr.add(p1);
p1.setVisible(false);

p2=new Panel();
p2.setBounds(525,325,300,100);
p2.setLayout(new GridLayout(3,2));
fr.add(p2);
p2.setVisible(false);

p3=new Panel();
p3.setBounds(525,325,300,30);
p3.setLayout(new GridLayout(1,2));
fr.add(p3);
p3.setVisible(false);

p4=new Panel();
p4.setBounds(525,325,300,150);
p4.setLayout(new GridLayout(5,2));
fr.add(p4);
p4.setVisible(false);

lpic=new JLabel();
lpic.setBounds(625,200,100,120);
lpic.setIcon(new ImageIcon(home.img));
fr.add(lpic);

l1=new Label("Name:");
l2=new Label("Registration No:");
l3=new Label("Course:");
l4=new Label("Address:");
l5=new Label("Email:");
l6=new Label("Mobile:");
l7=new Label("Existing Mobile No.:");
l8=new Label("New Mobile No.:");
l9=new Label("Existing Address:");
l10=new Label("New Address:");
l11=new Label("Eligible For Certificate:");
l12=new Label("Name:");
l13=new Label("Registration No.:");
l14=new Label("Course:");
l15=new Label("Payment Amount:");

b1=new Button("Logout");
b2=new Button("Exit");
b3=new Button("Update");
b4=new Button("Back");
b5=new Button("Update");
b6=new Button("Back");
b7=new Button("OK");
b8=new Button("Pay");
b9=new Button("Back");

t1=new Label();
t2=new Label();
t3=new Label();
t4=new Label();
t5=new Label();
t6=new Label();
t7=new Label();
t8=new Label();
t9=new Label();
t10=new Label();
t11=new Label();
t12=new Label();

ta1=new TextField();
ta2=new TextField();
ta3=new TextField();

t1.setText(home.name);
t2.setText(home.regno);
t3.setText(home.course);
t4.setText(home.address);
t5.setText(home.email);
t6.setText(home.mobile);

p.add(l1);
p.add(t1);
p.add(l2);
p.add(t2);
p.add(l3);
p.add(t3);
p.add(l4);
p.add(t4);
p.add(l5);
p.add(t5);
p.add(l6);
p.add(t6);
p.add(b1);
p.add(b2);
fr.add(p);

p1.add(l7);
p1.add(t7);
p1.add(l8);
p1.add(ta1);
p1.add(b3);
p1.add(b4);

p2.add(l9);
p2.add(t8);
p2.add(l10);
p2.add(ta2);
p2.add(b5);
p2.add(b6);

p3.add(l11);
p3.add(t9);
b7.setBounds(625,360,100,30);
fr.add(b7);
b7.setVisible(false);

p4.add(l12);
p4.add(t10);
p4.add(l13);
p4.add(t11);
p4.add(l14);
p4.add(t12);
p4.add(l15);
p4.add(ta3);
p4.add(b8);
p4.add(b9);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this); 
b7.addActionListener(this);
b8.addActionListener(this);
b9.addActionListener(this);

i1.addActionListener(this);
i2.addActionListener(this);
i3.addActionListener(this);
i4.addActionListener(this);
i5.addActionListener(this);
fr.setLayout(null);
fr.setVisible(true);

d=String.valueOf(new java.util.Date());
}

public void actionPerformed(ActionEvent k)
{

if(k.getSource()==b1)
{
fr.dispose();
new home();
JOptionPane.showMessageDialog(null, "You Have Been Successfully Logged Out!");
}

if(k.getSource()==b2)
{
fr.dispose();
}

if(k.getSource()==b3)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("update info set mobile=? where name=?");
pst.setString(1,ta1.getText());
pst.setString(2,t1.getText());
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Mobile Number Updated Successfully!");
conn.close();
pst.close();
}
catch(Exception e1)
{	
JOptionPane.showMessageDialog(null,"Failed="+e1);
}
}

if(k.getSource()==b4)
{
fr.dispose();
new profile();
}

if(k.getSource()==b5)
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("update info set address=? where name=?");
pst.setString(1,ta2.getText());
pst.setString(2,t1.getText());
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Address Updated Successfully!");
conn.close();
pst.close();
}
catch(Exception e2)
{	
JOptionPane.showMessageDialog(null,"Failed="+e2);
}
}

if(k.getSource()==b6)
{
fr.dispose();
new profile();
}

if(k.getSource()==b7)
{
fr.dispose();
new profile();
}

if(k.getSource()==b8)
{
	try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("insert into payment values(?,?,?,?,?)");
pst.setString(1,t10.getText());
pst.setString(2,t11.getText());
pst.setString(3,t12.getText());
pst.setString(4,ta3.getText());
pst.setString(5,d);
pst.executeUpdate();
JOptionPane.showMessageDialog(null,"Payment Processed!");
conn.close();
pst.close();
}
catch(Exception e)
{	
JOptionPane.showMessageDialog(null,"Failed="+e);
}

}

if(k.getSource()==b9)
{
fr.dispose();
new profile();
}

if(k.getSource()==i1)
{
p.setVisible(false);
p1.setVisible(true);
p2.setVisible(false);
p3.setVisible(false);
b7.setVisible(false);
p4.setVisible(false);

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info where name=?");
pst.setString(1,t1.getText());
rs=pst.executeQuery();
if(rs.next())
t7.setText(rs.getString(5));
conn.close();
pst.close();
}
catch(Exception k2)
{
JOptionPane.showMessageDialog(null, "Failed="+k2);
}

}

if(k.getSource()==i2)
{
p.setVisible(false);
p1.setVisible(false);
p2.setVisible(true);
p3.setVisible(false);
b7.setVisible(false);
p4.setVisible(false);

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info where name=?");
pst.setString(1,t1.getText());
rs=pst.executeQuery();
if(rs.next())
t8.setText(rs.getString(2));
conn.close();
pst.close();
}
catch(Exception k3)
{
JOptionPane.showMessageDialog(null, "Failed="+k3);
}

}

if(k.getSource()==i3)
{
new Fee_Table();
}

if(k.getSource()==i4)
{
p.setVisible(false);
p1.setVisible(false);
p2.setVisible(false);
p3.setVisible(false);
b7.setVisible(false);
p4.setVisible(true);
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info where name=?");
pst.setString(1,t1.getText());
rs=pst.executeQuery();
if(rs.next())
t10.setText(rs.getString(1));
t11.setText(rs.getString(6));
t12.setText(rs.getString(3));
conn.close();
pst.close();
}
catch(Exception k4)
{
JOptionPane.showMessageDialog(null, "Failed="+k4);
}

}

if(k.getSource()==i5)
{
p.setVisible(false);
p1.setVisible(false);
p2.setVisible(false);
p3.setVisible(true);
b7.setVisible(true);
p4.setVisible(false);
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from certification where name=?");
pst.setString(1,t1.getText());
rs=pst.executeQuery();
if(rs.next())
t9.setText(rs.getString(3));
conn.close();
pst.close();
}
catch(Exception k3)
{
JOptionPane.showMessageDialog(null, "Failed="+k3);
}

}

}

public static void main(String args[])
{
new profile();
}

}
