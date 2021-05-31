package com.iot.g89;

/**
 * <p>Entity.</p>
 * <p>Administrator class.</p>
 *
 * @version 0.5
 * @author ly129
 */
public class Administrator extends User{

	/**
	 * <p>Instantiate an admin. The function auto collect information from csv file.</p>
	 * <p>If there is no such Id in the CSV, the function will return an admin with Id equals to "none".</p>
	 *
	 * @param userId AdminId
	 */
	public Administrator(String userId) {
		super(userId);
	}

	/**
	 * Mainly use for the reflection.
	 *
	 * @param para para
	 */
	public Administrator(String[] para) {
		super(para);
	}
}
