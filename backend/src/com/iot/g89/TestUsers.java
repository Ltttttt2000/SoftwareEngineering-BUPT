package com.iot.g89;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUsers {

	@Test
	void test() {
		
		//test client
		Client user_1 = new Client("1001");
		
		user_1.setPassword("1234567");
	    user_1.setUserType("Normal");
		user_1.setSex( "Male");
		user_1.setPhone( "13176788165");
		user_1.loginLicense = true;
		user_1.setRechargeAmount(180);
		user_1.setResume("I like Yoga");
		user_1.setAge(40);
		user_1.setChest(95);
		user_1.setHeight(189);
		user_1.setWeight(140);
		user_1.setWaist(12);
		user_1.setHip(56);
		
		//注册user_2
	    Client user_2 = new Client("1002");
        user_2.setPassword("987654321");
		user_2.setUserType("Normal");
		user_2.setSex( "FeMale");
		user_2.setPhone("15601159166");
		user_2.banThisAccount();
		user_2.setRechargeAmount(250);
		user_2.setResume("I like Running, hiking");
		user_2.setAge(20);
		user_2.setChest(85);
		user_2.setHeight(164);
		user_2.setWeight(95);
		user_2.setWaist(8);
		user_2.setHip(35);
		System.out.println("\n");
		
		//test recharge and consume functions
		user_1.recharge(100);
		assertEquals(280, user_1.getRechargeAmount());
		user_1.consume(50);
		assertEquals(230, user_1.getRechargeAmount());
		
		//test update functions
		user_1.recharge(600);
		assertEquals("Member", user_1.getUserType());
		user_1.recharge(1000);
		assertEquals("SupremeMember", user_1.getUserType());

		
		Client user3 = new Client("1003");
		
		//注册instructor_1
		Instructor user_4 = new Instructor("2001");
        user_4.setPassword("203534");
		user_4.setUserType("supremember");
		user_4.setSex( "FeMale");
		user_4.setPhone("15553756699");
		user_4.banThisAccount();
		user_4.setResume("I like playing");
		user_4.setAge(30);
		user_4.setChest(90);
		user_4.setHeight(170);
		user_4.setWeight(102);
		user_4.setWaist(10);
		user_4.setHip(25);
		user_4.setInstructorMoney(500);
		System.out.println("\n");
		
		//System.out.println(user1.entry);
		//System.out.println(user2.entry);
		assertEquals(40, user_1.getAge() );
		
		assertEquals("15601159166", user_2.getPhone());
		
		//登录 instructor
		user_4 = new Instructor("instructor_1");
		
		//test admin
		Administrator user_41 = new Administrator("3001");
        user_41.setPassword("203534");
		user_41.setUserType("supremember");
		user_41.setSex( "FeMale");
		user_41.setPhone("15553756699");
		user_41.banThisAccount();
		user_41.setResume("I like playing");
		user_41.setAge(30);
		user_41.setChest(90);
		user_41.setHeight(170);
		user_41.setWeight(102);
		user_41.setWaist(10);
		user_41.setHip(25);
		System.out.println("\n");

	}

}
