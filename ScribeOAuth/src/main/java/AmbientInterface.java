
public class AmbientInterface {
	
	public static String runInterface(int percentCompleted){
		if (percentCompleted <= 10){
			return "/sadFace.png";
		}
		else if (percentCompleted > 10 && percentCompleted < 90){
			return "/middleFace.png";
		}
		else if (percentCompleted >= 90){
			return "/happyFace.png";
		}
		else
			return "error";
	}
	
	
}
