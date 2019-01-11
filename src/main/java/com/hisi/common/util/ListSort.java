package com.hisi.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.hisi.model.vo.CheckVo;
import com.hisi.model.vo.ComprehensiveOrderVo;


public class ListSort {
	public static class UserBean {
		private String id;
		private String birthday;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
	}

	public static void main(String[] args) {
       /* List<UserBean> list = new ArrayList<UserBean>();
        UserListGenerate(list);
        System.out.println("********排序前*******");
for(UserBean user: list){
System.out.println(user.getBirthday());
}
        ListSort(list);
        System.out.println("******排序后*****");
 for(UserBean user: list){
System.out.println(user.getBirthday());
 }*/
    }

	public static void UserListGenerate(List<UserBean> list) {
		UserBean user1 = new UserBean();
		UserBean user2 = new UserBean();
		UserBean user3 = new UserBean();
		user1.setId("zhagnsan");
		user1.setBirthday("1980-11-01");

		user2.setId("lisi");
		user2.setBirthday("1981-12-01");

		user3.setId("wangwu");
		user3.setBirthday("1980-12-01");

		list.add(user1);
		list.add(user2);
		list.add(user3);
	}

	public  void ListSort(List<CheckVo> list) {
		Collections.sort(list, new Comparator<CheckVo>() {
			@Override
			public int compare(CheckVo o1, CheckVo o2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					/*Date dt1 = format.parse(o1.getBirthday());
					Date dt2 = format.parse(o2.getBirthday());*/
					if (o1.getTime().getTime() > o2.getTime().getTime()) {
						return 1;
					} else if (o1.getTime().getTime() < o2.getTime().getTime()) {
						return -1;
					} else {
						return 0;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}
	public  void ListSort1(List<ComprehensiveOrderVo> list) {
		Collections.sort(list, new Comparator<ComprehensiveOrderVo>() {
			@Override
			public int compare(ComprehensiveOrderVo o1, ComprehensiveOrderVo o2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					/*Date dt1 = format.parse(o1.getBirthday());
					Date dt2 = format.parse(o2.getBirthday());*/
					if (o1.getCreateTime().getTime() > o2.getCreateTime().getTime()) {
						return -1;
					} else if (o1.getCreateTime().getTime() < o2.getCreateTime().getTime()) {
						return 1;
					} else {
						return 0;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}
}
