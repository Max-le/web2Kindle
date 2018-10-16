import java.io.IOException;

public class PressKey{

	public static void main(String[] args) {
		pressALT_K_in_Chrome();

	}

public static void pressALT_K_in_Chrome(){
	try{
		String[] args = new String[] {"open","press_ALT_K.app"};
		Process proc = new ProcessBuilder(args).start();
	}
	catch (IOException io){
		System.out.println("Error.");
	}
}
}
