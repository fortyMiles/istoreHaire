package com.istore.common.core.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

	public String insertSql() {
		return new SQL().INSERT_INTO("t_user")
				.VALUES("USERNAME", "#{user.username}")
				.VALUES("PASSWORD", "#{user.password}")
				.VALUES("EMAIL", "#{user.email}").toString();
	}

	public String updateSql() {
		return new SQL().UPDATE("t_user").SET("USERNAME = #{user.username}")
				.SET("PASSWORD = #{user.password}")
				.SET("EMAIL = #{user.password}").WHERE("USER_ID = #{user.id}")
				.toString();
	}

	public String getUserCount(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT("count(s.users_id)as count")
				.FROM(" users s")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id ")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id ")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_pos")
				.WHERE("s.field1 in('"
						+ string
						+ "') and a.selfaddress='1' and (o.field1='1' or o.field1='2')");
		return sql.toString();
	}

	public String getUserList(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" s.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,p.shopid_pos,s.field3 as newCardNum")
				.FROM(" users s")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id ")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_sap")
				.WHERE("s.field1 in('"
						+ string
						+ "') and a.selfaddress='1' and (o.field1='1' or o.field1='2')");
		return "select w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.shopid_pos, w.firstname,w.newCardNum from (select k.users_id, k.logonid ,k.codeNum, "
				+ "k.phone1,k.firstname,k.birthDay,k.shopid_pos,k.newCardNum ,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String getOrderCount(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL().SELECT(" count(a.orders_id)as count")
				.FROM(" orders a")
				.LEFT_OUTER_JOIN(" users s on a.member_id=s.users_id")
				.WHERE("s.field1 in('" + string + "')");
		return sql.toString();
	}

	public String getOrderList(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" a.orders_id,a.ormorder,a.totalproduct,a.totaladjustment,a.description,g.logonId,a.status,a.timeplaced,a.totalshipping,p.fullname")
				.FROM(" orders a")
				.LEFT_OUTER_JOIN(" users s on a.member_id=s.users_id ")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1=p.shopid_sap")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id=g.users_id ")
				.WHERE("s.field1 in('" + string + "')");
		return "select w.orders_id,w.logonId,w.totalproduct,w.totaladjustment,w.totalshipping,w.status,w.totalshipping,w.timeplaced ,w.description,w.fullname from (select k.orders_id,k.logonId,"
				+ " k.totalproduct,k.totaladjustment,k.status,k.totalshipping,k.timeplaced ,k.description ,k.fullname,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String getTotalCountForAdmin(Map<String, Object> map) {
		StringBuffer buffer = new StringBuffer();
		String shopId = map.get("shopId").toString();
		String[] arry = shopId.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		buffer.append("".equals(map.get("userName")) ? ""
				: " and a.firstname like '%" + map.get("userName") + "%'");
		buffer.append("".equals(map.get("code")) ? "" : " and o.field7 like '%"
				+ map.get("code") + "%'");
		buffer.append("".equals(map.get("phone")) ? ""
				: " and a.phone1 like '%" + map.get("phone") + "%'");
		buffer.append("".equals(map.get("searchShopId")) ? ""
				: " and p.SHOPID_POS = '" + map.get("searchShopId") + "'");
		buffer.append("".equals(map.get("startDate")) ? ""
				: " and g.PASSWORDCREATION>=to_date('" + map.get("startDate")
						+ "','yyyy-MM-dd')");
		buffer.append("".equals(map.get("endDate")) ? ""
				: " and g.PASSWORDCREATION<=to_date('" + map.get("endDate")
						+ "','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql = new SQL()
				.SELECT("count(g.users_id)as count")
				.FROM(" users s ")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id ")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_sap")
				.WHERE("s.field1 in('"
						+ string
						+ "') and a.SELFADDRESS='1' and (o.field1='1' or o.field1='2')"
						+ str);
		return sql.toString();
	}

	public String getUserListForAdmin(Map<String, Object> parameters) {
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		String shopId = parameters.get("shopId").toString();
		String[] arry = shopId.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? ""
				: " and a.firstname like'%" + parameters.get("userName") + "%'");
		buffer.append("".equals(parameters.get("code")) ? ""
				: " and o.field7 like '%" + parameters.get("code") + "%'");
		buffer.append("".equals(parameters.get("phone")) ? ""
				: " and a.phone1 like '%" + parameters.get("phone") + "%'");
		buffer.append("".equals(parameters.get("searchShopId")) ? ""
				: " and p.SHOPID_POS = '" + parameters.get("searchShopId")
						+ "'");
		buffer.append("".equals(parameters.get("startDate")) ? ""
				: " and g.PASSWORDCREATION>=to_date('"
						+ parameters.get("startDate") + "','yyyy-MM-dd')");
		buffer.append("".equals(parameters.get("endDate")) ? ""
				: " and g.PASSWORDCREATION<=to_date('"
						+ parameters.get("endDate") + "','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql = new SQL()
				.SELECT("g.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,p.shopid_pos,s.field3 as newCardNum")
				.FROM("users s")
				.LEFT_OUTER_JOIN("userreg g on s.users_id = g.users_id")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN("address a on s.users_id=a.member_id")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_sap")
				.WHERE("s.field1 in('"
						+ string
						+ "') and a.selfaddress='1' and (o.field1='1' or o.field1='2')"
						+ str);
		return "SELECT w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.shopid_pos, w.firstname,w.newCardNum FROM (SELECT k.users_id, k.logonid ,k.codeNum, "
				+ "k.phone1,k.firstname,k.birthDay,k.shopid_pos,k.newCardNum,rownum rn  FROM ("
				+ sql.toString()
				+ ") k)w WHERE  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String updateMemberInfo(Map<String, Object> parameters) {
		String oldUserCode = parameters.get("oldUserCode").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? ""
				: " username='" + parameters.get("userName") + "',");
		buffer.append("".equals(parameters.get("newUserCode")) ? ""
				: " newusermember='" + parameters.get("newUserCode") + "',");
		buffer.append("".equals(parameters.get("userPhone")) ? "" : " phone='"
				+ parameters.get("userPhone") + "',");
		buffer.append("".equals(parameters.get("userBirthday")) ? ""
				: " birthday='" + parameters.get("userBirthday") + "',");
		buffer.append("".equals(parameters.get("userProfession")) ? ""
				: " profession='" + parameters.get("userProfession") + "',");
		buffer.append(" lastupdate=sysdate");
		String string = buffer.toString();
		return "update x_changemember_info set " + string
				+ " where oldusermember = '" + oldUserCode + "'";
	}

	public String updateMemberDetailInfo(Map<String, Object> parameters) {
		long usersId = Long.parseLong(parameters.get("usersId").toString());
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? ""
				: " firstname='" + parameters.get("userName") + "',");
		buffer.append("".equals(parameters.get("userPhone")) ? "" : " phone1='"
				+ parameters.get("userPhone") + "',");
		buffer.append("".equals(parameters.get("userProfession")) ? ""
				: " field2='" + parameters.get("userProfession") + "',");
		buffer.append(" lastcreate=sysdate");
		String string = buffer.toString();
		return "update address set " + string
				+ " where selfaddress='1' and MEMBER_ID = " + usersId;
	}

	public String getSearch(Map<String, Object> parameters) {
		SQL sql = new SQL()
				.SELECT(" count(g.users_id)as count")
				.FROM(" userreg g")
				.LEFT_OUTER_JOIN(" users s on g.users_id = s.users_id")
				.LEFT_OUTER_JOIN(" address a on g.users_id=a.member_id ")
				.LEFT_OUTER_JOIN("  userdemo o on g.users_id=o.users_id")
				.WHERE("a.SELFADDRESS='1' and (o.field1='1' or o.field1='2' or o.field1='9')");
		return sql.toString();
	}

	public String getUserListSearch(Map<String, Object> parameters) {
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT("g.users_id, g.logonid ,s.field2 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,s.field1")
				.FROM("userreg g")
				.LEFT_OUTER_JOIN("users s on g.users_id = s.users_id")
				.LEFT_OUTER_JOIN("address a on g.users_id=a.member_id")
				.LEFT_OUTER_JOIN("userdemo o on g.users_id=o.users_id")
				.WHERE("a.SELFADDRESS='1' and (o.field1='1' or o.field1='2' or o.field1='9')");
		return "select w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.field1, w.firstname FROM (select k.users_id, k.logonid ,k.codeNum, "
				+ " k.phone1,k.firstname,k.birthDay,k.field1,rownum rn from ("
				+ sql.toString()
				+ ") k)w WHERE  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String getTotalCountForOldAdmin(Map<String, Object> map) {
		StringBuffer buffer = new StringBuffer();
		String shopId = map.get("shopId").toString();
		buffer.append("".equals(map.get("userName")) ? ""
				: " and a.firstname like '%" + map.get("userName") + "%'");
		buffer.append("".equals(map.get("code")) ? "" : " and o.field7 like '%"
				+ map.get("code") + "%'");
		buffer.append("".equals(map.get("phone")) ? ""
				: " and a.phone1 like '%" + map.get("phone") + "%'");
		buffer.append("".equals(map.get("startDate")) ? ""
				: " and o.dateofbirth>=to_date('" + map.get("startDate")
						+ "','yyyy-MM-dd')");
		buffer.append("".equals(map.get("endDate")) ? ""
				: " and o.dateofbirth<=to_date('" + map.get("endDate")
						+ "','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql = new SQL()
				.SELECT("count(g.users_id)as count")
				.FROM(" userreg g")
				.LEFT_OUTER_JOIN(" users s on g.users_id = s.users_id")
				.LEFT_OUTER_JOIN(" userdemo o on g.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" address a on g.users_id=a.member_id ")
				.WHERE("s.field1 in("
						+ shopId
						+ ") and a.SELFADDRESS='1' and (o.field1='1' or o.field1='2')"
						+ str);
		return sql.toString();
	}

	public String getUserListForOldAdmin(Map<String, Object> parameters) {
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		String shopId = parameters.get("shopId").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? ""
				: " and a.firstname like'%" + parameters.get("userName") + "%'");
		buffer.append("".equals(parameters.get("code")) ? ""
				: " and o.field7 like '%" + parameters.get("code") + "%'");
		buffer.append("".equals(parameters.get("phone")) ? ""
				: " and a.phone1 like '%" + parameters.get("phone") + "%'");
		buffer.append("".equals(parameters.get("startDate")) ? ""
				: " and o.dateofbirth>=to_date('" + parameters.get("startDate")
						+ "','yyyy-MM-dd')");
		buffer.append("".equals(parameters.get("endDate")) ? ""
				: " and o.dateofbirth<=to_date('" + parameters.get("endDate")
						+ "','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql = new SQL()
				.SELECT("g.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,s.field1,s.field3 as newCardNum")
				.FROM("userreg g")
				.LEFT_OUTER_JOIN("users s on g.users_id = s.users_id")
				.LEFT_OUTER_JOIN(" userdemo o on g.users_id = o.users_id")
				.LEFT_OUTER_JOIN("address a on g.users_id=a.member_id")
				.WHERE("s.field1 in("
						+ shopId
						+ ") and a.selfaddress='1' and (o.field1='1' or o.field1='2')"
						+ str);
		return "SELECT w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.field1, w.firstname,w.newCardNum FROM (SELECT k.users_id, k.logonid ,k.codeNum, "
				+ "k.phone1,k.firstname,k.birthDay,k.field1,k.newCardNum,rownum rn  FROM ("
				+ sql.toString()
				+ ") k)w WHERE  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String updateOldUserInfo(Map<String, Object> parameters) {
		String oldUserCode = parameters.get("oldUserCode").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? "" : " xm='"
				+ parameters.get("userName") + "',");
		buffer.append("".equals(parameters.get("newUserCode")) ? "" : " gbkh='"
				+ parameters.get("newUserCode") + "',");
		buffer.append("".equals(parameters.get("userPhone")) ? "" : " lxdh='"
				+ parameters.get("userPhone") + "',");
		buffer.append("2000808".equals(parameters.get("shopidNone")) ? " DJFD='"
				+ parameters.get("shopidNone") + "',"
				: "");
		buffer.append("".equals(parameters.get("userBirthday")) ? ""
				: " csny=to_date('" + parameters.get("userBirthday")
						+ "','yyyy-MM-dd')");
		String string = buffer.toString();
		String tring = "";
		String sql = "";
		if ("".equals(parameters.get("userBirthday"))) {
			tring = buffer.substring(0, buffer.length() - 1).toString();
			sql = "update t_vip_visitantcard set " + tring + " where gbkh = '"
					+ oldUserCode + "'";
		} else {
			sql = "update t_vip_visitantcard set " + string + " where gbkh = '"
					+ oldUserCode + "'";
		}
		return sql;
	}

	public String updateMemberInfoForUserInfo(Map<String, Object> parameters) {
		String oldUserCode = parameters.get("oldUserCode").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("userName")) ? ""
				: " lastname='" + parameters.get("userName") + "',");
		buffer.append("".equals(parameters.get("newUserCode")) ? ""
				: " card_id_ext='" + parameters.get("newUserCode") + "',");
		buffer.append("".equals(parameters.get("userPhone")) ? ""
				: " telephone='" + parameters.get("userPhone") + "',");
		buffer.append("".equals(parameters.get("userBirthday")) ? ""
				: " birthdate=to_date('" + parameters.get("userBirthday")
						+ "','yyyy-MM-dd'),");
		buffer.append("".equals(parameters.get("userProfession")) ? ""
				: " occupation='" + parameters.get("userProfession") + "',");
		buffer.append("isprocess='2'");
		String string = buffer.toString();
		return "update x_b2ctocrm_userinfo set " + string
				+ " where card_id_ext = '" + oldUserCode + "'";
	}

	public String getPaymentDividedCount(Map<String, Object> parameters) {
		String str = parameters.get("logonId").toString();
		SQL sql = new SQL().SELECT(" count(a.id)as count")
				.FROM(" x_divided_total a")
				.WHERE("a.FRANCHISEE_ID ='" + str + "'");
		return sql.toString();
	}

	public String getPaymentDividedList(Map<String, Object> parameters) {
		String str = parameters.get("logonId").toString();
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" e.id, e.franchisee_id ,e.shopid ,e.type ,e.sale_total_number ,e.sale_total_money ,e.franchisee_deliver_money ,e.total_profit, e.commission_debit ,"
						+ " e.consignment_debit ,e.member_pay_ship  ,e.member_pay_procedure ,e.transfer_procedure_pay ,e.billing_procedure_pay ,e.pay_profit ,e.count_time")
				.FROM(" x_divided_total e")
				.WHERE("e.FRANCHISEE_ID='" + str + "'");
		return "select w.id, w.franchisee_id ,w.shopid ,w.type ,w.sale_total_number ,w.sale_total_money ,w.franchisee_deliver_money ,w.total_profit,"
				+ " w.commission_debit ,w.consignment_debit ,w.member_pay_ship  ,w.member_pay_procedure ,w.transfer_procedure_pay ,w.billing_procedure_pay, "
				+ " w.pay_profit ,w.count_time from (select k.id,k.franchisee_id,k.shopid,k.type,k.sale_total_number,k.sale_total_money,k.franchisee_deliver_money,"
				+ "k.total_profit,k.commission_debit,k.consignment_debit,k.member_pay_ship,k.member_pay_procedure ,k.transfer_procedure_pay,k.billing_procedure_pay ,"
				+ "k.pay_profit ,k.count_time,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between " + index + " and " + sum;
	}

	public String getSearchPaymentDividedCount(Map<String, Object> parameters) {
		String logonId = (String) parameters.get("logonId");
		String shopid = (String) parameters.get("chestr");
		StringBuffer buffer = new StringBuffer();
		String[] arry = shopid.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		buffer.append("".equals(string) ? "" : " and e.shopid in(" + string
				+ ")");
		buffer.append("".equals(parameters.get("startDate")) ? ""
				: " and e.count_time>=to_date('" + parameters.get("startDate")
						+ "','yyyy-MM-dd')");
		buffer.append("".equals(parameters.get("endDate")) ? ""
				: " and e.count_time<=to_date('" + parameters.get("endDate")
						+ "','yyyy-MM-dd')");
		SQL sql = new SQL()
				.SELECT(" count(e.id)as count")
				.FROM(" x_divided_total e")
				.WHERE("e.FRANCHISEE_ID ='" + logonId + "'" + buffer.toString());
		return sql.toString();
	}

	public String getSearchPaymentDividedList(Map<String, Object> parameters) {
		String logonId = (String) parameters.get("logonId");
		String shopid = (String) parameters.get("chestr");
		StringBuffer buffer = new StringBuffer();
		String[] arry = shopid.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		buffer.append("".equals(string) ? "" : " and e.shopid in(" + string
				+ ")");
		buffer.append("".equals(parameters.get("startDate")) ? ""
				: " and e.count_time>=to_date('" + parameters.get("startDate")
						+ "','yyyy-MM-dd')");
		buffer.append("".equals(parameters.get("endDate")) ? ""
				: " and e.count_time<=to_date('" + parameters.get("endDate")
						+ "','yyyy-MM-dd')");
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" e.id, e.franchisee_id ,e.shopid ,e.type ,e.sale_total_number ,e.sale_total_money ,e.franchisee_deliver_money ,e.total_profit, e.commission_debit ,"
						+ " e.consignment_debit ,e.member_pay_ship  ,e.member_pay_procedure ,e.transfer_procedure_pay ,e.billing_procedure_pay ,e.pay_profit ,e.count_time")
				.FROM(" x_divided_total e")
				.WHERE("e.FRANCHISEE_ID ='" + logonId + "'" + buffer.toString());
		return "select w.id, w.franchisee_id ,w.shopid ,w.type ,w.sale_total_number ,w.sale_total_money ,w.franchisee_deliver_money ,w.total_profit,"
				+ " w.commission_debit ,w.consignment_debit ,w.member_pay_ship  ,w.member_pay_procedure ,w.transfer_procedure_pay ,w.billing_procedure_pay, "
				+ "w.pay_profit ,w.count_time from (select k.id,k.franchisee_id,k.shopid,k.type,k.sale_total_number,k.sale_total_money,k.franchisee_deliver_money,"
				+ "k.total_profit,k.commission_debit ,k.consignment_debit ,k.member_pay_ship  ,k.member_pay_procedure ,k.transfer_procedure_pay ,k.billing_procedure_pay, "
				+ "k.pay_profit ,k.count_time,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between " + index + " and " + sum;
	}

	public String getPaymentDividedDetailCount(Map<String, Object> parameters) {
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		String shopid = parameters.get("shopid").toString();
		buffer1.append("".equals(parameters.get("startDate")) ? ""
				: " to_char(a.sale_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer1.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(a.sale_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer2.append("".equals(parameters.get("startDate")) ? ""
				: " to_char(a.return_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer2.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(a.return_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		String str1[] = buffer1.toString().split("and");
		String bbu1 = "";
		String bbu2 = "";
		if ("".equals(str1[0].trim())) {
			bbu1 = str1[1];
		} else {
			bbu1 = buffer1.toString();
		}
		String str2[] = buffer2.toString().split("and");
		if ("".equals(str2[0].trim())) {
			bbu2 = str2[1];
		} else {
			bbu2 = buffer2.toString();
		}
		SQL sql = new SQL()
				.SELECT(" count(a.detail_id)as count")
				.FROM(" x_divided_detail a")
				.WHERE(" a.shopid='" + shopid + "' and (" + bbu1 + " or "
						+ bbu2 + ")");
		return sql.toString();
	}

	public String getPaymentDividedDetailList(Map<String, Object> parameters) {
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		String shopid = parameters.get("shopid").toString();
		buffer1.append("".equals(parameters.get("startDate")) ? ""
				: " to_char(e.sale_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer1.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(e.sale_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer2.append("".equals(parameters.get("startDate")) ? ""
				: " to_char(e.return_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer2.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(e.return_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		String str1[] = buffer1.toString().split("and");
		String bbu1 = "";
		String bbu2 = "";
		if ("".equals(str1[0].trim())) {
			bbu1 = str1[1];
		} else {
			bbu1 = buffer1.toString();
		}
		String str2[] = buffer2.toString().split("and");
		if ("".equals(str2[0].trim())) {
			bbu2 = str2[1];
		} else {
			bbu2 = buffer2.toString();
		}
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" e.detail_id,e.order_id,e.member_card_id,e.member_card_number,e.member_name,e.member_phone,e.sale_time ,e.order_type,e.Retail_Price,"
						+ " e.product_number, e.Distribution_Net,e.Distribution_total,e.Purchase_cost,e.Purchase_total,e.shopid ")
				.FROM(" x_divided_detail e")
				.WHERE("e.shopid='" + shopid + "' and (" + bbu1 + " or " + bbu2
						+ ")");
		return "select w.detail_id,w.order_id,w.member_card_id,w.member_card_number,w.member_name,w.member_phone,w.sale_time ,w.order_type,w.Retail_Price,"
				+ " w.product_number,w.Distribution_Net,w.Distribution_total,w.Purchase_cost,w.Purchase_total,w.shopid from (select k.detail_id,k.order_id,"
				+ " k.member_card_id,k.member_card_number,k.member_name,k.member_phone,k.sale_time ,k.order_type,k.Retail_Price,k.product_number, k.Distribution_Net,"
				+ " k.Distribution_total,k.Purchase_cost,k.Purchase_total,k.shopid,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String getSearchPaymentDividedDetailCount(
			Map<String, Object> parameters) {
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		StringBuffer buffer3 = new StringBuffer();
		StringBuffer buffer4 = new StringBuffer();
		String shopid = parameters.get("shopid").toString();
		buffer1.append("".equals(parameters.get("newDate")) ? ""
				: " to_char(d.sale_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer1.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(d.sale_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer2.append("".equals(parameters.get("newDate")) ? ""
				: " to_char(d.return_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer2.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(d.return_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer3.append("".equals(parameters.get("orderNumber")) ? ""
				: " and d.ORDER_ID ='" + parameters.get("orderNumber") + "'");
		String sDate = parameters.get("startDate").toString();
		String eDate = parameters.get("endDate").toString();
		buffer4.append(" to_char(w.sale_time,'yyyy-MM-dd') >='" + sDate + "'");
		buffer4.append(" and to_char(w.sale_time,'yyyy-MM-dd') <='" + eDate
				+ "'");
		String bbu1 = "";
		String bbu2 = "";
		String bbu3 = "";
		String str1[] = buffer1.toString().split("and");
		if ("".equals(str1[0].trim())) {
			bbu1 = str1[1];
		} else {
			bbu1 = buffer1.toString();
		}
		String str2[] = buffer2.toString().split("and");
		if ("".equals(str2[0].trim())) {
			bbu2 = str2[1];
		} else {
			bbu2 = buffer2.toString();
		}
		String str3[] = buffer4.toString().split("and");
		if ("".equals(sDate) && !"".equals(eDate)) {
			bbu3 = str3[1];
		} else if (!"".equals(sDate) && "".equals(eDate)) {
			bbu3 = str3[0];
		} else if ("".equals(sDate) && "".equals(eDate)) {
			bbu3 = "1=1";
		} else {
			bbu3 = buffer4.toString();
		}
		SQL sql = new SQL()
				.SELECT(" count(w.detail_id)as count")
				.FROM(" (select d.detail_id,d.sale_time from x_divided_detail d where d.shopid='"
						+ shopid
						+ "'"
						+ buffer3.toString()
						+ " and "
						+ "("
						+ bbu1 + " or " + bbu2 + "))w").WHERE(bbu3);
		return sql.toString();
	}

	public String getSearchPaymentDividedDetailList(
			Map<String, Object> parameters) {
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		StringBuffer buffer3 = new StringBuffer();
		StringBuffer buffer4 = new StringBuffer();
		String shopid = parameters.get("shopid").toString();
		buffer1.append("".equals(parameters.get("newDate")) ? ""
				: " to_char(d.sale_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer1.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(d.sale_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer2.append("".equals(parameters.get("newDate")) ? ""
				: " to_char(d.return_time,'yyyy-MM-dd') >='"
						+ parameters.get("startDate") + "'");
		buffer2.append("".equals(parameters.get("countTime")) ? ""
				: " and to_char(d.return_time,'yyyy-MM-dd') <='"
						+ parameters.get("countTime") + "'");
		buffer3.append("".equals(parameters.get("orderNumber")) ? ""
				: " and d.ORDER_ID ='" + parameters.get("orderNumber") + "'");
		String sDate = parameters.get("startDate").toString();
		String eDate = parameters.get("endDate").toString();
		buffer4.append(" to_char(w.sale_time,'yyyy-MM-dd') >='" + sDate + "'");
		buffer4.append(" and to_char(w.sale_time,'yyyy-MM-dd') <='" + eDate
				+ "'");
		String bbu1 = "";
		String bbu2 = "";
		String bbu3 = "";
		String str1[] = buffer1.toString().split("and");
		if ("".equals(str1[0].trim())) {
			bbu1 = str1[1];
		} else {
			bbu1 = buffer1.toString();
		}
		String str2[] = buffer2.toString().split("and");
		if ("".equals(str2[0].trim())) {
			bbu2 = str2[1];
		} else {
			bbu2 = buffer2.toString();
		}
		String str3[] = buffer4.toString().split("and");
		if ("".equals(sDate) && !"".equals(eDate)) {
			bbu3 = str3[1];
		} else if (!"".equals(sDate) && "".equals(eDate)) {
			bbu3 = str3[0];
		} else if ("".equals(sDate) && "".equals(eDate)) {
			bbu3 = "1=1";
		} else {
			bbu3 = buffer4.toString();
		}
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" w.detail_id,w.order_id,w.member_card_id,w.member_card_number,w.member_name,w.member_phone,w.sale_time ,w.order_type,w.Retail_Price,"
						+ " w.product_number, w.Distribution_Net,w.Distribution_total,w.Purchase_cost,w.Purchase_total,w.shopid  ")
				.FROM(" (select d.* from x_divided_detail d where d.shopid='"
						+ shopid + "'" + buffer3.toString() + " and " + "("
						+ bbu1 + " or " + bbu2 + "))w").WHERE(bbu3);
		return "select m.detail_id,m.order_id,m.member_card_id,m.member_card_number,m.member_name,m.member_phone,m.sale_time ,m.order_type,m.Retail_Price,"
				+ " m.product_number,m.Distribution_Net,m.Distribution_total,m.Purchase_cost,m.Purchase_total,m.shopid from (select k.detail_id,k.order_id,"
				+ " k.member_card_id,k.member_card_number,k.member_name,k.member_phone,k.sale_time ,k.order_type,k.Retail_Price,k.product_number, k.Distribution_Net,"
				+ " k.Distribution_total,k.Purchase_cost,k.Purchase_total,k.shopid,rownum rn from("
				+ sql.toString()
				+ ")k)m where  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String getDividedForCurrentDate(Map<String, Object> parameters) {
		String currentDay = parameters.get("currentDay").toString();
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT("sum(profit)as currentPayment")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string
						+ "') and  to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')='"
						+ currentDay + "' ");
		return sql.toString();
	}

	public String getDividedForCurrentMonth(Map<String, Object> parameters) {
		String monthStart = parameters.get("monthStart").toString();
		String monthEnd = parameters.get("monthEnd").toString();
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT("sum(profit)as monthPayment")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string
						+ "') and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ monthStart
						+ "' and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ monthEnd + "'");
		return sql.toString();
	}

	public String getDividedForCurrentTime(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("startDate")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ parameters.get("startDate") + "'");
		buffer.append("".equals(parameters.get("endDate")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ parameters.get("endDate") + "'");
		SQL sql = new SQL().SELECT("sum(profit)as timePayment")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string + "')" + buffer.toString());
		return sql.toString();
	}

	public String getDividedForCurrentDateCount(Map<String, Object> parameters) {
		String currentDate = parameters.get("currentDate").toString();
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT(" count(e.id)as count")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string
						+ "') and  to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')='"
						+ currentDate + "' ");
		return sql.toString();
	}

	public String getDividedForCurrentDateList(Map<String, Object> parameters) {
		String currentDate = parameters.get("currentDate").toString();
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT(" e.id,e.shopid,e.card_id,e.order_id,e.old_orders_id,e.total_order,e.cost_order,e.service_cost,e.Invoice_cost,e.profit,e.last_update_date ,e.type,e.act_ServiceCost,e.shipping,e.isprocess,e.real_returnCost,e.bank_cost")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string
						+ "') and  to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')='"
						+ currentDate + "' ");
		return "select m.id,m.shopid,m.card_id,m.order_id,m.old_orders_id,m.total_order,m.cost_order,m.service_cost,m.Invoice_cost,m.profit,m.last_update_date,m.type,m.act_ServiceCost,m.shipping,m.isprocess,m.real_returnCost,m.bank_cost from "
				+ "(select k.id,k.shopid,k.card_id,k.order_id,k.old_orders_id,k.total_order,k.cost_order,k.service_cost,k.Invoice_cost,k.profit,k.last_update_date ,k.type,k.act_ServiceCost,k.shipping,k.isprocess,k.real_returnCost,k.bank_cost,rownum rn"
				+ " from("
				+ sql.toString()
				+ ")k)m where  rn between "
				+ index
				+ " and " + sum;
	}

	public String getDividedForCurrentMonthCount(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String monthStart = parameters.get("monthStart").toString();
		String monthEnd = parameters.get("monthEnd").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT(" count(e.id)as count")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string
						+ "') and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ monthStart
						+ "' and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ monthEnd + "'");
		return sql.toString();
	}

	public String getDividedForCurrentMonthList(Map<String, Object> parameters) {
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		String str = parameters.get("shopId").toString();
		String monthStart = parameters.get("monthStart").toString();
		String monthEnd = parameters.get("monthEnd").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		SQL sql = new SQL()
				.SELECT("e.id,e.shopid,e.card_id,e.order_id,e.old_orders_id,e.total_order,e.cost_order,e.service_cost,e.Invoice_cost,e.profit,e.last_update_date ,e.type,e.act_ServiceCost,e.shipping,e.isprocess,e.real_returnCost,e.bank_cost")
				.FROM(" x_dividedmiddle e")
				.WHERE(" e.shopid in('" + string
						+ "') and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ monthStart
						+ "' and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ monthEnd + "'");
		String ss = "select m.id,m.shopid,m.card_id,m.order_id,m.old_orders_id,m.total_order,m.cost_order,m.service_cost,m.Invoice_cost,m.profit,m.last_update_date,m.type,m.act_ServiceCost,m.shipping,m.isprocess,m.real_returnCost,m.bank_cost from "
				+ "(select k.id,k.shopid,k.card_id,k.order_id,k.old_orders_id,k.total_order,k.cost_order,k.service_cost,k.Invoice_cost,k.profit,k.last_update_date,k.type,k.act_ServiceCost,k.shipping,k.isprocess,k.real_returnCost,k.bank_cost,rownum rn"
				+ " from("
				+ sql.toString()
				+ ")k)m where  rn between "
				+ index
				+ " and " + sum;
		return ss;
	}

	public String getDividedForCurrentTimeCount(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("monthStart")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ parameters.get("monthStart") + "'");
		buffer.append("".equals(parameters.get("monthEnd")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ parameters.get("monthEnd") + "'");
		SQL sql = new SQL().SELECT("count(e.id)as count")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string + "')" + buffer.toString());
		return sql.toString();
	}

	public String getDividedForCurrentTimeList(Map<String, Object> parameters) {
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		String str = parameters.get("shopId").toString();
		String[] arry = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]).append("'").append(",").append("'");
		}
		String string = sb.substring(0, sb.length() - 3).toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals(parameters.get("monthStart")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')>='"
						+ parameters.get("monthStart") + "'");
		buffer.append("".equals(parameters.get("monthEnd")) ? ""
				: " and to_char(e.LAST_UPDATE_DATE,'yyyy-MM-dd')<='"
						+ parameters.get("monthEnd") + "'");
		SQL sql = new SQL()
				.SELECT("e.id,e.shopid,e.card_id,e.order_id,e.old_orders_id,e.total_order,e.cost_order,e.service_cost,e.Invoice_cost,e.profit,e.last_update_date ,e.type,e.act_ServiceCost,e.shipping,e.isprocess,e.real_returnCost,e.bank_cost")
				.FROM(" x_dividedmiddle e")
				.WHERE("e.shopid in('" + string + "')" + buffer.toString());
		return "select m.id,m.shopid,m.card_id,m.order_id,m.old_orders_id,m.total_order,m.cost_order,m.service_cost,m.Invoice_cost,m.profit,m.last_update_date,m.type,m.act_ServiceCost,m.shipping,m.isprocess,m.real_returnCost,m.bank_cost from "
				+ "(select k.id,k.shopid,k.card_id,k.order_id,k.old_orders_id,k.total_order,k.cost_order,k.service_cost,k.Invoice_cost,k.profit,k.last_update_date,k.type,k.act_ServiceCost,k.shipping,k.isprocess,k.real_returnCost,k.bank_cost,rownum rn"
				+ " from("
				+ sql.toString()
				+ ")k)m where  rn between "
				+ index
				+ " and " + sum;
	}

	public String getMemberCount(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		SQL sql = new SQL()
				.SELECT("count(s.users_id)as count")
				.FROM(" users s")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id ")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id ")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_pos")
				.WHERE("s.field1 ='"
						+ str
						+ "' and a.selfaddress='1' and (o.field1='1' or o.field1='2')");
		return sql.toString();
	}

	public String getMemberList(Map<String, Object> parameters) {
		String str = parameters.get("shopId").toString();
		String index = parameters.get("index").toString();
		String sum = parameters.get("sum").toString();
		SQL sql = new SQL()
				.SELECT(" s.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,p.shopid_pos,s.field3 as newCardNum")
				.FROM(" users s")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id ")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_sap")
				.WHERE("s.field1 ='"
						+ str
						+ "' and a.selfaddress='1' and (o.field1='1' or o.field1='2')");
		return "select w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.shopid_pos, w.firstname,w.newCardNum from (select k.users_id, k.logonid ,k.codeNum, "
				+ "k.phone1,k.firstname,k.birthDay,k.shopid_pos,k.newCardNum ,rownum rn from("
				+ sql.toString()
				+ ")k)w where  rn between "
				+ index
				+ " and "
				+ sum;
	}

	public String exportMemberList(Map<String, Object> map) {
		String shopId = map.get("shopId").toString();
		String type = map.get("type").toString();
		StringBuffer sb = new StringBuffer();
		String string = "";
		if ("admin".equals(type)) {
			String[] arry = shopId.split(",");
			for (int i = 0; i < arry.length; i++) {
				sb.append(arry[i]).append("'").append(",").append("'");
			}
			string = sb.substring(0, sb.length() - 3).toString();
		} else {
			string = shopId;
		}
		SQL sql = new SQL()
				.SELECT("g.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,p.shopid_pos,s.field3 as newCardNum")
				.FROM(" users s ")
				.LEFT_OUTER_JOIN(" userreg g on s.users_id = g.users_id")
				.LEFT_OUTER_JOIN(" userdemo o on s.users_id = o.users_id")
				.LEFT_OUTER_JOIN(" address a on s.users_id=a.member_id ")
				.LEFT_OUTER_JOIN(" x_sap p on s.field1 = p.shopid_sap")
				.WHERE("s.field1 in('"
						+ string
						+ "') and a.SELFADDRESS='1' and (o.field1='1' or o.field1='2')");
		return sql.toString();
	}

	/**
	 * LogonId获得Users_Id
	 * 
	 * @param parameters
	 * @return
	 */
	public String getUsersIdByLogonId(Map<String, Object> parameters) {
		SQL sql = new SQL().SELECT("USERS_ID").FROM("USERREG")
				.WHERE("LOGONID = '" + parameters.get("logonid") + "'");
		return sql.toString();
	}
}
