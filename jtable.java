import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class jtable implements ActionListener
{
Connection conn;
PreparedStatement pst;
ResultSet rs;

JTextArea ta;
int i=0,j=0;
JButton b;
JFrame fr;
JTable jt;
JScrollPane s;
Object r[][]=new Object[100][100];
Object c[]={"NAME","ADDRESS","COURSE","EMAIL","MOBILE","REG"};
jtable()
{
fr=new JFrame();
ta=new JTextArea();
ta.setBounds(0,0,400,500);
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:Xe","system","a");
pst=conn.prepareStatement("select * from info");
rs=pst.executeQuery();
while(rs.next())
{
r[i][j++]=rs.getString(1);
r[i][j++]=rs.getString(2);
r[i][j++]=rs.getString(3);
r[i][j++]=rs.getString(4);
r[i][j++]=rs.getString(5);
r[i][j++]=rs.getString(6);
i++;
j=0;
}
conn.close();
pst.close();
}
catch(Exception k)
{
JOptionPane.showMessageDialog(null,"failed="+k);
}

jt=new JTable(r,c);
s=new JScrollPane(jt);
ta.add(s);
s.setBounds(0,0,400,300);

fr.add(ta);
b=new JButton("Print");
b.setBounds(0,500,100,30);
fr.add(b);
b.addActionListener(this);
fr.setLayout(null);
fr.setBounds(0,0,425,580);
fr.setVisible(true);
}

public void actionPerformed(ActionEvent k)
{
try
{
ta.print();
}
catch(Exception j)
{
}
}
public static void main( String[] args )
{
new jtable();
}
}
