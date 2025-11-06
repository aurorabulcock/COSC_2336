public class car {
	String make;
	String model;
	int year;
	String VIN;
	double mileage;
	double speed;
	double xpos;
	double ypos;
	double direction;
	String plate;
	String plateState;
	public static int MAXSPEED = 200;
	public void NewCar(String[] data)
	{
		VIN = data[0];
		plateState = data[1];
		plate = data[2];
		mileage = 0;
		speed = 0;
		System.out.println("New Car Added = VIN " + VIN) ;
		return;
	}
	public void accelerate(double speedupby)
	{
		speed = speed + speedupby;
		if (speed > MAXSPEED)
		{
			speed = MAXSPEED;
		}
	}
	public void brake(double slowdownby)
	{
		speed-=slowdownby;
		if (speed < 0)
		{
			speed = 0;
		}
	}
}
