package test;

import java.sql.*; 
	public class test { 
		//dbUrl数据库连接串信息，其中“1521”为端口，“ora9”为sid 
		String dbUrl = "jdbc:oracle:thin:@10.176.16.70:1521:dslrb2c"; 
		//theUser为数据库用户名 
		String theUser = "dslrww"; 
		//thePw为数据库密码 
		String thePw = "ww5005"; 
		//几个数据库变量 
		Connection c = null; 
		Statement conn; 
		ResultSet rs = null; 
		//初始化连接 
		public test() { 
			try { 
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
				//与url指定的数据源建立连接 
				c = DriverManager.getConnection(dbUrl, theUser, thePw); 
				//采用Statement进行查询 
				conn = c.createStatement(); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		} 
			
		//执行查询 
			public ResultSet executeQuery(String sql) { 
				rs = null; 
				try { 
					rs = conn.executeQuery(sql); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
				return rs; 
			} 
			
			//执行更新
			public void executeUpdate(String sql) { 
				try { 
					Statement conn1 = c.createStatement(); 
					conn1.executeUpdate(sql);
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
			
			 
			
			public void close() { 
				try { 
					conn.close(); 
					c.close(); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
			
			public static void main(String[] args) { 
				ResultSet newrs; 
				test newjdbc = new test(); 
				String sql1 = "select m.users_id,m.field7,m.firstname,m.logonId,m.field3 from (select s.users_id,o.field7,a.firstname,g.logonId,s.field3 from users s " +
					" left join userdemo o on s.users_id=o.users_id left join address a on s.users_id=a.member_id left join userreg g on " +
					" s.users_id=g.users_id where s.field3 in( select field3 from users group by field3 having count(field3)>1))m where m.field7 " +
					" not in (select m.CARD_ID_EXT from  x_b2ctocrm_userinfo m where m.B2C_CARD in (select field3 from users group by field3" +
					" having count(field3)>1))";
				newrs = newjdbc.executeQuery(sql1); 
				
				try { 
					String card = "M88140522";
					int kk = 900057;
					String b2cCard="";
					Double ll=0.0;
					
					while (newrs.next()){ 
						String ss = String.valueOf(kk);
						b2cCard = card+ss;
						String sql2 = "insert into x_b2ctocrm_userinfo(id,card_id_ext,lastname,sex,telephone,birthdate,createtime, lastupdate," +
							"isprocess,b2c_card,reg_store,CORRESPONDLANGUAGEISO,COUNTRY,REF_STORE,SOURCE,OCCUPATION) " +
							"values(ECOMB2C.X_USERINFO.nextval,'"+newrs.getString("field7")+"','"+newrs.getString("firstname")+"','1','"+newrs.getString("logonId")+"'," +
							"TIMESTAMP '1988-02-24 00:00:00',sysdate, sysdate,'0','"+b2cCard+"','2000050','ZH','CN'," +
							"'2000050','B2C','0020')";
						ll= Double.parseDouble(newrs.getString("users_id"));
						String sql3 = "update users set field3='"+b2cCard+"' where users_id="+ll;
						newjdbc.executeUpdate(sql2);
						System.out.println(sql3);
						newjdbc.executeUpdate(sql3);
						kk++; 
						System.out.println(b2cCard);
					} 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		}