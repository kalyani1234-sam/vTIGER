package commonUtils;

import java.util.Random;

public class JavaUtils {

	public int getRandomNumber()
	{
		/* creating this for every time we need edit orgnisations data
		 * along with company name number should be generated */
		Random rn = new Random();
		int ran=rn.nextInt(500); //has to return random num for every time u created orgnisation
		return ran;
	}
}
