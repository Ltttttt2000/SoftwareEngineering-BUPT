package com.iot.g89;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestFunctions {
	
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
		
		System.out.println("client");
		user_1.listAllInstructor();
        user_1.listMyInstructor();
		
		Instructor instructor = new Instructor("2001");
		instructor.setPassword("203534");
		instructor.setUserType("supremember");
		instructor.setSex( "FeMale");
		instructor.setPhone("15553756699");
		instructor.banThisAccount();
		instructor.setResume("I like playing");
		instructor.setAge(30);
		instructor.setChest(90);
		instructor.setHeight(170);
		instructor.setWeight(102);
		instructor.setWaist(10);
		instructor.setHip(25);
		instructor.setInstructorMoney(40);
		
		user_1.buyInstructor(instructor);

	/*	
		user_1.listPublicVideo();
		user_1.listPaidVideo();
		user_1.listPurchasedVideo();
		user_1.listPrivateVideo();
	*/
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
	
		
		Administrator admin = new Administrator("3001");
		admin.setPassword("203534");
		admin.setUserType("supremember");
		admin.setSex( "FeMale");
		admin.setPhone("15553756699");
		admin.banThisAccount();
		admin.setResume("I like playing");
		admin.setAge(30);
		admin.setChest(90);
		admin.setHeight(170);
		admin.setWeight(102);
		admin.setWaist(10);
		admin.setHip(25);
		
		System.out.println("admin");	
		admin.listAllClient();
		admin.listAllInstructor();
		admin.listAllUser();
		//admin.listAllVideo();
	}
}
